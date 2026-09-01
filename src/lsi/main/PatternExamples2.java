package lsi.main;

import java.util.List;

import lsi.ast.AST;
import lsi.ast.constraints.RelOperator;
import lsi.ast.expressions.DoubleLiteral;
//import lsi.ast.expressions.IdentifierExpr;
import lsi.ast.expressions.IntLiteral;
import lsi.ast.pattern.ASTPattern;
import lsi.ast.pattern.ASTPattern.Match;
import lsi.ast.pattern.ASTPattern.Pattern;
import lsi.ast.pattern.ASTPatternType;
import lsi.ast.variables.Variable;

public final class PatternExamples2 {

    private PatternExamples2() {
    }

    public static void run(AST ast) {
        runCategoryExamples(ast);
        runInitializedIntegerDeclarationExample(ast);
        runGreaterEqualZeroExample(ast);
        runIndexedVariableExample(ast);
    }

    private static void runCategoryExamples(AST ast) {
        printCount("Declaraciones", ast, ASTPatternType.DECLARATION);
        printCount("Expresiones", ast, ASTPatternType.EXPRESSION);
        printCount("Restricciones", ast, ASTPatternType.CONSTRAINT);
        printCount("Cotas", ast, ASTPatternType.BOUND);
    }

    private static void runInitializedIntegerDeclarationExample(AST ast) {
        Pattern integerDeclaration = ASTPattern.type(lsi.ast.declarations.VarDeclaration.class,
                ASTPattern.field("type", ASTPatternType.INTEGER_TYPE),
                ASTPattern.field("initializer", ASTPattern.capture("initializer", ASTPatternType.INT_LITERAL)));

        List<Match> matches = ASTPattern.findAll(ast, integerDeclaration);
        System.out.println("[PATTERN 2] Declaraciones Integer inicializadas: " + matches.size());
        for (Match match : matches) {
            System.out.println("  initializer = " + match.bindings().get("initializer"));
        }
    }

    private static void runGreaterEqualZeroExample(AST ast) {
        Pattern greaterEqualZero = ASTPattern.type(lsi.ast.constraints.RelationalConstraint.class,
                ASTPattern.field("op", ASTPattern.value(RelOperator.GE)),
                ASTPattern.field("right", ASTPattern.anyOf(
                        ASTPattern.type(IntLiteral.class, ASTPattern.field("value", ASTPattern.value(0))),
                        ASTPattern.type(DoubleLiteral.class, ASTPattern.field("value", ASTPattern.value(0.0))))),
                ASTPattern.field("left", ASTPattern.capture("left")));

        List<Match> matches = ASTPattern.findAll(ast, greaterEqualZero);
        System.out.println("[PATTERN 2] Restricciones relacionales >= 0: " + matches.size());
        for (Match match : matches) {
            System.out.println("  left = " + match.bindings().get("left"));
        }
    }

    private static void runIndexedVariableExample(AST ast) {
        Pattern indexFour = ASTPattern.type(IntLiteral.class,
                ASTPattern.field("value", ASTPattern.value(4)));
        Pattern variableXAtFour = ASTPattern.type(Variable.class,
                ASTPattern.field("name", ASTPattern.value("x")),
                ASTPattern.field("indexes", ASTPattern.anyElement(indexFour)));

        List<Match> matches = ASTPattern.findAll(ast, variableXAtFour);
        System.out.println("[PATTERN 2] Variables x[4]: " + matches.size());
    }

    private static void printCount(String description, AST ast, Pattern pattern) {
        System.out.println("[PATTERN 2] " + description + ": " + ASTPattern.findAll(ast, pattern).size());
    }
}
