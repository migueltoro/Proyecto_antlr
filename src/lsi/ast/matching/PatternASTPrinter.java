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
import lsi.ast.expressions.Expression;
import lsi.ast.expressions.FunctionCallExpr;
import lsi.ast.expressions.IdentifierExpr;
import lsi.ast.expressions.IntLiteral;
import lsi.ast.expressions.UnaryExpr;
import lsi.ast.expressions.UnaryOperator;
import lsi.ast.linear.LinearExpr;
import lsi.ast.linear.LinearFactor;
import lsi.ast.linear.LinearTerm;
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

    /**
     * Imprime en sintaxis AMPL un valor real del AST, típicamente un
     * subárbol capturado por una variable de patrón (por ejemplo, el
     * {@link LinearExpr} ligado a {@code ?expression} en un {@code Match}).
     *
     * <p>A diferencia de {@link #print(Pattern)}, que describe un patrón,
     * este método describe un nodo AST concreto ya instanciado, sin partes
     * comodín ni variables: todo el subárbol es real.</p>
     */
    public static String printValue(Object value) {
        return formatNodeValue(value);
    }

    private static String formatNodeValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof LinearExpr linearExpr) {
            return formatLinearTerms(linearExpr.terms());
        }
        if (value instanceof LinearTerm term) {
            return formatLinearTerm(term);
        }
        if (value instanceof Variable variable) {
            return formatVariableValue(variable);
        }
        if (value instanceof Set_of<?> setOf) {
            return formatSetOfValue(setOf);
        }
        if (value instanceof Index index) {
            return index.variable() + " in " + formatNodeValue(index.lowerBound())
                    + ".." + formatNodeValue(index.upperBound());
        }
        if (value instanceof RelationalConstraint constraint) {
            return formatNodeValue(constraint.left()) + " " + relOperatorSymbol(constraint.op())
                    + " " + formatNodeValue(constraint.right());
        }
        if (value instanceof OneSideBound bound) {
            return formatNodeValue(bound.variable()) + " " + relOperatorSymbol(bound.operator())
                    + " " + formatNodeValue(bound.expression());
        }
        if (value instanceof TwoSideBound bound) {
            return formatNodeValue(bound.lower()) + " <= " + formatNodeValue(bound.variable())
                    + " <= " + formatNodeValue(bound.upper());
        }
        if (value instanceof VarDeclaration declaration) {
            String initializer = declaration.initializer() == null ? ""
                    : " := " + formatNodeValue(declaration.initializer());
            return "param " + declaration.name() + initializer + ";";
        }
        if (value instanceof Expression expression) {
            return formatExpressionValue(expression);
        }
        if (value instanceof IntegerType) {
            return "integer";
        }
        if (value instanceof DoubleType) {
            return "double";
        }
        if (value instanceof BooleanType) {
            return "boolean";
        }
        if (value instanceof StringType) {
            return "string";
        }
        if (value instanceof List<?> list) {
            StringBuilder text = new StringBuilder("[");
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    text.append(", ");
                }
                text.append(formatNodeValue(list.get(i)));
            }
            return text.append("]").toString();
        }
        return Objects.toString(value);
    }

    private static String formatLinearTerms(List<LinearTerm> terms) {
        if (terms == null || terms.isEmpty()) {
            return "0";
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            if (i > 0) {
                text.append(" + ");
            }
            text.append(formatLinearTerm(terms.get(i)));
        }
        return text.toString();
    }

    private static String formatLinearTerm(LinearTerm term) {
        if (term instanceof LinearFactor factor) {
            String coefficient = formatNodeValue(factor.coefficient());
            String variable = formatVariableValue(factor.variable());
            return isOne(factor.coefficient()) ? variable : coefficient + " * " + variable;
        }
        if (term instanceof Sum sum) {
            Set_of<LinearFactor> terms = sum.terms();
            return "sum" + formatIndexesAndFilter(terms.indexes(), terms.filter())
                    + " " + formatLinearTerm(terms.element());
        }
        return Objects.toString(term);
    }

    private static boolean isOne(Expression coefficient) {
        return (coefficient instanceof IntLiteral il && il.value() == 1)
                || (coefficient instanceof DoubleLiteral dl && dl.value() == 1.0);
    }

    private static String formatVariableValue(Variable variable) {
        if (variable == null) {
            return "null";
        }
        List<Expression> indexes = variable.indexes();
        if (indexes == null || indexes.isEmpty()) {
            return variable.name();
        }
        StringBuilder text = new StringBuilder(variable.name()).append('[');
        for (int i = 0; i < indexes.size(); i++) {
            if (i > 0) {
                text.append(", ");
            }
            text.append(formatNodeValue(indexes.get(i)));
        }
        return text.append(']').toString();
    }

    private static String formatSetOfValue(Set_of<?> setOf) {
        String element = formatNodeValue(setOf.element());
        String header = formatIndexesAndFilter(setOf.indexes(), setOf.filter());
        return header + " " + element;
    }

    private static String formatIndexesAndFilter(List<Index> indexes, Expression filter) {
        StringBuilder text = new StringBuilder("{");
        if (indexes != null) {
            for (int i = 0; i < indexes.size(); i++) {
                if (i > 0) {
                    text.append(", ");
                }
                text.append(formatNodeValue(indexes.get(i)));
            }
        }
        if (filter != null) {
            text.append(" : ").append(formatNodeValue(filter));
        }
        return text.append("}").toString();
    }

    private static String formatExpressionValue(Expression expression) {
        if (expression instanceof BinaryExpr binary) {
            return "(" + formatNodeValue(binary.left()) + " " + binaryOperatorSymbol(binary.operator())
                    + " " + formatNodeValue(binary.right()) + ")";
        }
        if (expression instanceof UnaryExpr unary) {
            return unaryOperatorSymbol(unary.operator()) + formatNodeValue(unary.expression());
        }
        if (expression instanceof IdentifierExpr identifier) {
            return identifier.name();
        }
        if (expression instanceof IntLiteral intLiteral) {
            return Integer.toString(intLiteral.value());
        }
        if (expression instanceof DoubleLiteral doubleLiteral) {
            return Double.toString(doubleLiteral.value());
        }
        if (expression instanceof BooleanLiteral booleanLiteral) {
            return Boolean.toString(booleanLiteral.value());
        }
        if (expression instanceof CastExpr cast) {
            return "(" + formatNodeValue(cast.targetType()) + ") " + formatNodeValue(cast.expression());
        }
        if (expression instanceof FunctionCallExpr call) {
            return call.name() + "(" + formatNodeValue(call.arguments()) + ")";
        }
        return Objects.toString(expression);
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
