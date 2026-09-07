package lsi.main;

import java.util.List;

import lsi.ast.AST;
import lsi.ast.constraints.RelOperator;
import lsi.ast.constraints.RelationalConstraint;
import lsi.ast.declarations.VarDeclaration;
import lsi.ast.expressions.DoubleLiteral;
import lsi.ast.expressions.Expression;
import lsi.ast.expressions.IntLiteral;
import lsi.ast.linear.LinearExpr;
import lsi.ast.matching.PatternAST;
import lsi.ast.matching.PatternASTMatcher;
import lsi.ast.matching.PatternASTMatcher.Match;
import lsi.ast.matching.PatternASTPrinter;
import lsi.ast.types.IntegerType;
import lsi.ast.variables.Variable;

public final class PatternASTExamples {

    private static final PatternASTMatcher MATCHER = new PatternASTMatcher();

    private PatternASTExamples() {
    }

    public static void run(AST ast) {
        runCategoryExamples(ast);
        runInitializedIntegerDeclarationExample(ast);
        runGreaterEqualZeroExample(ast);
        runIndexedVariableExample(ast);
        runLinearExpressionVariableExample(ast);
    }

    private static void runCategoryExamples(AST ast) {
        printCount("Declaraciones", ast, PatternAST.node(lsi.ast.declarations.Declaration.class));
        printCount("Expresiones", ast, PatternAST.node(Expression.class));
        printCount("Restricciones", ast, PatternAST.node(lsi.ast.constraints.Constraint.class));
        printCount("Cotas", ast, PatternAST.node(lsi.ast.bounds.Bound.class));
    }

    private static void runInitializedIntegerDeclarationExample(AST ast) {
        PatternAST.VariablePattern<IntLiteral> initializerVar =
                PatternAST.variable("initializer", IntLiteral.class);
        PatternAST.Pattern pattern = PatternAST.node(VarDeclaration.class,
                PatternAST.field("type", PatternAST.node(IntegerType.class)),
                PatternAST.field("initializer", initializerVar));

        System.out.println("[PATTERN AST] " + PatternASTPrinter.describe(pattern));

        List<Match> matches = MATCHER.findAll(ast, pattern);
        System.out.println("[PATTERN AST] Declaraciones Integer inicializadas: "
                + matches.size());
        for (Match match : matches) {
            printBindings(match, pattern);
        }
    }

    private static void runGreaterEqualZeroExample(AST ast) {
        PatternAST.VariablePattern<LinearExpr> leftVar =
                PatternAST.variable("left", LinearExpr.class);
        PatternAST.Pattern zero = PatternAST.anyOf(
                PatternAST.node(IntLiteral.class,
                        PatternAST.field("value", PatternAST.value(0))),
                PatternAST.node(DoubleLiteral.class,
                        PatternAST.field("value", PatternAST.value(0.0))));
        PatternAST.Pattern pattern = PatternAST.node(RelationalConstraint.class,
                PatternAST.field("op", PatternAST.value(RelOperator.GE)),
                PatternAST.field("left", leftVar),
                PatternAST.field("right", zero));

        System.out.println("[PATTERN AST] " + PatternASTPrinter.describe(pattern));

        List<Match> matches = MATCHER.findAll(ast, pattern);
        System.out.println("[PATTERN AST] Restricciones relacionales >= 0: "
                + matches.size());
        for (Match match : matches) {
            printBindings(match, pattern);
        }
    }

    private static void runIndexedVariableExample(AST ast) {
        PatternAST.Pattern indexFour = PatternAST.node(IntLiteral.class,
                PatternAST.field("value", PatternAST.value(4)));
        // La variable liga el subárbol "Variable" completo (nombre, índices,
        // ...), pero solo cuando ese subárbol coincide con el patrón interno:
        // nombre "x" e indexado por el literal 4 en alguna posición.
        PatternAST.VariablePattern<Variable> xAtFourVar = PatternAST.variable(
                "xAtFour", Variable.class,
                PatternAST.node(Variable.class,
                        PatternAST.field("name", PatternAST.value("x")),
                        PatternAST.field("indexes", PatternAST.anyElement(indexFour))));

        System.out.println("[PATTERN AST] " + PatternASTPrinter.describe(xAtFourVar));

        List<Match> matches = MATCHER.findAll(ast, xAtFourVar);
        System.out.println("[PATTERN AST] Variables x[4]: " + matches.size());
        for (Match match : matches) {
            printBindings(match, xAtFourVar);
        }
    }

    private static void runLinearExpressionVariableExample(AST ast) {
        // Captura el subárbol LinearExpr completo del lado izquierdo, pero
        // solo si contiene al menos un término (patrón interno no trivial).
        PatternAST.Pattern nonEmpty = PatternAST.predicate(value ->
                value instanceof LinearExpr expr && !expr.terms().isEmpty());
        PatternAST.LinearExprVariable expressionVar =
                PatternAST.linearExprVariable("expression", nonEmpty);
        PatternAST.Pattern pattern = PatternAST.node(RelationalConstraint.class,
                PatternAST.field("left", expressionVar),
                PatternAST.field("op", PatternAST.value(RelOperator.GE)),
                PatternAST.field("right", PatternAST.any()));

        System.out.println("[PATTERN AST] " + PatternASTPrinter.describe(pattern));

        List<Match> matches = MATCHER.findAll(ast, pattern);
        System.out.println("[PATTERN AST] Expresiones lineales capturadas: "
                + matches.size());
        for (Match match : matches) {
            printBindings(match, pattern);
        }
    }

    private static void printCount(String description, AST ast,
            PatternAST.Pattern pattern) {
        System.out.println("[PATTERN AST] " + description + ": "
                + MATCHER.findAll(ast, pattern).size());
    }

    /**
     * Imprime, para un resultado de matching, el valor ligado a cada
     * variable declarada por el patrón (obtenidas con
     * {@link PatternAST#variablesOf(PatternAST.Pattern)}), con el mismo
     * nombre con el que aparecen en el patrón (prefijo {@code ?}).
     */
    private static void printBindings(Match match, PatternAST.Pattern pattern) {
        for (PatternAST.VariableDeclaration variable : PatternAST.variablesOf(pattern).values()) {
            Object value = match.bindings().get(variable.name());
            System.out.println("  ?" + variable.name() + " = " + PatternASTPrinter.printValue(value));
        }
    }
}
