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
        PatternAST.Pattern pattern = PatternAST.node(VarDeclaration.class,
                PatternAST.field("type", PatternAST.node(IntegerType.class)),
                PatternAST.field("initializer",
                        PatternAST.variable("initializer", IntLiteral.class)));

        List<Match> matches = MATCHER.findAll(ast, pattern);
        System.out.println("[PATTERN AST] Declaraciones Integer inicializadas: "
                + matches.size());
        for (Match match : matches) {
            System.out.println("  initializer = "
                    + match.bindings().get("initializer"));
        }
    }

    private static void runGreaterEqualZeroExample(AST ast) {
        PatternAST.Pattern zero = PatternAST.anyOf(
                PatternAST.node(IntLiteral.class,
                        PatternAST.field("value", PatternAST.value(0))),
                PatternAST.node(DoubleLiteral.class,
                        PatternAST.field("value", PatternAST.value(0.0))));
        PatternAST.Pattern pattern = PatternAST.node(RelationalConstraint.class,
                PatternAST.field("op", PatternAST.value(RelOperator.GE)),
                PatternAST.field("left",
                        PatternAST.variable("left", LinearExpr.class)),
                PatternAST.field("right", zero));

        List<Match> matches = MATCHER.findAll(ast, pattern);
        System.out.println("[PATTERN AST] Restricciones relacionales >= 0: "
                + matches.size());
        for (Match match : matches) {
            System.out.println("  left = " + match.bindings().get("left"));
        }
    }

    private static void runIndexedVariableExample(AST ast) {
        PatternAST.Pattern indexFour = PatternAST.node(IntLiteral.class,
                PatternAST.field("value", PatternAST.value(4)));
        PatternAST.Pattern pattern = PatternAST.node(Variable.class,
                PatternAST.field("name", PatternAST.value("x")),
                PatternAST.field("indexes", PatternAST.anyElement(indexFour)));

        System.out.println("[PATTERN AST] Variables x[4]: "
                + MATCHER.findAll(ast, pattern).size());
    }

    private static void runLinearExpressionVariableExample(AST ast) {
        PatternAST.Pattern pattern = PatternAST.node(RelationalConstraint.class,
                PatternAST.field("left",
                        PatternAST.linearExprVariable("expression")),
                PatternAST.field("op", PatternAST.value(RelOperator.GE)),
                PatternAST.field("right", PatternAST.any()));

        List<Match> matches = MATCHER.findAll(ast, pattern);
        System.out.println("[PATTERN AST] Expresiones lineales capturadas: "
                + matches.size());
        for (Match match : matches) {
            System.out.println("  expression = "
                    + match.bindings().get("expression"));
        }
    }

    private static void printCount(String description, AST ast,
            PatternAST.Pattern pattern) {
        System.out.println("[PATTERN AST] " + description + ": "
                + MATCHER.findAll(ast, pattern).size());
    }
}
