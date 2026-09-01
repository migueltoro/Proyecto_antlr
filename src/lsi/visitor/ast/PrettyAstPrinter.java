package lsi.visitor.ast;

import lsi.ast.AST;
//import lsi.ast.Objective;
import lsi.ast.common.Index;
import lsi.ast.common.Set_of;
import lsi.ast.declarations.Declaration;
import lsi.ast.declarations.FunctionDeclaration;
import lsi.ast.declarations.VarDeclaration;
import lsi.ast.expressions.*;
import lsi.ast.linear.*;
import lsi.ast.types.*;
import lsi.ast.variables.Variable;
import lsi.ast.bounds.*;
import lsi.ast.constraints.*;

import java.util.List;
import java.util.Objects;

/**
 * 
 * PrettyAstPrinter: imprime una representación legible e indentada del AST
 * 
 * concreto definido en lsi.ast.*.
 * 
 * Diseñado para la estructura de clases/records de tu proyecto.
 */
public final class PrettyAstPrinter {
    private final StringBuilder sb = new StringBuilder();
    private final int indentStep = 2;
    private int indent = 0;
    public final int maxTextLen;

    public PrettyAstPrinter() {
        this(120);
    }

    public PrettyAstPrinter(int maxTextLen) {
        this.maxTextLen = maxTextLen;
    }

    public String print(AST ast) {
        sb.setLength(0);
        visitAST(ast);
        return sb.toString();
    }

    public static String prettyPrint(AST ast) {
        return new PrettyAstPrinter().print(ast);
    }

    /* ---------- Visitors for top-level structures ---------- */

    private void visitAST(AST ast) {
        appendLine("AST");
        indent += indentStep;

        appendLine("objective: " + (ast.objective() == null ? "null" : ast.objective()));
        appendLine("objectiveExpr:");
        indent += indentStep;
        visitLinearExpr(ast.objectiveExpr());
        indent -= indentStep;

        printList("declarations", ast.declarations(), this::visitDeclaration);
        printList("constraints", ast.constraints(), this::visitSetOfConstraint);
        printList("bounds", ast.bounds(), this::visitSetOfBound);
        printList("binaryVars", ast.binaryVars(), this::visitSetOfVariable);
        printList("integerVars", ast.integerVars(), this::visitSetOfVariable);
        printList("freeVars", ast.freeVars(), this::visitSetOfVariable);
        printList("semiContinuousVars", ast.semiContinuousVars(), this::visitSetOfVariable);

        indent -= indentStep;
    }

    /* ---------- Declarations ---------- */

    private void visitDeclaration(Declaration d) {
        if (d == null) {
            appendLine("Declaration: null");
            return;
        }
        if (d instanceof VarDeclaration v) {
            appendLine("VarDeclaration");
            indent += indentStep;
            appendLine("type: " + safeToString(v.type()));
            appendLine("name: " + v.name());
            appendLine("initializer: " + formatExpression(v.initializer()));
            indent -= indentStep;
            return;
        }
        if (d instanceof FunctionDeclaration f) {
            appendLine("FunctionDeclaration");
            indent += indentStep;
            appendLine("returnType: " + safeToString(f.returnType()));
            appendLine("name: " + f.name());
            printList("parameters", f.parameters(), p -> appendLine(safeToString(p)));
            indent -= indentStep;
            return;
        }
        // fallback
        appendLine("Declaration: " + safeToString(d));
    }

    /* ---------- Expressions ---------- */

    private void visitExpression(Expression e) {
        appendLine(formatExpression(e));
    }

    /* ---------- Linear expressions & terms ---------- */

    private void visitLinearExpr(LinearExpr le) {
        if (le == null) {
            appendLine("null");
            return;
        }
        appendLine("LinearExpr");
        indent += indentStep;
        printList("terms", le.terms(), this::visitLinearTerm);
        indent -= indentStep;
    }

    private void visitLinearTerm(LinearTerm t) {
        if (t == null) {
            appendLine("null");
            return;
        }
        if (t instanceof LinearFactor lf) {
            appendLine("LinearFactor");
            indent += indentStep;
            appendLine("coefficient: " + formatExpression(lf.coefficient()));
            appendLine("variable:");
            indent += indentStep;
            visitVariable(lf.variable());
            indent -= indentStep;
            indent -= indentStep;
            return;
        }
        if (t instanceof Sum sot) {
            appendLine("Sum");
            indent += indentStep;
            visitSetOf(sot.terms(), "terms");
            indent -= indentStep;
            return;
        }
        appendLine("LinearTerm: " + safeToString(t));
    }

    /* ---------- Variables ---------- */

    private void visitVariable(Variable v) {
        if (v == null) {
            appendLine("Variable: null");
            return;
        }
        appendLine("Variable: " + v.name());
        indent += indentStep;
        printList("indexes", v.indexes(), this::visitExpression);
        indent -= indentStep;
    }

    /* ---------- Set_of helper ---------- */

    private <T> void visitSetOf(Set_of<T> s, String labelForElement) {
        if (s == null) {
            appendLine("Set_of: null");
            return;
        }
        appendLine("Set_of");
        indent += indentStep;
        appendLine(labelForElement + ":");
        indent += indentStep;
        visitPossibleElement(s.element());
        indent -= indentStep;

        printList("indexes", s.indexes(), this::visitIndex);
        appendLine("filter: " + formatExpression(s.filter()));

        indent -= indentStep;
    }

    private void visitSetOfConstraint(Set_of<?> s) {
        visitSetOf(s, "constraint");
    }

    private void visitSetOfBound(Set_of<?> s) {
        visitSetOf(s, "bound");
    }

    private void visitSetOfVariable(Set_of<Variable> s) {
        visitSetOf(s, "variable");
    }

    private void visitPossibleElement(Object elem) {
        if (elem == null) {
            appendLine("null");
            return;
        }
        if (elem instanceof LinearFactor lf) {
            visitLinearFactor(lf);
            return;
        }
        if (elem instanceof lsi.ast.constraints.Constraint c) {
            visitConstraint(c);
            return;
        }
        if (elem instanceof lsi.ast.bounds.Bound b) {
            visitBound(b);
            return;
        }
        if (elem instanceof Variable v) {
            visitVariable(v);
            return;
        }
        // generic fallback:
        appendLine("element: " + safeToString(elem));
    }

    private void visitLinearFactor(LinearFactor lf) {
        appendLine("LinearFactor");
        indent += indentStep;
        appendLine("coefficient: " + formatExpression(lf.coefficient()));
        appendLine("variable:");
        indent += indentStep;
        visitVariable(lf.variable());
        indent -= indentStep;
        indent -= indentStep;
    }

    private void visitIndex(Index idx) {
        if (idx == null) {
            appendLine("Index: null");
            return;
        }
        appendLine("Index: " + idx.variable());
        indent += indentStep;
        appendLine("lower: " + formatExpression(idx.lowerBound()));
        appendLine("upper: " + formatExpression(idx.upperBound()));
        indent -= indentStep;
    }

    /* ---------- Bounds ---------- */

    private void visitBound(Bound b) {
        if (b == null) {
            appendLine("Bound: null");
            return;
        }
        if (b instanceof OneSideBound osb) {
            appendLine("OneSideBound (" + osb.operator() + ")");
            indent += indentStep;
            appendLine("variable:");
            indent += indentStep;
            visitVariable(osb.variable());
            indent -= indentStep;
            appendLine("expression: " + formatExpression(osb.expression()));
            indent -= indentStep;
            return;
        }
        if (b instanceof TwoSideBound tsb) {
            appendLine("TwoSideBound");
            indent += indentStep;
            appendLine("lower: " + formatExpression(tsb.lower()));
            appendLine("variable:");
            indent += indentStep;
            visitVariable(tsb.variable());
            indent -= indentStep;
            appendLine("upper: " + formatExpression(tsb.upper()));
            indent -= indentStep;
            return;
        }
        appendLine("Bound: " + safeToString(b));
    }

    /* ---------- Constraints ---------- */

    private void visitConstraint(Constraint c) {
        if (c == null) {
            appendLine("Constraint: null");
            return;
        }
        if (c instanceof RelationalConstraint rc) {
            appendLine("RelationalConstraint (" + rc.op() + ")");
            indent += indentStep;
            appendLine("left:");
            indent += indentStep;
            visitLinearExpr(rc.left());
            indent -= indentStep;
            appendLine("right: " + formatExpression(rc.right()));
            indent -= indentStep;
            return;
        }
        // For many constraint types fall back to names + fields if needed
        appendLine("Constraint: " + safeToString(c));
    }

    /* ---------- Utility helpers ---------- */

    private <T> void printList(String label, List<T> list, java.util.function.Consumer<T> consumer) {
        if (list == null) {
            appendLine(label + ": null");
            return;
        }
        appendLine(label + ": size=" + list.size());
        indent += indentStep;
        int i = 0;
        for (T it : list) {
            appendLine("[" + (i++) + "]");
            indent += indentStep;
            consumer.accept(it);
            indent -= indentStep;
        }
        indent -= indentStep;
    }

    private void appendLine(String line) {
        for (int i = 0; i < indent; i++)
            sb.append(' ');
        sb.append(line).append('\n');
    }

    private static String safeToString(Object o) {
        if (o == null)
            return "null";
        return Objects.toString(o);
    }

    private String formatExpression(Expression e) {
        if (e == null) {
            return "null";
        }
        if (e instanceof BinaryExpr be) {
            return "(" + formatExpression(be.left()) + " "
                    + formatBinaryOperator(be.operator()) + " "
                    + formatExpression(be.right()) + ")";
        }
        if (e instanceof UnaryExpr ue) {
            return formatUnaryOperator(ue.operator()) + parenthesizeIfNeeded(ue.expression());
        }
        if (e instanceof IdentifierExpr id) {
            return id.name();
        }
        if (e instanceof IntLiteral il) {
            return Integer.toString(il.value());
        }
        if (e instanceof DoubleLiteral dl) {
            return Double.toString(dl.value());
        }
        if (e instanceof BooleanLiteral bl) {
            return Boolean.toString(bl.value());
        }
        if (e instanceof CastExpr ce) {
            return "(" + formatType(ce.targetType()) + ") " + parenthesizeIfNeeded(ce.expression());
        }
        if (e instanceof FunctionCallExpr fc) {
            return fc.name() + "(" + formatExpressions(fc.arguments()) + ")";
        }
        return safeToString(e);
    }

    private String formatExpressions(List<? extends Expression> expressions) {
        if (expressions == null || expressions.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(formatExpression(expressions.get(i)));
        }
        return result.toString();
    }

    private String parenthesizeIfNeeded(Expression e) {
        String formatted = formatExpression(e);
        if (e instanceof IdentifierExpr
                || e instanceof IntLiteral
                || e instanceof DoubleLiteral
                || e instanceof BooleanLiteral
                || e instanceof FunctionCallExpr) {
            return formatted;
        }
        return "(" + formatted + ")";
    }

    private String formatBinaryOperator(BinaryOperator operator) {
        if (operator == null) {
            return "?";
        }
        return switch (operator) {
            case ADD -> "+";
            case SUB -> "-";
            case MUL -> "*";
            case DIV -> "/";
            case MOD -> "%";
            case LT -> "<";
            case LE -> "<=";
            case GT -> ">";
            case GE -> ">=";
            case EQ -> "==";
            case NE -> "!=";
            case AND -> "&&";
            case OR -> "||";
        };
    }

    private String formatUnaryOperator(UnaryOperator operator) {
        if (operator == null) {
            return "?";
        }
        return switch (operator) {
            case PLUS -> "+";
            case MINUS -> "-";
            case NOT -> "!";
        };
    }

    private String formatType(Type type) {
        if (type == null) {
            return "null";
        }
        if (type instanceof IntegerType) {
            return "int";
        }
        if (type instanceof DoubleType) {
            return "double";
        }
        if (type instanceof BooleanType) {
            return "boolean";
        }
        if (type instanceof StringType) {
            return "string";
        }
        return safeToString(type);
    }

}
