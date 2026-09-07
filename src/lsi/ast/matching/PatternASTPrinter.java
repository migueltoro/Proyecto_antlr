package lsi.ast.matching;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import lsi.ast.bounds.OneSideBound;
import lsi.ast.bounds.TwoSideBound;
import lsi.ast.common.Index;
import lsi.ast.common.Set_of;
import lsi.ast.constraints.RelationalConstraint;
import lsi.ast.declarations.VarDeclaration;
import lsi.ast.expressions.BinaryExpr;
import lsi.ast.expressions.BinaryOperator;
import lsi.ast.expressions.BooleanLiteral;
import lsi.ast.expressions.CastExpr;
import lsi.ast.expressions.DoubleLiteral;
import lsi.ast.expressions.FunctionCallExpr;
import lsi.ast.expressions.IdentifierExpr;
import lsi.ast.expressions.IntLiteral;
import lsi.ast.expressions.UnaryExpr;
import lsi.ast.expressions.UnaryOperator;
import lsi.ast.linear.LinearFactor;
import lsi.ast.linear.Sum;
import lsi.ast.matching.PatternAST.AllOfPattern;
import lsi.ast.matching.PatternAST.AnyElementPattern;
import lsi.ast.matching.PatternAST.AnyOfPattern;
import lsi.ast.matching.PatternAST.AnyPattern;
import lsi.ast.matching.PatternAST.FieldPattern;
import lsi.ast.matching.PatternAST.LinearExprVariable;
import lsi.ast.matching.PatternAST.ListPattern;
import lsi.ast.matching.PatternAST.NodePattern;
import lsi.ast.matching.PatternAST.Pattern;
import lsi.ast.matching.PatternAST.PredicatePattern;
import lsi.ast.matching.PatternAST.ValuePattern;
import lsi.ast.matching.PatternAST.VariablePattern;
import lsi.ast.types.BooleanType;
import lsi.ast.types.DoubleType;
import lsi.ast.types.IntegerType;
import lsi.ast.types.StringType;
import lsi.ast.variables.Variable;

/**
 * Imprime un {@link PatternAST.Pattern} con una sintaxis cercana a AMPL, para
 * facilitar su lectura durante el desarrollo y la depuración.
 *
 * <p>Las variables del patrón (aquellas partes instanciables, como
 * {@link VariablePattern} o {@link LinearExprVariable}) se muestran con el
 * prefijo {@code ?}, por ejemplo {@code ?left}. Como un {@link NodePattern}
 * solo restringe los campos indicados explícitamente, los campos no
 * mencionados se muestran con {@code _} (comodín).</p>
 */
public final class PatternASTPrinter {

    private PatternASTPrinter() {
    }

    public static String print(Pattern pattern) {
        return format(pattern);
    }

    /**
     * Imprime el patrón junto con la lista de variables que declara,
     * indicando el tipo esperado de cada una. Pensado para volcar por
     * consola de forma legible al definir un patrón.
     */
    public static String describe(Pattern pattern) {
        StringBuilder text = new StringBuilder();
        text.append(format(pattern));
        Map<String, PatternAST.VariableDeclaration> variables = PatternAST.variablesOf(pattern);
        if (!variables.isEmpty()) {
            text.append("  donde ");
            boolean first = true;
            for (PatternAST.VariableDeclaration declaration : variables.values()) {
                if (!first) {
                    text.append(", ");
                }
                text.append('?').append(declaration.name())
                        .append(" : ").append(declaration.expectedType().getSimpleName());
                first = false;
            }
        }
        return text.toString();
    }

    private static String format(Pattern pattern) {
        if (pattern instanceof AnyPattern) {
            return "_";
        }
        if (pattern instanceof ValuePattern value) {
            return formatValue(value.expected());
        }
        if (pattern instanceof PredicatePattern) {
            return "<predicado>";
        }
        if (pattern instanceof VariablePattern<?> variable) {
            return formatVariable(variable.name(), variable.innerPattern());
        }
        if (pattern instanceof LinearExprVariable variable) {
            return formatVariable(variable.name(), variable.innerPattern());
        }
        if (pattern instanceof NodePattern nodePattern) {
            return formatNode(nodePattern);
        }
        if (pattern instanceof ListPattern listPattern) {
            StringBuilder text = new StringBuilder("[");
            List<Pattern> elements = listPattern.elements();
            for (int i = 0; i < elements.size(); i++) {
                if (i > 0) {
                    text.append(", ");
                }
                text.append(format(elements.get(i)));
            }
            if (listPattern.allowExtraElements()) {
                text.append(elements.isEmpty() ? "..." : ", ...");
            }
            return text.append("]").toString();
        }
        if (pattern instanceof AnyElementPattern anyElement) {
            return "exists(" + format(anyElement.element()) + ")";
        }
        if (pattern instanceof AllOfPattern allOf) {
            return "(" + joinPatterns(allOf.patterns(), " && ") + ")";
        }
        if (pattern instanceof AnyOfPattern anyOf) {
            return "(" + joinPatterns(anyOf.patterns(), " || ") + ")";
        }
        return Objects.toString(pattern);
    }

    private static String formatVariable(String name, Pattern innerPattern) {
        if (innerPattern instanceof AnyPattern) {
            return "?" + name;
        }
        return "?" + name + "@(" + format(innerPattern) + ")";
    }

    private static String joinPatterns(List<Pattern> patterns, String separator) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < patterns.size(); i++) {
            if (i > 0) {
                text.append(separator);
            }
            text.append(format(patterns.get(i)));
        }
        return text.toString();
    }

    private static String formatNode(NodePattern nodePattern) {
        Class<?> type = nodePattern.nodeType();
        Map<String, Pattern> fields = fieldsByName(nodePattern);

        if (type == RelationalConstraint.class) {
            return fieldOr(fields, "left") + " " + relOperatorOrPlaceholder(fields)
                    + " " + fieldOr(fields, "right");
        }
        if (type == BinaryExpr.class) {
            return "(" + fieldOr(fields, "left") + " " + operatorOrPlaceholder(fields, "operator")
                    + " " + fieldOr(fields, "right") + ")";
        }
        if (type == UnaryExpr.class) {
            return operatorOrPlaceholder(fields, "operator") + fieldOr(fields, "expression");
        }
        if (type == IntLiteral.class || type == DoubleLiteral.class || type == BooleanLiteral.class) {
            return fieldOr(fields, "value");
        }
        if (type == IdentifierExpr.class) {
            return fieldOr(fields, "name");
        }
        if (type == FunctionCallExpr.class) {
            return fieldOr(fields, "name") + "(" + fieldOrList(fields, "arguments") + ")";
        }
        if (type == CastExpr.class) {
            return "(" + fieldOr(fields, "targetType") + ") " + fieldOr(fields, "expression");
        }
        if (type == Variable.class) {
            String indexes = fields.containsKey("indexes")
                    ? "[" + format(fields.get("indexes")) + "]"
                    : "[_]";
            return fieldOr(fields, "name") + indexes;
        }
        if (type == LinearFactor.class) {
            String coefficient = fields.containsKey("coefficient")
                    ? format(fields.get("coefficient"))
                    : null;
            String variable = fieldOr(fields, "variable");
            if (coefficient == null || "1".equals(coefficient)) {
                return variable;
            }
            return coefficient + " * " + variable;
        }
        if (type == Sum.class) {
            return "sum" + formatSetOfHeader(fields.get("terms")) + " "
                    + formatSetOfElement(fields.get("terms"));
        }
        if (type == Set_of.class) {
            return formatSetOfHeader(nodePattern) + " " + formatSetOfElement(nodePattern);
        }
        if (type == Index.class) {
            return fieldOr(fields, "variable") + " in "
                    + fieldOr(fields, "lowerBound") + ".." + fieldOr(fields, "upperBound");
        }
        if (type == OneSideBound.class) {
            return fieldOr(fields, "variable") + " " + relOperatorOrPlaceholder(fields)
                    + " " + fieldOr(fields, "bound");
        }
        if (type == TwoSideBound.class) {
            return fieldOr(fields, "lower") + " <= " + fieldOr(fields, "variable")
                    + " <= " + fieldOr(fields, "upper");
        }
        if (type == VarDeclaration.class) {
            String initializer = fields.containsKey("initializer")
                    ? " := " + format(fields.get("initializer"))
                    : "";
            return "param " + fieldOr(fields, "name") + initializer + ";";
        }
        if (type == IntegerType.class) {
            return "integer";
        }
        if (type == DoubleType.class) {
            return "double";
        }
        if (type == BooleanType.class) {
            return "boolean";
        }
        if (type == StringType.class) {
            return "string";
        }

        return genericNode(type, nodePattern.fields());
    }

    private static String formatSetOfHeader(Pattern setOfPattern) {
        if (!(setOfPattern instanceof NodePattern setOf) || setOf.nodeType() != Set_of.class) {
            return "{_}";
        }
        Map<String, Pattern> fields = fieldsByName(setOf);
        String indexes = fields.containsKey("indexes") ? format(fields.get("indexes")) : "_";
        if (fields.containsKey("filter")) {
            return "{" + indexes + " : " + format(fields.get("filter")) + "}";
        }
        return "{" + indexes + "}";
    }

    private static String formatSetOfElement(Pattern setOfPattern) {
        if (!(setOfPattern instanceof NodePattern setOf) || setOf.nodeType() != Set_of.class) {
            return "_";
        }
        Map<String, Pattern> fields = fieldsByName(setOf);
        return fields.containsKey("element") ? format(fields.get("element"))
                : fields.containsKey("constraint") ? format(fields.get("constraint"))
                : fields.containsKey("variable") ? format(fields.get("variable"))
                : "_";
    }

    private static String genericNode(Class<?> type, List<FieldPattern> fields) {
        StringBuilder text = new StringBuilder(type.getSimpleName()).append("(");
        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) {
                text.append(", ");
            }
            FieldPattern field = fields.get(i);
            text.append(field.name()).append("=").append(format(field.pattern()));
        }
        return text.append(")").toString();
    }

    private static Map<String, Pattern> fieldsByName(NodePattern nodePattern) {
        Map<String, Pattern> fields = new java.util.LinkedHashMap<>();
        for (FieldPattern field : nodePattern.fields()) {
            fields.put(field.name(), field.pattern());
        }
        return fields;
    }

    private static String fieldOr(Map<String, Pattern> fields, String name) {
        return fields.containsKey(name) ? format(fields.get(name)) : "_";
    }

    private static String fieldOrList(Map<String, Pattern> fields, String name) {
        Pattern pattern = fields.get(name);
        return pattern == null ? "_" : format(pattern);
    }

    private static String relOperatorOrPlaceholder(Map<String, Pattern> fields) {
        Pattern op = fields.get("op");
        if (op instanceof ValuePattern value && value.expected() instanceof lsi.ast.constraints.RelOperator relOp) {
            return relOperatorSymbol(relOp);
        }
        return op == null ? "_" : format(op);
    }

    private static String operatorOrPlaceholder(Map<String, Pattern> fields, String name) {
        Pattern op = fields.get(name);
        if (op instanceof ValuePattern value) {
            if (value.expected() instanceof BinaryOperator binaryOperator) {
                return binaryOperatorSymbol(binaryOperator);
            }
            if (value.expected() instanceof UnaryOperator unaryOperator) {
                return unaryOperatorSymbol(unaryOperator);
            }
        }
        return op == null ? "_" : format(op);
    }

    private static String formatValue(Object expected) {
        if (expected instanceof lsi.ast.constraints.RelOperator relOp) {
            return relOperatorSymbol(relOp);
        }
        if (expected instanceof BinaryOperator binaryOperator) {
            return binaryOperatorSymbol(binaryOperator);
        }
        if (expected instanceof UnaryOperator unaryOperator) {
            return unaryOperatorSymbol(unaryOperator);
        }
        if (expected instanceof String stringValue) {
            return stringValue;
        }
        return Objects.toString(expected);
    }

    private static String relOperatorSymbol(lsi.ast.constraints.RelOperator operator) {
        return switch (operator) {
            case LT -> "<";
            case LE -> "<=";
            case GT -> ">";
            case GE -> ">=";
            case EQ -> "=";
        };
    }

    private static String binaryOperatorSymbol(BinaryOperator operator) {
        return switch (operator) {
            case ADD -> "+";
            case SUB -> "-";
            case MUL -> "*";
            case DIV -> "/";
            case MOD -> "mod";
            case LT -> "<";
            case LE -> "<=";
            case GT -> ">";
            case GE -> ">=";
            case EQ -> "=";
            case NE -> "!=";
            case AND -> "&&";
            case OR -> "||";
        };
    }

    private static String unaryOperatorSymbol(UnaryOperator operator) {
        return switch (operator) {
            case PLUS -> "+";
            case MINUS -> "-";
            case NOT -> "!";
        };
    }
}
