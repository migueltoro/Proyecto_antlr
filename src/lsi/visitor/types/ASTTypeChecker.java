package lsi.visitor.types;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import lsi.ast.AST;
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
// import lsi.ast.constraints.Pair;
import lsi.ast.constraints.PermutationConstraint;
import lsi.ast.constraints.PiecewiseLinearConstraint;
// import lsi.ast.constraints.RelOperator;
import lsi.ast.constraints.RelationalConstraint;
import lsi.ast.declarations.Declaration;
import lsi.ast.declarations.FunctionDeclaration;
import lsi.ast.common.Index;
// import lsi.ast.declarations.Parameter;
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
import lsi.ast.variables.Variable;

public final class ASTTypeChecker {

    private final List<String> errors = new ArrayList<>();
    private final Map<String, Type> variableTypes = new HashMap<>();
    private final Map<String, Type> globalIndexTypes = new HashMap<>();
    private final Map<String, FunctionDeclaration> functionDeclarations = new HashMap<>();
    private final Map<String, Object> constantValues = new HashMap<>();
    private final Deque<Map<String, Type>> localScopes = new ArrayDeque<>();

    public AST visit(AST ast) {
        initEnvironment(ast.declarations());
        collectGlobalIndexVariables(ast);

        List<Declaration> declarations = visitDeclarations(ast.declarations());
        LinearExpr objectiveExpr = visitLinearExpr(ast.objectiveExpr());
        List<Set_of<Constraint>> constraints = visitConstraints(ast.constraints());
        List<Set_of<Bound>> bounds = visitBounds(ast.bounds());
        List<Set_of<Variable>> binaryVars = visitVariableSets(ast.binaryVars());
        List<Set_of<Variable>> integerVars = visitVariableSets(ast.integerVars());
        List<Set_of<Variable>> freeVars = visitVariableSets(ast.freeVars());
        List<Set_of<Variable>> semiContinuousVars = visitVariableSets(ast.semiContinuousVars());

        return new AST(declarations, ast.objective(), objectiveExpr, constraints, bounds, binaryVars, integerVars,
                freeVars, semiContinuousVars);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> errors() {
        return List.copyOf(errors);
    }

    public void printErrors() {
        errors.forEach(System.err::println);
    }

    private void initEnvironment(List<Declaration> declarations) {
        for (Declaration declaration : declarations) {
            if (declaration instanceof VarDeclaration variableDeclaration) {
                variableTypes.put(variableDeclaration.name(), variableDeclaration.type());
            } else if (declaration instanceof FunctionDeclaration functionDeclaration) {
                functionDeclarations.put(functionDeclaration.name(), functionDeclaration);
            }
        }
    }

    private void collectGlobalIndexVariables(AST ast) {
        for (Set_of<Variable> variableSet : ast.binaryVars()) {
            for (Index index : variableSet.indexes()) {
                globalIndexTypes.put(index.variable(), new IntegerType());
            }
        }
        for (Set_of<Variable> variableSet : ast.integerVars()) {
            for (Index index : variableSet.indexes()) {
                globalIndexTypes.put(index.variable(), new IntegerType());
            }
        }
        for (Set_of<Variable> variableSet : ast.freeVars()) {
            for (Index index : variableSet.indexes()) {
                globalIndexTypes.put(index.variable(), new IntegerType());
            }
        }
        for (Set_of<Variable> variableSet : ast.semiContinuousVars()) {
            for (Index index : variableSet.indexes()) {
                globalIndexTypes.put(index.variable(), new IntegerType());
            }
        }
    }

    private List<Declaration> visitDeclarations(List<Declaration> declarations) {
        List<Declaration> visited = new ArrayList<>();
        for (Declaration declaration : declarations) {
            if (declaration instanceof VarDeclaration variableDeclaration) {
                Expression initializer = variableDeclaration.initializer();
                ExpressionResult result = initializer != null ? visitExpression(initializer) : null;
                Expression visitedInitializer = result != null ? result.expression() : null;
                if (result != null && result.value() != null) {
                    constantValues.put(variableDeclaration.name(), result.value());
                }
                visited.add(new VarDeclaration(variableDeclaration.type(), variableDeclaration.name(), visitedInitializer));
                continue;
            }
            if (declaration instanceof FunctionDeclaration functionDeclaration) {
                visited.add(functionDeclaration);
                continue;
            }
            visited.add(declaration);
        }
        return visited;
    }

    private List<Set_of<Constraint>> visitConstraints(List<Set_of<Constraint>> constraints) {
        List<Set_of<Constraint>> visited = new ArrayList<>();
        for (Set_of<Constraint> constraint : constraints) {
            visited.add(visitSetOfConstraint(constraint));
        }
        return visited;
    }

    private Constraint visitConstraint(Constraint constraint) {
        if (constraint instanceof RelationalConstraint relationalConstraint) {
            return new RelationalConstraint(visitLinearExpr(relationalConstraint.left()), relationalConstraint.op(),
                    visitExpression(relationalConstraint.right()).expression());
        }
        if (constraint instanceof OrConstraint orConstraint) {
            List<RelationalConstraint> relationalConstraints = new ArrayList<>();
            for (RelationalConstraint relationalConstraint : orConstraint.constraints()) {
                relationalConstraints.add((RelationalConstraint) visitConstraint(relationalConstraint));
            }
            return new OrConstraint(orConstraint.operator(), orConstraint.threshold(), relationalConstraints);
        }
        if (constraint instanceof ImplicationConstraint implicationConstraint) {
            return new ImplicationConstraint((RelationalConstraint) visitConstraint(implicationConstraint.antecedent()),
                    (RelationalConstraint) visitConstraint(implicationConstraint.consequent()));
        }
        if (constraint instanceof DifferentValueConstraint differentValueConstraint) {
            return new DifferentValueConstraint(visitVariable(differentValueConstraint.left()),
                    visitVariable(differentValueConstraint.right()));
        }
        if (constraint instanceof IndicatorConstraint indicatorConstraint) {
            return new IndicatorConstraint(visitVariable(indicatorConstraint.indicator()), indicatorConstraint.value(),
                    (RelationalConstraint) visitConstraint(indicatorConstraint.consequence()));
        }
        if (constraint instanceof EqualsConstraint equalsConstraint) {
            return new EqualsConstraint(visitVariable(equalsConstraint.left()), visitVariable(equalsConstraint.right()));
        }
        if (constraint instanceof AllDifferentConstraint allDifferentConstraint) {
            return new AllDifferentConstraint(visitSetOfVariable(allDifferentConstraint.variables()));
        }
        if (constraint instanceof PermutationConstraint permutationConstraint) {
            return new PermutationConstraint(visitSetOfVariable(permutationConstraint.variables()),
                    visitSetOfExpression(permutationConstraint.values()));
        }
        if (constraint instanceof MembershipConstraint membershipConstraint) {
            return new MembershipConstraint(visitVariable(membershipConstraint.variable()),
                    visitSetOfExpression(membershipConstraint.set()));
        }
        if (constraint instanceof MaxConstraint maxConstraint) {
            return new MaxConstraint(visitVariable(maxConstraint.result()), visitSetOfVariable(maxConstraint.variables()));
        }
        if (constraint instanceof MinConstraint minConstraint) {
            return new MinConstraint(visitVariable(minConstraint.result()), visitSetOfVariable(minConstraint.variables()));
        }
        if (constraint instanceof OrBinaryConstraint orBinaryConstraint) {
            return new OrBinaryConstraint(visitVariable(orBinaryConstraint.result()),
                    visitSetOfConstraint(orBinaryConstraint.constraints()));
        }
        if (constraint instanceof AndBinaryConstraint andBinaryConstraint) {
            return new AndBinaryConstraint(visitVariable(andBinaryConstraint.result()),
                    visitSetOfConstraint(andBinaryConstraint.constraints()));
        }
        if (constraint instanceof AbsConstraint absConstraint) {
            return new AbsConstraint(visitVariable(absConstraint.result()), visitSetOfVariable(absConstraint.variables()));
        }
        if (constraint instanceof PiecewiseLinearConstraint piecewiseLinearConstraint) {
            return new PiecewiseLinearConstraint(visitVariable(piecewiseLinearConstraint.result()),
                    visitVariable(piecewiseLinearConstraint.source()), piecewiseLinearConstraint.points());
        }
        return constraint;
    }

    private List<Set_of<Bound>> visitBounds(List<Set_of<Bound>> bounds) {
        List<Set_of<Bound>> visited = new ArrayList<>();
        for (Set_of<Bound> bound : bounds) {
            visited.add(visitSetOfBound(bound));
        }
        return visited;
    }

    private Bound visitBound(Bound bound) {
        if (bound instanceof OneSideBound oneSideBound) {
            return new OneSideBound(visitVariable(oneSideBound.variable()), oneSideBound.operator(),
                    visitExpression(oneSideBound.expression()).expression());
        }
        if (bound instanceof TwoSideBound twoSideBound) {
            return new TwoSideBound(visitExpression(twoSideBound.lower()).expression(), visitVariable(twoSideBound.variable()),
                    visitExpression(twoSideBound.upper()).expression());
        }
        return bound;
    }

    private List<Set_of<Variable>> visitVariableSets(List<Set_of<Variable>> sets) {
        List<Set_of<Variable>> visited = new ArrayList<>();
        for (Set_of<Variable> variableSet : sets) {
            visited.add(visitVariableSet(variableSet));
        }
        return visited;
    }

    private Set_of<Variable> visitVariableSet(Set_of<Variable> variableSet) {
        pushScope();
        List<Index> indexes = new ArrayList<>();
        for (Index index : variableSet.indexes()) {
            indexes.add(visitIndex(index));
        }
        for (Index visitedIndex : indexes) {
            declareLocal(visitedIndex.variable(), new IntegerType());
        }
        Variable variable = visitVariable(variableSet.element());
        Expression filter = variableSet.filter() == null ? null : visitExpression(variableSet.filter()).expression();
        popScope();
        return new Set_of<Variable>(variable, indexes, filter);
    }

    private Variable visitVariable(Variable variable) {
        List<Expression> indexes = new ArrayList<>();
        for (Expression index : variable.indexes()) {
            indexes.add(visitExpression(index).expression());
        }
        return new Variable(variable.name(), indexes);
    }

    private List<Index> visitIndex(List<Index> indexes) {
        List<Index> visited = new ArrayList<>();
        for (Index index : indexes) {
            visited.add(visitIndex(index));
        }
        return visited;
    }

    private Index visitIndex(Index index) {
        return new Index(index.variable(), visitExpression(index.lowerBound()).expression(),
                visitExpression(index.upperBound()).expression());
    }

    private LinearExpr visitLinearExpr(LinearExpr linearExpr) {
        List<LinearTerm> terms = new ArrayList<>();
        for (LinearTerm term : linearExpr.terms()) {
            terms.add(visitLinearTerm(term));
        }
        return new LinearExpr(terms);
    }

    private LinearTerm visitLinearTerm(LinearTerm term) {
        if (term instanceof LinearFactor factorTerm) {
            return visitLinearFactor(factorTerm);
        }
        if (term instanceof Sum indexedSumTerm) {
            return new Sum(visitSetOfLinearFactor(indexedSumTerm.terms()));
        }
        return term;
    }

    private LinearFactor visitLinearFactor(LinearFactor factor) {
        Expression coefficient = factor.coefficient() != null ? visitExpression(factor.coefficient()).expression() : null;
        return new LinearFactor(coefficient, visitVariable(factor.variable()));
    }

    private Set_of<LinearFactor> visitSetOfLinearFactor(Set_of<LinearFactor> set) {
        return new Set_of<>(visitLinearFactor(set.element()), visitIndex(set.indexes()),
                set.filter() == null ? null : visitExpression(set.filter()).expression());
    }

    private Set_of<Bound> visitSetOfBound(Set_of<Bound> set) {
        return visitSetOf(set, this::visitBound);
    }

    private Set_of<Variable> visitSetOfVariable(Set_of<Variable> set) {
        return visitSetOf(set, this::visitVariable);
    }

    private Set_of<Constraint> visitSetOfConstraint(Set_of<Constraint> set) {
        return visitSetOf(set, this::visitConstraint);
    }

    private Set_of<Expression> visitSetOfExpression(Set_of<Expression> set) {
        return visitSetOf(set, expression -> visitExpression(expression).expression());
    }

    private <T> Set_of<T> visitSetOf(Set_of<T> set, Function<T, T> elementMapper) {
        pushScope();
        List<Index> indexes = new ArrayList<>();
        for (Index index : set.indexes()) {
            indexes.add(visitIndex(index));
        }
        for (Index visitedIndex : indexes) {
            declareLocal(visitedIndex.variable(), new IntegerType());
        }
        T element = elementMapper.apply(set.element());
        Expression filter = set.filter() == null ? null : visitExpression(set.filter()).expression();
        popScope();
        return new Set_of<>(element, indexes, filter);
    }

    private void pushScope() {
        localScopes.push(new HashMap<>());
    }

    private void popScope() {
        localScopes.pop();
    }

    private void declareLocal(String name, Type type) {
        if (!localScopes.isEmpty()) {
            localScopes.peek().put(name, type);
        }
    }

    private Type lookupType(String name) {
        for (Map<String, Type> scope : localScopes) {
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        Type type = variableTypes.get(name);
        if (type != null) {
            return type;
        }
        return globalIndexTypes.get(name);
    }

    private ExpressionResult visitExpression(Expression expression) {
        if (expression instanceof IntLiteral intLiteral) {
            return new ExpressionResult(intLiteral, new IntegerType(), Integer.valueOf(intLiteral.value()));
        }
        if (expression instanceof DoubleLiteral doubleLiteral) {
            return new ExpressionResult(doubleLiteral, new DoubleType(), Double.valueOf(doubleLiteral.value()));
        }
        if (expression instanceof BooleanLiteral booleanLiteral) {
            return new ExpressionResult(booleanLiteral, new BooleanType(), Boolean.valueOf(booleanLiteral.value()));
        }
        if (expression instanceof IdentifierExpr identifierExpr) {
            String name = identifierExpr.name();
            Type type = lookupType(name);
            if (type == null) {
                errors.add("Variable '" + name + "' no declarada.");
                return new ExpressionResult(identifierExpr, new IntegerType(), null);
            }
            Object constantValue = constantValues.get(name);
            Expression resultExpression = constantValue != null ? literalFromValue(constantValue) : identifierExpr;
            return new ExpressionResult(resultExpression, type, constantValue);
        }
        if (expression instanceof FunctionCallExpr functionCallExpr) {
            List<Expression> visitedArguments = new ArrayList<>();
            List<ExpressionResult> argumentResults = new ArrayList<>();
            for (Expression argument : functionCallExpr.arguments()) {
                ExpressionResult argumentResult = visitExpression(argument);
                visitedArguments.add(argumentResult.expression());
                argumentResults.add(argumentResult);
            }
            FunctionDeclaration signature = functionDeclarations.get(functionCallExpr.name());
            if (signature == null) {
                errors.add("Función '" + functionCallExpr.name() + "' no declarada.");
                return new ExpressionResult(new FunctionCallExpr(functionCallExpr.name(), visitedArguments), new IntegerType(), null);
            }
            if (signature.parameters().size() != argumentResults.size()) {
                errors.add("Llamada a función '" + functionCallExpr.name() + "' con número de argumentos incorrecto: se esperaban "
                        + signature.parameters().size() + " y se recibieron " + argumentResults.size() + ".");
            }
            for (int i = 0; i < Math.min(signature.parameters().size(), argumentResults.size()); i++) {
                Type expectedType = signature.parameters().get(i).type();
                Type actualType = argumentResults.get(i).type();
                if (!isAssignable(actualType, expectedType)) {
                    errors.add("Tipo inválido en el argumento " + (i + 1) + " de la llamada a '" + functionCallExpr.name()
                            + "': se esperaba " + typeName(expectedType) + " y se encontró " + typeName(actualType) + ".");
                }
            }
            return new ExpressionResult(new FunctionCallExpr(functionCallExpr.name(), visitedArguments), signature.returnType(), null);
        }
        if (expression instanceof CastExpr castExpr) {
            ExpressionResult value = visitExpression(castExpr.expression());
            Type targetType = castExpr.targetType();
            if (targetType instanceof IntegerType) {
                if (value.type() instanceof IntegerType) {
                    return new ExpressionResult(new CastExpr(targetType, value.expression()), targetType, value.value());
                }
                if (value.type() instanceof DoubleType && value.value() instanceof Double doubleValue) {
                    int casted = (int) doubleValue.doubleValue();
                    return new ExpressionResult(new IntLiteral(casted), targetType, Integer.valueOf(casted));
                }
                errors.add("Cast inválido a Integer desde " + typeName(value.type()) + ".");
                return new ExpressionResult(new CastExpr(targetType, value.expression()), targetType, null);
            }
            if (targetType instanceof DoubleType) {
                if (value.type() instanceof DoubleType) {
                    return new ExpressionResult(new CastExpr(targetType, value.expression()), targetType, value.value());
                }
                if (value.type() instanceof IntegerType && value.value() instanceof Integer intValue) {
                    double casted = intValue.doubleValue();
                    return new ExpressionResult(new DoubleLiteral(casted), targetType, Double.valueOf(casted));
                }
                errors.add("Cast inválido a Double desde " + typeName(value.type()) + ".");
                return new ExpressionResult(new CastExpr(targetType, value.expression()), targetType, null);
            }
            if (!isSameType(value.type(), targetType)) {
                errors.add("Cast inválido a " + typeName(targetType) + " desde " + typeName(value.type()) + ".");
            }
            return new ExpressionResult(new CastExpr(targetType, value.expression()), targetType, null);
        }
        if (expression instanceof UnaryExpr unaryExpr) {
            ExpressionResult operand = visitExpression(unaryExpr.expression());
            UnaryOperator operator = unaryExpr.operator();
            switch (operator) {
                case PLUS, MINUS -> {
                    if (!isNumeric(operand.type())) {
                        errors.add("Operador unario '" + operator + "' aplicado a tipo no numérico: " + typeName(operand.type()) + ".");
                        return new ExpressionResult(new UnaryExpr(operator, operand.expression()), operand.type(), null);
                    }
                    if (operand.value() != null) {
                        if (operand.type() instanceof IntegerType && operand.value() instanceof Integer intValue) {
                            int result = operator == UnaryOperator.MINUS ? -intValue.intValue() : intValue.intValue();
                            return new ExpressionResult(new IntLiteral(result), operand.type(), Integer.valueOf(result));
                        }
                        if (operand.type() instanceof DoubleType && operand.value() instanceof Double doubleValue) {
                            double result = operator == UnaryOperator.MINUS ? -doubleValue.doubleValue() : doubleValue.doubleValue();
                            return new ExpressionResult(new DoubleLiteral(result), operand.type(), Double.valueOf(result));
                        }
                    }
                    return new ExpressionResult(new UnaryExpr(operator, operand.expression()), operand.type(), null);
                }
                case NOT -> {
                    if (!(operand.type() instanceof BooleanType)) {
                        errors.add("Operador '!' aplicado a tipo no booleano: " + typeName(operand.type()) + ".");
                        return new ExpressionResult(new UnaryExpr(operator, operand.expression()), new BooleanType(), null);
                    }
                    if (operand.value() instanceof Boolean boolValue) {
                        return new ExpressionResult(new BooleanLiteral(!boolValue.booleanValue()), new BooleanType(), Boolean.valueOf(!boolValue.booleanValue()));
                    }
                    return new ExpressionResult(new UnaryExpr(operator, operand.expression()), new BooleanType(), null);
                }
                default -> {
                    errors.add("Operador unario desconocido: " + operator + ".");
                    return new ExpressionResult(new UnaryExpr(operator, operand.expression()), operand.type(), null);
                }
            }
        }
        if (expression instanceof BinaryExpr binaryExpr) {
            ExpressionResult left = visitExpression(binaryExpr.left());
            ExpressionResult right = visitExpression(binaryExpr.right());
            BinaryOperator operator = binaryExpr.operator();
            Type leftType = left.type();
            Type rightType = right.type();
            Expression visitedLeft = left.expression();
            Expression visitedRight = right.expression();
            boolean leftConstant = left.value() != null;
            boolean rightConstant = right.value() != null;

            switch (operator) {
                case ADD, SUB, MUL, DIV, MOD -> {
                    if (!isNumeric(leftType) || !isNumeric(rightType)) {
                        errors.add("Operador aritmético '" + operator + "' requiere operandos numéricos. Se encontraron "
                                + typeName(leftType) + " y " + typeName(rightType) + ".");
                        return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new IntegerType(), null);
                    }
                    Type resultType = unifyNumeric(leftType, rightType);
                    if (leftConstant && rightConstant) {
                        Object value = computeNumericOperation(left.value(), right.value(), operator, resultType);
                        return literalFromConstant(value, resultType);
                    }
                    return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), resultType, null);
                }
                case LT, LE, GT, GE -> {
                    if (!isNumeric(leftType) || !isNumeric(rightType)) {
                        errors.add("Operador relacional '" + operator + "' requiere operandos numéricos. Se encontraron "
                                + typeName(leftType) + " y " + typeName(rightType) + ".");
                        return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new BooleanType(), null);
                    }
                    if (leftConstant && rightConstant) {
                        boolean value = compareNumeric(left.value(), right.value(), operator);
                        return new ExpressionResult(new BooleanLiteral(value), new BooleanType(), Boolean.valueOf(value));
                    }
                    return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new BooleanType(), null);
                }
                case EQ, NE -> {
                    if (!isEqualityCompatible(leftType, rightType)) {
                        errors.add("Operador de igualdad '" + operator + "' aplicado a tipos incompatibles: " + typeName(leftType)
                                + " y " + typeName(rightType) + ".");
                        return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new BooleanType(), null);
                    }
                    if (leftConstant && rightConstant) {
                        boolean result = compareEquality(left.value(), right.value(), operator);
                        return new ExpressionResult(new BooleanLiteral(result), new BooleanType(), Boolean.valueOf(result));
                    }
                    return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new BooleanType(), null);
                }
                case AND, OR -> {
                    if (!(leftType instanceof BooleanType) || !(rightType instanceof BooleanType)) {
                        errors.add("Operador booleano '" + operator + "' requiere operandos booleanos. Se encontraron "
                                + typeName(leftType) + " y " + typeName(rightType) + ".");
                        return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new BooleanType(), null);
                    }
                    if (leftConstant && rightConstant && left.value() instanceof Boolean leftBool
                            && right.value() instanceof Boolean rightBool) {
                        boolean result = operator == BinaryOperator.AND ? leftBool.booleanValue() && rightBool.booleanValue()
                                : leftBool.booleanValue() || rightBool.booleanValue();
                        return new ExpressionResult(new BooleanLiteral(result), new BooleanType(), Boolean.valueOf(result));
                    }
                    return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new BooleanType(), null);
                }
                default -> {
                    errors.add("Operador desconocido: " + operator + ".");
                    return new ExpressionResult(new BinaryExpr(visitedLeft, operator, visitedRight), new IntegerType(), null);
                }
            }
        }

        return new ExpressionResult(expression, new IntegerType(), null);
    }

    private boolean isAssignable(Type actual, Type expected) {
        if (expected instanceof DoubleType && actual instanceof IntegerType) {
            return true;
        }
        return isSameType(actual, expected);
    }

    private boolean isSameType(Type left, Type right) {
        return left.getClass().equals(right.getClass());
    }

    private boolean isNumeric(Type type) {
        return type instanceof IntegerType || type instanceof DoubleType;
    }

    private Type unifyNumeric(Type left, Type right) {
        if (left instanceof DoubleType || right instanceof DoubleType) {
            return new DoubleType();
        }
        return new IntegerType();
    }

    private ExpressionResult literalFromConstant(Object value, Type resultType) {
        if (value instanceof Integer intValue) {
            return new ExpressionResult(new IntLiteral(intValue.intValue()), resultType, Integer.valueOf(intValue.intValue()));
        }
        if (value instanceof Double doubleValue) {
            return new ExpressionResult(new DoubleLiteral(doubleValue.doubleValue()), resultType, Double.valueOf(doubleValue.doubleValue()));
        }
        if (value instanceof Boolean booleanValue) {
            return new ExpressionResult(new BooleanLiteral(booleanValue.booleanValue()), new BooleanType(), Boolean.valueOf(booleanValue.booleanValue()));
        }
        return new ExpressionResult(new IntLiteral(0), resultType, null);
    }

    private Expression literalFromValue(Object value) {
        if (value instanceof Integer intValue) {
            return new IntLiteral(intValue.intValue());
        }
        if (value instanceof Double doubleValue) {
            return new DoubleLiteral(doubleValue.doubleValue());
        }
        if (value instanceof Boolean booleanValue) {
            return new BooleanLiteral(booleanValue.booleanValue());
        }
        return null;
    }

    private boolean compareNumeric(Object leftValue, Object rightValue, BinaryOperator operator) {
        double left = toDouble(leftValue);
        double right = toDouble(rightValue);
        return switch (operator) {
            case LT -> left < right;
            case LE -> left <= right;
            case GT -> left > right;
            case GE -> left >= right;
            default -> false;
        };
    }

    private boolean compareEquality(Object leftValue, Object rightValue, BinaryOperator operator) {
        boolean equal;
        if (leftValue instanceof Number leftNumber && rightValue instanceof Number rightNumber) {
            equal = Double.compare(toDouble(leftNumber), toDouble(rightNumber)) == 0;
        } else {
            equal = leftValue == null ? rightValue == null : leftValue.equals(rightValue);
        }
        return operator == BinaryOperator.EQ ? equal : !equal;
    }

    private Number computeNumericOperation(Object leftValue, Object rightValue, BinaryOperator operator, Type resultType) {
        if (resultType instanceof IntegerType) {
            int left = ((Number) leftValue).intValue();
            int right = ((Number) rightValue).intValue();
            return switch (operator) {
                case ADD -> left + right;
                case SUB -> left - right;
                case MUL -> left * right;
                case DIV -> right != 0 ? left / right : 0;
                case MOD -> right != 0 ? left % right : 0;
                default -> 0;
            };
        }
        double left = toDouble(leftValue);
        double right = toDouble(rightValue);
        return switch (operator) {
            case ADD -> left + right;
            case SUB -> left - right;
            case MUL -> left * right;
            case DIV -> right != 0 ? left / right : Double.NaN;
            case MOD -> right != 0 ? left % right : Double.NaN;
            default -> 0.0;
        };
    }

    private double toDouble(Object value) {
        return value instanceof Number number ? number.doubleValue() : 0.0;
    }

    private boolean isEqualityCompatible(Type left, Type right) {
        if (isNumeric(left) && isNumeric(right)) {
            return true;
        }
        return isSameType(left, right);
    }

    private String typeName(Type type) {
        if (type instanceof IntegerType) {
            return "Integer";
        }
        if (type instanceof DoubleType) {
            return "Double";
        }
        if (type instanceof BooleanType) {
            return "Boolean";
        }
        if (type instanceof StringType) {
            return "String";
        }
        return "Unknown";
    }

    private static final class ExpressionResult {
        private final Expression expression;
        private final Type type;
        private final Object value;

        private ExpressionResult(Expression expression, Type type, Object value) {
            this.expression = expression;
            this.type = type;
            this.value = value;
        }

        public Expression expression() {
            return expression;
        }

        public Type type() {
            return type;
        }

        public Object value() {
            return value;
        }
    }
}
