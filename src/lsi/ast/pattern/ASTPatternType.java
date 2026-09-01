package lsi.ast.pattern;

import static lsi.ast.pattern.ASTPattern.anyOf;
import static lsi.ast.pattern.ASTPattern.type;

import lsi.ast.AST;
import lsi.ast.Objective;
import lsi.ast.bounds.OneSideBound;
import lsi.ast.bounds.TwoSideBound;
import lsi.ast.common.Index;
import lsi.ast.common.Set_of;
import lsi.ast.constraints.AbsConstraint;
import lsi.ast.constraints.AllDifferentConstraint;
import lsi.ast.constraints.AndBinaryConstraint;
import lsi.ast.constraints.DifferentValueConstraint;
import lsi.ast.constraints.EqualsConstraint;
import lsi.ast.constraints.ImplicationConstraint;
import lsi.ast.constraints.IndicatorConstraint;
import lsi.ast.constraints.MaxConstraint;
import lsi.ast.constraints.MembershipConstraint;
import lsi.ast.constraints.MinConstraint;
import lsi.ast.constraints.OrBinaryConstraint;
import lsi.ast.constraints.OrConstraint;
import lsi.ast.constraints.Pair;
import lsi.ast.constraints.PermutationConstraint;
import lsi.ast.constraints.PiecewiseLinearConstraint;
import lsi.ast.constraints.RelOperator;
import lsi.ast.constraints.RelationalConstraint;
import lsi.ast.declarations.FunctionDeclaration;
import lsi.ast.declarations.IndexDeclaration;
import lsi.ast.declarations.Parameter;
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
import lsi.ast.linear.LinearExpr;
import lsi.ast.linear.LinearFactor;
import lsi.ast.linear.Sum;
import lsi.ast.types.BooleanType;
import lsi.ast.types.DoubleType;
import lsi.ast.types.EType;
import lsi.ast.types.IntegerType;
import lsi.ast.types.StringType;
import lsi.ast.variables.Variable;
import lsi.ast.variables.VariableSet;

/**
 * Catálogo de patrones base para todos los tipos concretos del AST.
 *
 * <p>Los patrones de categorías (por ejemplo, {@link #EXPRESSION}) agrupan
 * todos sus subtipos posibles. Para restringir campos, combínelos con
 * {@link ASTPattern#allOf(ASTPattern.Pattern...)}.</p>
 */
public final class ASTPatternType {

    public static final ASTPattern.Pattern AST = type(AST.class);

    public static final ASTPattern.Pattern VAR_DECLARATION = type(VarDeclaration.class);
    public static final ASTPattern.Pattern FUNCTION_DECLARATION = type(FunctionDeclaration.class);
    public static final ASTPattern.Pattern INDEX_DECLARATION = type(IndexDeclaration.class);
    public static final ASTPattern.Pattern PARAMETER = type(Parameter.class);
    public static final ASTPattern.Pattern DECLARATION = anyOf(VAR_DECLARATION, FUNCTION_DECLARATION);

    public static final ASTPattern.Pattern INTEGER_TYPE = type(IntegerType.class);
    public static final ASTPattern.Pattern DOUBLE_TYPE = type(DoubleType.class);
    public static final ASTPattern.Pattern BOOLEAN_TYPE = type(BooleanType.class);
    public static final ASTPattern.Pattern STRING_TYPE = type(StringType.class);
    public static final ASTPattern.Pattern TYPE = anyOf(
            INTEGER_TYPE, DOUBLE_TYPE, BOOLEAN_TYPE, STRING_TYPE);
    public static final ASTPattern.Pattern E_TYPE = type(EType.class);

    public static final ASTPattern.Pattern BINARY_EXPR = type(BinaryExpr.class);
    public static final ASTPattern.Pattern UNARY_EXPR = type(UnaryExpr.class);
    public static final ASTPattern.Pattern CAST_EXPR = type(CastExpr.class);
    public static final ASTPattern.Pattern IDENTIFIER_EXPR = type(IdentifierExpr.class);
    public static final ASTPattern.Pattern INT_LITERAL = type(IntLiteral.class);
    public static final ASTPattern.Pattern DOUBLE_LITERAL = type(DoubleLiteral.class);
    public static final ASTPattern.Pattern BOOLEAN_LITERAL = type(BooleanLiteral.class);
    public static final ASTPattern.Pattern FUNCTION_CALL_EXPR = type(FunctionCallExpr.class);
    public static final ASTPattern.Pattern EXPRESSION = anyOf(
            BINARY_EXPR, UNARY_EXPR, CAST_EXPR, IDENTIFIER_EXPR, INT_LITERAL,
            DOUBLE_LITERAL, BOOLEAN_LITERAL, FUNCTION_CALL_EXPR);
    public static final ASTPattern.Pattern BINARY_OPERATOR = type(BinaryOperator.class);
    public static final ASTPattern.Pattern UNARY_OPERATOR = type(UnaryOperator.class);

    public static final ASTPattern.Pattern VARIABLE = type(Variable.class);
    public static final ASTPattern.Pattern VARIABLE_SET = type(VariableSet.class);
    public static final ASTPattern.Pattern INDEX = type(Index.class);
    public static final ASTPattern.Pattern SET_OF = type(Set_of.class);

    public static final ASTPattern.Pattern LINEAR_EXPR = type(LinearExpr.class);
    public static final ASTPattern.Pattern LINEAR_FACTOR = type(LinearFactor.class);
    public static final ASTPattern.Pattern SUM = type(Sum.class);
    public static final ASTPattern.Pattern LINEAR_TERM = anyOf(LINEAR_FACTOR, SUM);

    public static final ASTPattern.Pattern ONE_SIDE_BOUND = type(OneSideBound.class);
    public static final ASTPattern.Pattern TWO_SIDE_BOUND = type(TwoSideBound.class);
    public static final ASTPattern.Pattern BOUND = anyOf(ONE_SIDE_BOUND, TWO_SIDE_BOUND);

    public static final ASTPattern.Pattern RELATIONAL_CONSTRAINT = type(RelationalConstraint.class);
    public static final ASTPattern.Pattern OR_CONSTRAINT = type(OrConstraint.class);
    public static final ASTPattern.Pattern IMPLICATION_CONSTRAINT = type(ImplicationConstraint.class);
    public static final ASTPattern.Pattern DIFFERENT_VALUE_CONSTRAINT = type(DifferentValueConstraint.class);
    public static final ASTPattern.Pattern INDICATOR_CONSTRAINT = type(IndicatorConstraint.class);
    public static final ASTPattern.Pattern EQUALS_CONSTRAINT = type(EqualsConstraint.class);
    public static final ASTPattern.Pattern ALL_DIFFERENT_CONSTRAINT = type(AllDifferentConstraint.class);
    public static final ASTPattern.Pattern PERMUTATION_CONSTRAINT = type(PermutationConstraint.class);
    public static final ASTPattern.Pattern MEMBERSHIP_CONSTRAINT = type(MembershipConstraint.class);
    public static final ASTPattern.Pattern MAX_CONSTRAINT = type(MaxConstraint.class);
    public static final ASTPattern.Pattern MIN_CONSTRAINT = type(MinConstraint.class);
    public static final ASTPattern.Pattern OR_BINARY_CONSTRAINT = type(OrBinaryConstraint.class);
    public static final ASTPattern.Pattern AND_BINARY_CONSTRAINT = type(AndBinaryConstraint.class);
    public static final ASTPattern.Pattern ABS_CONSTRAINT = type(AbsConstraint.class);
    public static final ASTPattern.Pattern PIECEWISE_LINEAR_CONSTRAINT = type(PiecewiseLinearConstraint.class);
    public static final ASTPattern.Pattern CONSTRAINT = anyOf(
            RELATIONAL_CONSTRAINT, OR_CONSTRAINT, IMPLICATION_CONSTRAINT,
            DIFFERENT_VALUE_CONSTRAINT, INDICATOR_CONSTRAINT, EQUALS_CONSTRAINT,
            ALL_DIFFERENT_CONSTRAINT, PERMUTATION_CONSTRAINT, MEMBERSHIP_CONSTRAINT,
            MAX_CONSTRAINT, MIN_CONSTRAINT, OR_BINARY_CONSTRAINT,
            AND_BINARY_CONSTRAINT, ABS_CONSTRAINT, PIECEWISE_LINEAR_CONSTRAINT);
    public static final ASTPattern.Pattern REL_OPERATOR = type(RelOperator.class);
    public static final ASTPattern.Pattern PAIR = type(Pair.class);
    public static final ASTPattern.Pattern OBJECTIVE = type(Objective.class);

    private ASTPatternType() {
    }
}
