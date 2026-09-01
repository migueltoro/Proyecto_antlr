package lsi.visitor.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import lsi.ast.bounds.Bound;
import lsi.ast.bounds.OneSideBound;
import lsi.ast.bounds.TwoSideBound;
import lsi.ast.common.Set_of;
import lsi.ast.constraints.AbsConstraint;
import lsi.ast.constraints.AllDifferentConstraint;
import lsi.ast.constraints.AndBinaryConstraint;
import lsi.ast.constraints.Constraint;
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
import lsi.ast.declarations.Declaration;
import lsi.ast.declarations.FunctionDeclaration;
import lsi.ast.common.Index;
import lsi.ast.declarations.Parameter;
import lsi.ast.declarations.VarDeclaration;
import lsi.ast.expressions.BinaryExpr;
import lsi.ast.expressions.BinaryOperator;
import lsi.ast.expressions.CastExpr;
import lsi.ast.expressions.DoubleLiteral;
import lsi.ast.expressions.Expression;
import lsi.ast.expressions.FunctionCallExpr;
import lsi.ast.expressions.IdentifierExpr;
import lsi.ast.expressions.IntLiteral;
import lsi.ast.expressions.UnaryExpr;
import lsi.ast.expressions.UnaryOperator;
//import lsi.ast.linear.FactorTerm;
//import lsi.ast.linear.FactorTerm;
import lsi.ast.linear.LinearExpr;
import lsi.ast.linear.LinearFactor;
import lsi.ast.linear.LinearTerm;
//import lsi.ast.linear.Set_of_Terms;
//import lsi.ast.linear.SumTerm;
import lsi.ast.linear.Sum;
import lsi.ast.types.BooleanType;
import lsi.ast.types.DoubleType;
import lsi.ast.types.IntegerType;
import lsi.ast.types.StringType;
import lsi.ast.types.Type;

import lsi.ast.AST;
import lsi.ast.Objective;
import lsi.ast.variables.Variable;
import lsi.parser.PLIModelParser;

public class ASTBuilder {

    public AST build(ParseTree tree) {
        if (tree instanceof PLIModelParser.ModelContext modelContext) {
            return build(modelContext);
        }
        throw new IllegalArgumentException("Expected a model parse tree");
    }

    public AST build(PLIModelParser.ModelContext context) {
        List<Declaration> declarations = new ArrayList<>();
        if (context.head() != null) {
            declarations.addAll(buildDeclarations(context.head()));
        }

        Objective objective = buildObjective(context.goal().objective());
        LinearExpr objectiveExpr = buildLinearExpr(context.goal().linear_expr());

        List<Set_of<Constraint>> constraints = new ArrayList<>();
        for (PLIModelParser.Set_of_constraintsContext setOfConstraintContext : context.constraints()
                .set_of_constraints()) {
            constraints.add(buildSetOfConstraint(setOfConstraintContext));
        }

        List<Set_of<Bound>> bounds = new ArrayList<>();
        if (context.bounds() != null) {
            for (PLIModelParser.Set_of_boundsContext setOfBoundContext : context.bounds().set_of_bounds()) {
                bounds.add(buildSetOfBound(setOfBoundContext));
            }
        }

        List<Set_of<Variable>> binaryVars = new ArrayList<>();
        if (context.bin_vars() != null) {
            for (PLIModelParser.Set_of_varsContext setOfVarContext : context.bin_vars().set_of_vars()) {
                binaryVars.add(buildVariableSet(setOfVarContext));
            }
        }

        List<Set_of<Variable>> integerVars = new ArrayList<>();
        if (context.int_vars() != null) {
            for (PLIModelParser.Set_of_varsContext setOfVarContext : context.int_vars().set_of_vars()) {
                integerVars.add(buildVariableSet(setOfVarContext));
            }
        }

        List<Set_of<Variable>> freeVars = new ArrayList<>();
        if (context.free_vars() != null) {
            for (PLIModelParser.Set_of_varsContext setOfVarContext : context.free_vars().set_of_vars()) {
                freeVars.add(buildVariableSet(setOfVarContext));
            }
        }

        List<Set_of<Variable>> semiContinuousVars = new ArrayList<>();
        if (context.semi_continuous_vars() != null) {
            for (PLIModelParser.Set_of_varsContext setOfVarContext : context.semi_continuous_vars().set_of_vars()) {
                semiContinuousVars.add(buildVariableSet(setOfVarContext));
            }
        }

        return new AST(declarations, objective, objectiveExpr, constraints, bounds, binaryVars, integerVars, freeVars,
                semiContinuousVars);
    }

    private List<Declaration> buildDeclarations(PLIModelParser.HeadContext context) {
        List<Declaration> declarations = new ArrayList<>();
        for (PLIModelParser.DeclarationContext declarationContext : context.declaration()) {
            declarations.add(buildDeclaration(declarationContext));
        }
        return declarations;
    }

    private Declaration buildDeclaration(PLIModelParser.DeclarationContext context) {
        if (context instanceof PLIModelParser.VarDeclarationContext varDeclarationContext) {
            Type type = buildType(varDeclarationContext.type());
            String name = varDeclarationContext.ID().getText();
            Expression initializer = buildExpression(varDeclarationContext.expression());
            return new VarDeclaration(type, name, initializer);
        }

        if (context instanceof PLIModelParser.FunctionDeclarationContext functionDeclarationContext) {
            Type returnType = buildType(functionDeclarationContext.type());
            String name = functionDeclarationContext.ID().getText();
            List<Parameter> parameters = new ArrayList<>();
            if (functionDeclarationContext.formal_parameters() != null) {
                for (PLIModelParser.Formal_parameterContext parameterContext : functionDeclarationContext
                        .formal_parameters()
                        .formal_parameter()) {
                    parameters.add(new Parameter(buildType(parameterContext.type()), parameterContext.ID().getText()));
                }
            }
            return new FunctionDeclaration(returnType, name, parameters);
        }

        throw new IllegalArgumentException("Unsupported declaration type: " + context.getClass().getSimpleName());
    }

    private Index buildIndex(PLIModelParser.IndexContext context) {
        return new Index(context.ID().getText(), buildExpression(context.expression(0)),
                buildExpression(context.expression(1)));
    }

    private Type buildType(PLIModelParser.TypeContext context) {
        return switch (context.getText()) {
            case "Integer" -> new IntegerType();
            case "Double" -> new DoubleType();
            case "Boolean" -> new BooleanType();
            case "String" -> new StringType();
            default -> throw new IllegalArgumentException("Unsupported type: " + context.getText());
        };
    }

    private Objective buildObjective(PLIModelParser.ObjectiveContext context) {
        return context.getText().equals("min") ? Objective.MIN : Objective.MAX;
    }

    private LinearExpr buildLinearExpr(PLIModelParser.Linear_exprContext context) {
        List<LinearTerm> terms = new ArrayList<>();

        terms.add(applyLinearTermSign(buildLinearTerm(context.linear_term()), 1));
        for (PLIModelParser.Slinear_termContext signedTermContext : context.slinear_term()) {
            terms.add(buildSignedLinearTerm(signedTermContext));
        }

        return new LinearExpr(terms);
    }

    private LinearTerm buildSignedLinearTerm(PLIModelParser.Slinear_termContext context) {
        if (context instanceof PLIModelParser.PlusTermContext plusTermContext) {
            return applyLinearTermSign(buildLinearTerm(plusTermContext.linear_term()), 1);
        }
        if (context instanceof PLIModelParser.MinusTermContext minusTermContext) {
            return applyLinearTermSign(buildLinearTerm(minusTermContext.linear_term()), -1);
        }
        throw new IllegalArgumentException("Unsupported signed linear term: " + context.getClass().getSimpleName());
    }

    private LinearTerm applyLinearTermSign(LinearTerm term, int operatorSign) {
        NormalizedLinearTerm normalizedTerm = normalizeLinearTerm(term);
        return buildLinearTermWithSign(normalizedTerm, operatorSign * normalizedTerm.sign());
    }

    private NormalizedLinearTerm normalizeLinearTerm(LinearTerm term) {
        if (term instanceof LinearFactor factor) {
            NormalizedCoefficient normalizedCoefficient = normalizeCoefficient(factor.coefficient());
            return new NormalizedLinearTerm(normalizedCoefficient.sign(),
                    new LinearFactor(normalizedCoefficient.expression(), factor.variable()));
        }
        if (term instanceof Sum sumTerm) {
            LinearFactor factor = sumTerm.terms().element();
            NormalizedCoefficient normalizedCoefficient = normalizeCoefficient(factor.coefficient());
            LinearFactor normalizedFactor = new LinearFactor(normalizedCoefficient.expression(), factor.variable());
            Set_of<LinearFactor> normalizedSet = new Set_of<>(normalizedFactor, sumTerm.terms().indexes(),
                    sumTerm.terms().filter());
            return new NormalizedLinearTerm(normalizedCoefficient.sign(), new Sum(normalizedSet));
        }
        return new NormalizedLinearTerm(1, term);
    }

    private NormalizedCoefficient normalizeCoefficient(Expression expression) {
        if (expression instanceof IntLiteral intLiteral) {
            return intLiteral.value() < 0
                    ? new NormalizedCoefficient(-1, new IntLiteral(-intLiteral.value()))
                    : new NormalizedCoefficient(1, intLiteral);
        }
        if (expression instanceof DoubleLiteral doubleLiteral) {
            return Double.compare(doubleLiteral.value(), 0.0) < 0
                    ? new NormalizedCoefficient(-1, new DoubleLiteral(-doubleLiteral.value()))
                    : new NormalizedCoefficient(1, doubleLiteral);
        }
        if (expression instanceof UnaryExpr unaryExpr) {
            if (unaryExpr.operator() == UnaryOperator.PLUS) {
                return normalizeCoefficient(unaryExpr.expression());
            }
            if (unaryExpr.operator() == UnaryOperator.MINUS) {
                NormalizedCoefficient normalizedInner = normalizeCoefficient(unaryExpr.expression());
                return new NormalizedCoefficient(-normalizedInner.sign(), normalizedInner.expression());
            }
        }
        return new NormalizedCoefficient(1, expression);
    }

    private LinearTerm buildLinearTermWithSign(NormalizedLinearTerm normalizedTerm, int sign) {
        return sign < 0 ? negateNormalizedLinearTerm(normalizedTerm.term()) : normalizedTerm.term();
    }

    private LinearTerm buildLinearTerm(PLIModelParser.Linear_termContext context) {
        if (context instanceof PLIModelParser.FactorTermContext factorTermContext) {
            return buildLinearFactor(factorTermContext.linear_factor());
        }
        if (context instanceof PLIModelParser.SumSetOfTermContext sumSetOfTermContext) {
            return new Sum(buildSetOfLinearFactor(sumSetOfTermContext.set_of_linear_factor()));
        }
        throw new IllegalArgumentException("Unsupported linear term: " + context.getClass().getSimpleName());
    }

    private LinearFactor buildLinearFactor(PLIModelParser.Linear_factorContext context) {
        Expression coefficient = context.expression() != null ? buildExpression(context.expression())
                : new IntLiteral(1);
        return new LinearFactor(coefficient, buildVariable(context.var()));
    }

    private Set_of<LinearFactor> buildSetOfLinearFactor(PLIModelParser.Set_of_linear_factorContext context) {
        List<Index> indexes = new ArrayList<>();
        for (PLIModelParser.IndexContext indexContext : context.index()) {
            indexes.add(buildIndex(indexContext));
        }
        Expression filter = context.expression() != null ? buildExpression(context.expression()) : null;
        return new Set_of<>(buildLinearFactor(context.linear_factor()), indexes, filter);
    }

    private Variable buildVariable(PLIModelParser.VarContext context) {
        List<Expression> indexes = new ArrayList<>();
        if (context.index_list() != null) {
            for (PLIModelParser.ExpressionContext expressionContext : context.index_list().expression()) {
                indexes.add(buildExpression(expressionContext));
            }
        }
        return new Variable(context.ID().getText(), indexes);
    }

    private Set_of<Variable> buildVariableSet(PLIModelParser.Set_of_varsContext context) {
        List<Index> indexes = new ArrayList<>();
        for (PLIModelParser.IndexContext indexContext : context.index()) {
            indexes.add(buildIndex(indexContext));
        }
        Expression filter = context.expression() != null ? buildExpression(context.expression()) : null;
        return new Set_of<>(buildVariable(context.var()), indexes, filter);
    }

    private Constraint buildConstraint(PLIModelParser.ConstraintContext context) {
        if (context instanceof PLIModelParser.AtomicConstraintExprContext atomicConstraintExprContext) {
            return buildAtomicConstraint(atomicConstraintExprContext.atomic_constraint());
        }
        if (context instanceof PLIModelParser.CompoundConstraintExprContext compoundConstraintExprContext) {
            return buildCompoundConstraint(compoundConstraintExprContext.compound_constraint());
        }
        throw new IllegalArgumentException("Unsupported constraint: " + context.getClass().getSimpleName());
    }

    private Constraint buildAtomicConstraint(PLIModelParser.Atomic_constraintContext context) {
        if (context instanceof PLIModelParser.RelationalConstraintContext relationalConstraintContext) {
            return new RelationalConstraint(buildLinearExpr(relationalConstraintContext.linear_expr()),
                    buildRelOperator(relationalConstraintContext.rel_op()),
                    buildExpression(relationalConstraintContext.expression()));
        }
        throw new IllegalArgumentException("Unsupported atomic constraint: " + context.getClass().getSimpleName());
    }

    private Constraint buildCompoundConstraint(PLIModelParser.Compound_constraintContext context) {
        if (context instanceof PLIModelParser.OrConstraintContext orConstraintContext) {
            List<RelationalConstraint> constraints = new ArrayList<>();
            for (PLIModelParser.Atomic_constraintContext atomicConstraintContext : orConstraintContext
                    .atomic_constraint()) {
                constraints.add((RelationalConstraint) buildAtomicConstraint(atomicConstraintContext));
            }
            return new OrConstraint(buildRelOperator(orConstraintContext.rel_op()),
                    Integer.parseInt(orConstraintContext.INT().getText()),
                    constraints);
        }
        if (context instanceof PLIModelParser.ImplicationConstraintContext implicationConstraintContext) {
            return new ImplicationConstraint(
                    (RelationalConstraint) buildAtomicConstraint(implicationConstraintContext.atomic_constraint(0)),
                    (RelationalConstraint) buildAtomicConstraint(implicationConstraintContext.atomic_constraint(1)));
        }
        if (context instanceof PLIModelParser.DifferentValueConstraintContext differentValueConstraintContext) {
            return new DifferentValueConstraint(buildVariable(differentValueConstraintContext.var(0)),
                    buildVariable(differentValueConstraintContext.var(1)));
        }
        if (context instanceof PLIModelParser.IndicatorConstraintContext indicatorConstraintContext) {
            return new IndicatorConstraint(buildVariable(indicatorConstraintContext.var()),
                    Integer.parseInt(indicatorConstraintContext.INT().getText()),
                    (RelationalConstraint) buildAtomicConstraint(indicatorConstraintContext.atomic_constraint()));
        }
        if (context instanceof PLIModelParser.EqualsConstraintContext equalsConstraintContext) {
            return new EqualsConstraint(buildVariable(equalsConstraintContext.var(0)),
                    buildVariable(equalsConstraintContext.var(1)));
        }
        if (context instanceof PLIModelParser.AllDifferentConstraintContext allDifferentConstraintContext) {
            return new AllDifferentConstraint(buildSetOfVariable(allDifferentConstraintContext.set_of_vars()));
        }
        if (context instanceof PLIModelParser.PermutationConstraintContext permutationConstraintContext) {
            return new PermutationConstraint(buildSetOfVariable(permutationConstraintContext.set_of_vars()),
                    buildSetOfExpression(permutationConstraintContext.set_of_expressions()));
        }
        if (context instanceof PLIModelParser.MembershipConstraintContext membershipConstraintContext) {
            return new MembershipConstraint(buildVariable(membershipConstraintContext.var()),
                    buildSetOfExpression(membershipConstraintContext.set_of_expressions()));
        }
        if (context instanceof PLIModelParser.MaxConstraintContext maxConstraintContext) {
            return new MaxConstraint(buildVariable(maxConstraintContext.var()),
                    buildSetOfVariable(maxConstraintContext.set_of_vars()));
        }
        if (context instanceof PLIModelParser.MinConstraintContext minConstraintContext) {
            return new MinConstraint(buildVariable(minConstraintContext.var()),
                    buildSetOfVariable(minConstraintContext.set_of_vars()));
        }
        if (context instanceof PLIModelParser.OrBinaryConstraintContext orBinaryConstraintContext) {
            return new OrBinaryConstraint(buildVariable(orBinaryConstraintContext.var()),
                    buildSetOfConstraint(orBinaryConstraintContext.set_of_constraints()));
        }
        if (context instanceof PLIModelParser.AndBinaryConstraintContext andBinaryConstraintContext) {
            return new AndBinaryConstraint(buildVariable(andBinaryConstraintContext.var()),
                    buildSetOfConstraint(andBinaryConstraintContext.set_of_constraints()));
        }
        if (context instanceof PLIModelParser.AbsConstraintContext absConstraintContext) {
            return new AbsConstraint(buildVariable(absConstraintContext.var()),
                    buildSetOfVariable(absConstraintContext.set_of_vars()));
        }
        if (context instanceof PLIModelParser.PiecewiseLinearConstraintContext piecewiseLinearConstraintContext) {
            return new PiecewiseLinearConstraint(buildVariable(piecewiseLinearConstraintContext.var(0)),
                    buildVariable(piecewiseLinearConstraintContext.var(1)),
                    buildPairs(piecewiseLinearConstraintContext.pair()));
        }
        throw new IllegalArgumentException("Unsupported compound constraint: " + context.getClass().getSimpleName());
    }

    private Set_of<Variable> buildSetOfVariable(PLIModelParser.Set_of_varsContext context) {
        List<Index> indexes = new ArrayList<>();
        for (PLIModelParser.IndexContext indexContext : context.index()) {
            indexes.add(buildIndex(indexContext));
        }
        Expression filter = context.expression() != null ? buildExpression(context.expression()) : null;
        return new Set_of<>(buildVariable(context.var()), indexes, filter);
    }

    private Set_of<Constraint> buildSetOfConstraint(PLIModelParser.Set_of_constraintsContext context) {
        List<Index> indexes = new ArrayList<>();
        for (PLIModelParser.IndexContext indexContext : context.index()) {
            indexes.add(buildIndex(indexContext));
        }
        Expression filter = context.expression() != null ? buildExpression(context.expression()) : null;
        return new Set_of<>(buildConstraint(context.constraint()), indexes, filter);
    }

    private Set_of<Expression> buildSetOfExpression(PLIModelParser.Set_of_expressionsContext context) {
        List<Index> indexes = new ArrayList<>();
        for (PLIModelParser.IndexContext indexContext : context.index()) {
            indexes.add(buildIndex(indexContext));
        }
        Expression filter = context.expression().size() > 1 ? buildExpression(context.expression(1)) : null;
        return new Set_of<>(buildExpression(context.expression(0)), indexes, filter);
    }

    private List<Pair> buildPairs(List<PLIModelParser.PairContext> contexts) {
        List<Pair> pairs = new ArrayList<>();
        for (PLIModelParser.PairContext pairContext : contexts) {
            pairs.add(new Pair(Integer.parseInt(pairContext.INT(0).getText()),
                    Integer.parseInt(pairContext.INT(1).getText())));
        }
        return pairs;
    }

    private Set_of<Bound> buildSetOfBound(PLIModelParser.Set_of_boundsContext context) {
        List<Index> indexes = new ArrayList<>();
        for (PLIModelParser.IndexContext indexContext : context.index()) {
            indexes.add(buildIndex(indexContext));
        }
        Expression filter = context.expression() != null ? buildExpression(context.expression()) : null;
        return new Set_of<>(buildBound(context.bound()), indexes, filter);
    }

    private Bound buildBound(PLIModelParser.BoundContext context) {
        if (context instanceof PLIModelParser.OneSideBoundContext oneSideBoundContext) {
            return new OneSideBound(buildVariable(oneSideBoundContext.var()),
                    buildRelOperator(oneSideBoundContext.rel_op()),
                    buildExpression(oneSideBoundContext.expression()));
        }
        if (context instanceof PLIModelParser.TwoSideBoundContext twoSideBoundContext) {
            return new TwoSideBound(buildExpression(twoSideBoundContext.expression(0)),
                    buildVariable(twoSideBoundContext.var()),
                    buildExpression(twoSideBoundContext.expression(1)));
        }
        throw new IllegalArgumentException("Unsupported bound: " + context.getClass().getSimpleName());
    }

    private RelOperator buildRelOperator(PLIModelParser.Rel_opContext context) {
        return switch (context.getText()) {
            case ">=" -> RelOperator.GE;
            case ">" -> RelOperator.GT;
            case "<=" -> RelOperator.LE;
            case "<" -> RelOperator.LT;
            case "=" -> RelOperator.EQ;
            default -> throw new IllegalArgumentException("Unsupported rel operator: " + context.getText());
        };
    }

    private Expression buildExpression(PLIModelParser.ExpressionContext context) {
        if (context instanceof PLIModelParser.AdditiveExpressionContext additiveExpressionContext) {
            return new BinaryExpr(buildExpression(additiveExpressionContext.expression(0)),
                    parseBinaryOperator(additiveExpressionContext.getChild(1).getText()),
                    buildExpression(additiveExpressionContext.expression(1)));
        }
        if (context instanceof PLIModelParser.MultiplicativeExpressionContext multiplicativeExpressionContext) {
            return new BinaryExpr(buildExpression(multiplicativeExpressionContext.expression(0)),
                    parseBinaryOperator(multiplicativeExpressionContext.getChild(1).getText()),
                    buildExpression(multiplicativeExpressionContext.expression(1)));
        }
        if (context instanceof PLIModelParser.RelationalExpressionContext relationalExpressionContext) {
            return new BinaryExpr(buildExpression(relationalExpressionContext.expression(0)),
                    parseBinaryOperator(relationalExpressionContext.getChild(1).getText()),
                    buildExpression(relationalExpressionContext.expression(1)));
        }
        if (context instanceof PLIModelParser.EqualityExpressionContext equalityExpressionContext) {
            return new BinaryExpr(buildExpression(equalityExpressionContext.expression(0)),
                    parseBinaryOperator(equalityExpressionContext.getChild(1).getText()),
                    buildExpression(equalityExpressionContext.expression(1)));
        }
        if (context instanceof PLIModelParser.LogicalExpressionContext logicalExpressionContext) {
            return new BinaryExpr(buildExpression(logicalExpressionContext.expression(0)),
                    parseBinaryOperator(logicalExpressionContext.getChild(1).getText()),
                    buildExpression(logicalExpressionContext.expression(1)));
        }
        if (context instanceof PLIModelParser.UnaryExpressionContext unaryExpressionContext) {
            return new UnaryExpr(parseUnaryOperator(unaryExpressionContext.getChild(0).getText()),
                    buildExpression(unaryExpressionContext.expression()));
        }
        if (context instanceof PLIModelParser.IntCastExpressionContext intCastExpressionContext) {
            return new CastExpr(new IntegerType(), buildExpression(intCastExpressionContext.expression()));
        }
        if (context instanceof PLIModelParser.DoubleCastExpressionContext doubleCastExpressionContext) {
            return new CastExpr(new DoubleType(), buildExpression(doubleCastExpressionContext.expression()));
        }
        if (context instanceof PLIModelParser.ParenthesizedExpressionContext parenthesizedExpressionContext) {
            return buildExpression(parenthesizedExpressionContext.expression());
        }
        if (context instanceof PLIModelParser.FunctionCallExpressionContext functionCallExpressionContext) {
            return buildFunctionCall(functionCallExpressionContext.function_call());
        }
        if (context instanceof PLIModelParser.IdentifierExpressionContext identifierExpressionContext) {
            return new IdentifierExpr(identifierExpressionContext.ID().getText());
        }
        if (context instanceof PLIModelParser.DoubleLiteralExpressionContext doubleLiteralExpressionContext) {
            return new DoubleLiteral(Double.parseDouble(doubleLiteralExpressionContext.DOUBLE().getText()));
        }
        if (context instanceof PLIModelParser.IntegerLiteralExpressionContext integerLiteralExpressionContext) {
            return new IntLiteral(Integer.parseInt(integerLiteralExpressionContext.INT().getText()));
        }
        throw new IllegalArgumentException("Unsupported expression: " + context.getClass().getSimpleName());
    }

    private FunctionCallExpr buildFunctionCall(PLIModelParser.Function_callContext context) {
        List<Expression> arguments = new ArrayList<>();
        if (context.expr_collection() != null) {
            for (PLIModelParser.ExpressionContext expressionContext : context.expr_collection().expression()) {
                arguments.add(buildExpression(expressionContext));
            }
        }
        return new FunctionCallExpr(context.ID().getText(), arguments);
    }

    private BinaryOperator parseBinaryOperator(String operator) {
        return switch (operator) {
            case "+" -> BinaryOperator.ADD;
            case "-" -> BinaryOperator.SUB;
            case "*" -> BinaryOperator.MUL;
            case "/" -> BinaryOperator.DIV;
            case "%" -> BinaryOperator.MOD;
            case "<" -> BinaryOperator.LT;
            case "<=" -> BinaryOperator.LE;
            case ">" -> BinaryOperator.GT;
            case ">=" -> BinaryOperator.GE;
            case "=" -> BinaryOperator.EQ;
            case "!=" -> BinaryOperator.NE;
            case "&&" -> BinaryOperator.AND;
            case "||" -> BinaryOperator.OR;
            default -> throw new IllegalArgumentException("Unsupported binary operator: " + operator);
        };
    }

    private UnaryOperator parseUnaryOperator(String operator) {
        return switch (operator) {
            case "+" -> UnaryOperator.PLUS;
            case "-" -> UnaryOperator.MINUS;
            case "!" -> UnaryOperator.NOT;
            default -> throw new IllegalArgumentException("Unsupported unary operator: " + operator);
        };
    }

    private LinearTerm negateNormalizedLinearTerm(LinearTerm term) {
        if (term instanceof LinearFactor factorTerm) {
            LinearFactor factor = factorTerm;
            return new LinearFactor(negateCoefficient(factor.coefficient()), factor.variable());
        }
        if (term instanceof Sum setOfTerms) {
            Expression coefficient = negateCoefficient(setOfTerms.terms().element().coefficient());
            LinearFactor negatedFactor = new LinearFactor(coefficient, setOfTerms.terms().element().variable());
            List<Index> indexes = setOfTerms.terms().indexes();
            Expression filter = setOfTerms.terms().filter();
            return new Sum(new Set_of<LinearFactor>(negatedFactor, indexes, filter));
        }
        return term;
    }

    private Expression negateCoefficient(Expression expression) {
        NormalizedCoefficient normalizedCoefficient = normalizeCoefficient(expression);
        if (normalizedCoefficient.sign() < 0) {
            return normalizedCoefficient.expression();
        }
        if (normalizedCoefficient.expression() instanceof IntLiteral intLiteral) {
            return new IntLiteral(-intLiteral.value());
        }
        if (normalizedCoefficient.expression() instanceof DoubleLiteral doubleLiteral) {
            return new DoubleLiteral(-doubleLiteral.value());
        }
        return new UnaryExpr(UnaryOperator.MINUS, normalizedCoefficient.expression());
    }

    private record NormalizedLinearTerm(int sign, LinearTerm term) {
    }

    private record NormalizedCoefficient(int sign, Expression expression) {
    }
}
