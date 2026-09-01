// Generated from C:/Users/Miguel Toro/Proyecto_antlr/antlr/grammars/PLIModel.g4 by ANTLR 4.13.2
package lsi.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PLIModelParser}.
 */
public interface PLIModelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#model}.
	 * @param ctx the parse tree
	 */
	void enterModel(PLIModelParser.ModelContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#model}.
	 * @param ctx the parse tree
	 */
	void exitModel(PLIModelParser.ModelContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(PLIModelParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(PLIModelParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#goal}.
	 * @param ctx the parse tree
	 */
	void enterGoal(PLIModelParser.GoalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#goal}.
	 * @param ctx the parse tree
	 */
	void exitGoal(PLIModelParser.GoalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#constraints}.
	 * @param ctx the parse tree
	 */
	void enterConstraints(PLIModelParser.ConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#constraints}.
	 * @param ctx the parse tree
	 */
	void exitConstraints(PLIModelParser.ConstraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#bounds}.
	 * @param ctx the parse tree
	 */
	void enterBounds(PLIModelParser.BoundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#bounds}.
	 * @param ctx the parse tree
	 */
	void exitBounds(PLIModelParser.BoundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#bin_vars}.
	 * @param ctx the parse tree
	 */
	void enterBin_vars(PLIModelParser.Bin_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#bin_vars}.
	 * @param ctx the parse tree
	 */
	void exitBin_vars(PLIModelParser.Bin_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#int_vars}.
	 * @param ctx the parse tree
	 */
	void enterInt_vars(PLIModelParser.Int_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#int_vars}.
	 * @param ctx the parse tree
	 */
	void exitInt_vars(PLIModelParser.Int_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#free_vars}.
	 * @param ctx the parse tree
	 */
	void enterFree_vars(PLIModelParser.Free_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#free_vars}.
	 * @param ctx the parse tree
	 */
	void exitFree_vars(PLIModelParser.Free_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#semi_continuous_vars}.
	 * @param ctx the parse tree
	 */
	void enterSemi_continuous_vars(PLIModelParser.Semi_continuous_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#semi_continuous_vars}.
	 * @param ctx the parse tree
	 */
	void exitSemi_continuous_vars(PLIModelParser.Semi_continuous_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#objective}.
	 * @param ctx the parse tree
	 */
	void enterObjective(PLIModelParser.ObjectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#objective}.
	 * @param ctx the parse tree
	 */
	void exitObjective(PLIModelParser.ObjectiveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclaration}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(PLIModelParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclaration}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(PLIModelParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(PLIModelParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(PLIModelParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#formal_parameters}.
	 * @param ctx the parse tree
	 */
	void enterFormal_parameters(PLIModelParser.Formal_parametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#formal_parameters}.
	 * @param ctx the parse tree
	 */
	void exitFormal_parameters(PLIModelParser.Formal_parametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#formal_parameter}.
	 * @param ctx the parse tree
	 */
	void enterFormal_parameter(PLIModelParser.Formal_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#formal_parameter}.
	 * @param ctx the parse tree
	 */
	void exitFormal_parameter(PLIModelParser.Formal_parameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PLIModelParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PLIModelParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#var_collection}.
	 * @param ctx the parse tree
	 */
	void enterVar_collection(PLIModelParser.Var_collectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#var_collection}.
	 * @param ctx the parse tree
	 */
	void exitVar_collection(PLIModelParser.Var_collectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#expr_collection}.
	 * @param ctx the parse tree
	 */
	void enterExpr_collection(PLIModelParser.Expr_collectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#expr_collection}.
	 * @param ctx the parse tree
	 */
	void exitExpr_collection(PLIModelParser.Expr_collectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#linear_expr_collection}.
	 * @param ctx the parse tree
	 */
	void enterLinear_expr_collection(PLIModelParser.Linear_expr_collectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#linear_expr_collection}.
	 * @param ctx the parse tree
	 */
	void exitLinear_expr_collection(PLIModelParser.Linear_expr_collectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#set_of_constraints}.
	 * @param ctx the parse tree
	 */
	void enterSet_of_constraints(PLIModelParser.Set_of_constraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#set_of_constraints}.
	 * @param ctx the parse tree
	 */
	void exitSet_of_constraints(PLIModelParser.Set_of_constraintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#set_of_bounds}.
	 * @param ctx the parse tree
	 */
	void enterSet_of_bounds(PLIModelParser.Set_of_boundsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#set_of_bounds}.
	 * @param ctx the parse tree
	 */
	void exitSet_of_bounds(PLIModelParser.Set_of_boundsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#set_of_LinearExprs}.
	 * @param ctx the parse tree
	 */
	void enterSet_of_LinearExprs(PLIModelParser.Set_of_LinearExprsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#set_of_LinearExprs}.
	 * @param ctx the parse tree
	 */
	void exitSet_of_LinearExprs(PLIModelParser.Set_of_LinearExprsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#set_of_vars}.
	 * @param ctx the parse tree
	 */
	void enterSet_of_vars(PLIModelParser.Set_of_varsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#set_of_vars}.
	 * @param ctx the parse tree
	 */
	void exitSet_of_vars(PLIModelParser.Set_of_varsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#set_of_expressions}.
	 * @param ctx the parse tree
	 */
	void enterSet_of_expressions(PLIModelParser.Set_of_expressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#set_of_expressions}.
	 * @param ctx the parse tree
	 */
	void exitSet_of_expressions(PLIModelParser.Set_of_expressionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomicConstraintExpr}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterAtomicConstraintExpr(PLIModelParser.AtomicConstraintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomicConstraintExpr}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitAtomicConstraintExpr(PLIModelParser.AtomicConstraintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compoundConstraintExpr}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterCompoundConstraintExpr(PLIModelParser.CompoundConstraintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compoundConstraintExpr}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitCompoundConstraintExpr(PLIModelParser.CompoundConstraintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalConstraint}
	 * labeled alternative in {@link PLIModelParser#atomic_constraint}.
	 * @param ctx the parse tree
	 */
	void enterRelationalConstraint(PLIModelParser.RelationalConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalConstraint}
	 * labeled alternative in {@link PLIModelParser#atomic_constraint}.
	 * @param ctx the parse tree
	 */
	void exitRelationalConstraint(PLIModelParser.RelationalConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterOrConstraint(PLIModelParser.OrConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitOrConstraint(PLIModelParser.OrConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implicationConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterImplicationConstraint(PLIModelParser.ImplicationConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implicationConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitImplicationConstraint(PLIModelParser.ImplicationConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code differentValueConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code differentValueConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indicatorConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indicatorConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalsConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterEqualsConstraint(PLIModelParser.EqualsConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalsConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitEqualsConstraint(PLIModelParser.EqualsConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allDifferentConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterAllDifferentConstraint(PLIModelParser.AllDifferentConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allDifferentConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitAllDifferentConstraint(PLIModelParser.AllDifferentConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code permutationConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterPermutationConstraint(PLIModelParser.PermutationConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code permutationConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitPermutationConstraint(PLIModelParser.PermutationConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code membershipConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterMembershipConstraint(PLIModelParser.MembershipConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code membershipConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitMembershipConstraint(PLIModelParser.MembershipConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code maxConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterMaxConstraint(PLIModelParser.MaxConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code maxConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitMaxConstraint(PLIModelParser.MaxConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterMinConstraint(PLIModelParser.MinConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitMinConstraint(PLIModelParser.MinConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orBinaryConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterOrBinaryConstraint(PLIModelParser.OrBinaryConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orBinaryConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitOrBinaryConstraint(PLIModelParser.OrBinaryConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andBinaryConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterAndBinaryConstraint(PLIModelParser.AndBinaryConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andBinaryConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitAndBinaryConstraint(PLIModelParser.AndBinaryConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code absConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterAbsConstraint(PLIModelParser.AbsConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code absConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitAbsConstraint(PLIModelParser.AbsConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code piecewiseLinearConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void enterPiecewiseLinearConstraint(PLIModelParser.PiecewiseLinearConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code piecewiseLinearConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 */
	void exitPiecewiseLinearConstraint(PLIModelParser.PiecewiseLinearConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code oneSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterOneSideBound(PLIModelParser.OneSideBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code oneSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitOneSideBound(PLIModelParser.OneSideBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code twoSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void enterTwoSideBound(PLIModelParser.TwoSideBoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code twoSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 */
	void exitTwoSideBound(PLIModelParser.TwoSideBoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#linear_expr}.
	 * @param ctx the parse tree
	 */
	void enterLinear_expr(PLIModelParser.Linear_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#linear_expr}.
	 * @param ctx the parse tree
	 */
	void exitLinear_expr(PLIModelParser.Linear_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusTerm}
	 * labeled alternative in {@link PLIModelParser#slinear_term}.
	 * @param ctx the parse tree
	 */
	void enterPlusTerm(PLIModelParser.PlusTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusTerm}
	 * labeled alternative in {@link PLIModelParser#slinear_term}.
	 * @param ctx the parse tree
	 */
	void exitPlusTerm(PLIModelParser.PlusTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minusTerm}
	 * labeled alternative in {@link PLIModelParser#slinear_term}.
	 * @param ctx the parse tree
	 */
	void enterMinusTerm(PLIModelParser.MinusTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minusTerm}
	 * labeled alternative in {@link PLIModelParser#slinear_term}.
	 * @param ctx the parse tree
	 */
	void exitMinusTerm(PLIModelParser.MinusTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code factorTerm}
	 * labeled alternative in {@link PLIModelParser#linear_term}.
	 * @param ctx the parse tree
	 */
	void enterFactorTerm(PLIModelParser.FactorTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code factorTerm}
	 * labeled alternative in {@link PLIModelParser#linear_term}.
	 * @param ctx the parse tree
	 */
	void exitFactorTerm(PLIModelParser.FactorTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sumSetOfTerm}
	 * labeled alternative in {@link PLIModelParser#linear_term}.
	 * @param ctx the parse tree
	 */
	void enterSumSetOfTerm(PLIModelParser.SumSetOfTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sumSetOfTerm}
	 * labeled alternative in {@link PLIModelParser#linear_term}.
	 * @param ctx the parse tree
	 */
	void exitSumSetOfTerm(PLIModelParser.SumSetOfTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#set_of_linear_factor}.
	 * @param ctx the parse tree
	 */
	void enterSet_of_linear_factor(PLIModelParser.Set_of_linear_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#set_of_linear_factor}.
	 * @param ctx the parse tree
	 */
	void exitSet_of_linear_factor(PLIModelParser.Set_of_linear_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#linear_factor}.
	 * @param ctx the parse tree
	 */
	void enterLinear_factor(PLIModelParser.Linear_factorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#linear_factor}.
	 * @param ctx the parse tree
	 */
	void exitLinear_factor(PLIModelParser.Linear_factorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(PLIModelParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(PLIModelParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#index_list}.
	 * @param ctx the parse tree
	 */
	void enterIndex_list(PLIModelParser.Index_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#index_list}.
	 * @param ctx the parse tree
	 */
	void exitIndex_list(PLIModelParser.Index_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(PLIModelParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(PLIModelParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(PLIModelParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(PLIModelParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(PLIModelParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(PLIModelParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationalExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(PLIModelParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationalExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(PLIModelParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(PLIModelParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(PLIModelParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(PLIModelParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(PLIModelParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(PLIModelParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(PLIModelParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleLiteralExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteralExpression(PLIModelParser.DoubleLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleLiteralExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteralExpression(PLIModelParser.DoubleLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(PLIModelParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(PLIModelParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intCastExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntCastExpression(PLIModelParser.IntCastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intCastExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntCastExpression(PLIModelParser.IntCastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLiteralExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteralExpression(PLIModelParser.IntegerLiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLiteralExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteralExpression(PLIModelParser.IntegerLiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(PLIModelParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(PLIModelParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(PLIModelParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(PLIModelParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleCastExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDoubleCastExpression(PLIModelParser.DoubleCastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleCastExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDoubleCastExpression(PLIModelParser.DoubleCastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(PLIModelParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(PLIModelParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(PLIModelParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(PLIModelParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link PLIModelParser#rel_op}.
	 * @param ctx the parse tree
	 */
	void enterRel_op(PLIModelParser.Rel_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link PLIModelParser#rel_op}.
	 * @param ctx the parse tree
	 */
	void exitRel_op(PLIModelParser.Rel_opContext ctx);
}