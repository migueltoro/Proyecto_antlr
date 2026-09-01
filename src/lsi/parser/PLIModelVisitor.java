// Generated from C:/Users/Miguel Toro/Proyecto_antlr/antlr/grammars/PLIModel.g4 by ANTLR 4.13.2
package lsi.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PLIModelParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PLIModelVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#model}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel(PLIModelParser.ModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(PLIModelParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#goal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoal(PLIModelParser.GoalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#constraints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraints(PLIModelParser.ConstraintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#bounds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBounds(PLIModelParser.BoundsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#bin_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBin_vars(PLIModelParser.Bin_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#int_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_vars(PLIModelParser.Int_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#free_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_vars(PLIModelParser.Free_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#semi_continuous_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSemi_continuous_vars(PLIModelParser.Semi_continuous_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#objective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjective(PLIModelParser.ObjectiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDeclaration}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(PLIModelParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionDeclaration}
	 * labeled alternative in {@link PLIModelParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(PLIModelParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#formal_parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_parameters(PLIModelParser.Formal_parametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#formal_parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal_parameter(PLIModelParser.Formal_parameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PLIModelParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#var_collection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_collection(PLIModelParser.Var_collectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#expr_collection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_collection(PLIModelParser.Expr_collectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#linear_expr_collection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinear_expr_collection(PLIModelParser.Linear_expr_collectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#set_of_constraints}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_of_constraints(PLIModelParser.Set_of_constraintsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#set_of_bounds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_of_bounds(PLIModelParser.Set_of_boundsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#set_of_LinearExprs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_of_LinearExprs(PLIModelParser.Set_of_LinearExprsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#set_of_vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_of_vars(PLIModelParser.Set_of_varsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#set_of_expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_of_expressions(PLIModelParser.Set_of_expressionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomicConstraintExpr}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomicConstraintExpr(PLIModelParser.AtomicConstraintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compoundConstraintExpr}
	 * labeled alternative in {@link PLIModelParser#constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundConstraintExpr(PLIModelParser.CompoundConstraintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalConstraint}
	 * labeled alternative in {@link PLIModelParser#atomic_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalConstraint(PLIModelParser.RelationalConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrConstraint(PLIModelParser.OrConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code implicationConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicationConstraint(PLIModelParser.ImplicationConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code differentValueConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDifferentValueConstraint(PLIModelParser.DifferentValueConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indicatorConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndicatorConstraint(PLIModelParser.IndicatorConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalsConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualsConstraint(PLIModelParser.EqualsConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allDifferentConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllDifferentConstraint(PLIModelParser.AllDifferentConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code permutationConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPermutationConstraint(PLIModelParser.PermutationConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code membershipConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMembershipConstraint(PLIModelParser.MembershipConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code maxConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMaxConstraint(PLIModelParser.MaxConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinConstraint(PLIModelParser.MinConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orBinaryConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrBinaryConstraint(PLIModelParser.OrBinaryConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andBinaryConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndBinaryConstraint(PLIModelParser.AndBinaryConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code absConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsConstraint(PLIModelParser.AbsConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code piecewiseLinearConstraint}
	 * labeled alternative in {@link PLIModelParser#compound_constraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPiecewiseLinearConstraint(PLIModelParser.PiecewiseLinearConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code oneSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneSideBound(PLIModelParser.OneSideBoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code twoSideBound}
	 * labeled alternative in {@link PLIModelParser#bound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTwoSideBound(PLIModelParser.TwoSideBoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#linear_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinear_expr(PLIModelParser.Linear_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusTerm}
	 * labeled alternative in {@link PLIModelParser#slinear_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusTerm(PLIModelParser.PlusTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusTerm}
	 * labeled alternative in {@link PLIModelParser#slinear_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusTerm(PLIModelParser.MinusTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code factorTerm}
	 * labeled alternative in {@link PLIModelParser#linear_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorTerm(PLIModelParser.FactorTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sumSetOfTerm}
	 * labeled alternative in {@link PLIModelParser#linear_term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumSetOfTerm(PLIModelParser.SumSetOfTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#set_of_linear_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet_of_linear_factor(PLIModelParser.Set_of_linear_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#linear_factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinear_factor(PLIModelParser.Linear_factorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(PLIModelParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#index_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_list(PLIModelParser.Index_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(PLIModelParser.IndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(PLIModelParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by the {@code additiveExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(PLIModelParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationalExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(PLIModelParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(PLIModelParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicativeExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(PLIModelParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(PLIModelParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleLiteralExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteralExpression(PLIModelParser.DoubleLiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(PLIModelParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intCastExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntCastExpression(PLIModelParser.IntCastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteralExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteralExpression(PLIModelParser.IntegerLiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(PLIModelParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(PLIModelParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleCastExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleCastExpression(PLIModelParser.DoubleCastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link PLIModelParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(PLIModelParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(PLIModelParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link PLIModelParser#rel_op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRel_op(PLIModelParser.Rel_opContext ctx);
}