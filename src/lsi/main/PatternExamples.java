package lsi.main;

import java.util.List;

import lsi.ast.AST;
import lsi.ast.common.Set_of;
import lsi.ast.constraints.RelOperator;
import lsi.ast.constraints.RelationalConstraint;
import lsi.ast.expressions.DoubleLiteral;
import lsi.ast.expressions.IdentifierExpr;
import lsi.ast.expressions.IntLiteral;
import lsi.ast.linear.LinearFactor;
import lsi.ast.linear.Sum;
import lsi.ast.declarations.VarDeclaration;
import lsi.ast.bounds.TwoSideBound;
import lsi.ast.pattern.ASTPattern;
import lsi.ast.pattern.ASTPattern.Match;
import lsi.ast.pattern.ASTPattern.Pattern;

public final class PatternExamples {

    private PatternExamples() {
    }

    public static void run(AST ast) {
        runVarDeclarationExamples(ast);
        runRelationalGreaterEqualZeroExamples(ast);
        runIndexedBoundExamples(ast);
        runAggregatedSumExamples(ast);
        runSpecificLinearFactorExample(ast);
    }

    private static void runVarDeclarationExamples(AST ast) {
        Pattern constantN = ASTPattern.type(VarDeclaration.class,
                ASTPattern.field("name", ASTPattern.value("N")),
                ASTPattern.field("initializer", ASTPattern.type(IntLiteral.class,
                        ASTPattern.field("value", ASTPattern.value(10)))));

        List<Match> declarations = ASTPattern.findAll(ast, constantN);
        System.out.println("[PATTERN] VarDeclaration N = 10 encontrados: " + declarations.size());
    }

    private static void runRelationalGreaterEqualZeroExamples(AST ast) {
        Pattern geZeroOnX = ASTPattern.type(RelationalConstraint.class,
                ASTPattern.field("op", ASTPattern.value(RelOperator.GE)),
                ASTPattern.field("right", ASTPattern.predicate(value ->
                        (value instanceof IntLiteral intLiteral && intLiteral.value() == 0)
                                || (value instanceof DoubleLiteral doubleLiteral && doubleLiteral.value() == 0.0))),
                ASTPattern.field("left", ASTPattern.capture("leftExpr")));

        List<Match> constraints = ASTPattern.findAll(ast, geZeroOnX);
        System.out.println("[PATTERN] RelationalConstraint GE 0 encontrados: " + constraints.size());
        for (Match match : constraints) {
            System.out.println("  left = " + match.bindings().get("leftExpr"));
        }
    }

    private static void runIndexedBoundExamples(AST ast) {
        Pattern indexedTwoSideBound = ASTPattern.type(Set_of.class,
                ASTPattern.field("element", ASTPattern.type(TwoSideBound.class,
                        ASTPattern.field("lower", ASTPattern.type(IntLiteral.class,
                                ASTPattern.field("value", ASTPattern.value(0)))),
                        ASTPattern.field("upper", ASTPattern.type(IntLiteral.class,
                                ASTPattern.field("value", ASTPattern.value(10)))))),
                ASTPattern.field("indexes", ASTPattern.anyElement(ASTPattern.type(lsi.ast.common.Index.class,
                        ASTPattern.field("variable", ASTPattern.value("i")),
                        ASTPattern.field("lowerBound", ASTPattern.type(IntLiteral.class,
                                ASTPattern.field("value", ASTPattern.value(1)))),
                        ASTPattern.field("upperBound", ASTPattern.type(IntLiteral.class,
                                ASTPattern.field("value", ASTPattern.value(10))))))));

        List<Match> sets = ASTPattern.findAll(ast, indexedTwoSideBound);
        System.out.println("[PATTERN] Bounds 0 <= x[i] <= 10 con i in 1..10: " + sets.size());
    }

    private static void runAggregatedSumExamples(AST ast) {
        Pattern indexedI = ASTPattern.type(IdentifierExpr.class,
                ASTPattern.field("name", ASTPattern.value("i")));
        Pattern variableXIndexedByI = ASTPattern.type(lsi.ast.variables.Variable.class,
                ASTPattern.field("name", ASTPattern.value("x")),
                ASTPattern.field("indexes", ASTPattern.anyElement(indexedI)));
        Pattern factorWithVariableX = ASTPattern.type(LinearFactor.class,
                ASTPattern.field("variable", variableXIndexedByI));
        Pattern setOfFactor = ASTPattern.type(Set_of.class,
                ASTPattern.field("element", factorWithVariableX));
        Pattern sumOverI = ASTPattern.type(Sum.class,
                ASTPattern.field("terms", setOfFactor));

        List<Match> sets = ASTPattern.findAll(ast, sumOverI);
        System.out.println("[PATTERN] Sum(x[i], i in 1..N) encontrados: " + sets.size());
        for (Match match : sets) {
            System.out.println("  nodo = " + match.node().getClass().getSimpleName());
        }
    }

    private static void runSpecificLinearFactorExample(AST ast) {
        Pattern xIndexedByFour = ASTPattern.type(lsi.ast.variables.Variable.class,
                ASTPattern.field("name", ASTPattern.value("x")),
                ASTPattern.field("indexes", ASTPattern.anyElement(ASTPattern.type(IntLiteral.class,
                        ASTPattern.field("value", ASTPattern.value(4))))));

        Pattern factor2x4 = ASTPattern.type(LinearFactor.class,
                ASTPattern.field("coefficient", ASTPattern.type(DoubleLiteral.class,
                        ASTPattern.field("value", ASTPattern.value(2.0)))),
                ASTPattern.field("variable", xIndexedByFour));

        List<Match> factors = ASTPattern.findAll(ast, factor2x4);
        System.out.println("[PATTERN] LinearFactor 2.0 * x[4] encontrados: " + factors.size());
    }
}
