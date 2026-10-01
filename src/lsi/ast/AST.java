package lsi.ast;

import java.util.List;
import java.util.Objects;

public record AST(
        List<Declaration> declarations,
        Objective objective,
        LinearExpr objectiveExpr,
        List<Set_of<Constraint>> constraints,
        List<Set_of<Bound>> bounds,
        List<Set_of<Variable>> binaryVars,
        List<Set_of<Variable>> integerVars,
        List<Set_of<Variable>> freeVars,
        List<Set_of<Variable>> semiContinuousVars) {

    public static AST of(
            List<Declaration> declarations,
            Objective objective,
            LinearExpr objectiveExpr,
            List<Set_of<Constraint>> constraints,
            List<Set_of<Bound>> bounds,
            List<Set_of<Variable>> binaryVars,
            List<Set_of<Variable>> integerVars,
            List<Set_of<Variable>> freeVars,
            List<Set_of<Variable>> semiContinuousVars) {
        Objects.requireNonNull(declarations, "declarations cannot be null");
        Objects.requireNonNull(objective, "objective cannot be null");
        Objects.requireNonNull(objectiveExpr, "objectiveExpr cannot be null");
        Objects.requireNonNull(constraints, "constraints cannot be null");
        Objects.requireNonNull(bounds, "bounds cannot be null");
        Objects.requireNonNull(binaryVars, "binaryVars cannot be null");
        Objects.requireNonNull(integerVars, "integerVars cannot be null");
        Objects.requireNonNull(freeVars, "freeVars cannot be null");
        Objects.requireNonNull(semiContinuousVars, "semiContinuousVars cannot be null");
        return new AST(declarations, objective, objectiveExpr, constraints, bounds,
                binaryVars, integerVars, freeVars, semiContinuousVars);
    }

    public enum Objective {
        MIN,
        MAX
    }

    public sealed interface Declaration permits VarDeclaration, FunctionDeclaration {
    }

    public record VarDeclaration(Type type, String name, Expression initializer) implements Declaration {
        public static VarDeclaration of(Type type, String name, Expression initializer) {
            Objects.requireNonNull(type, "type cannot be null");
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(initializer, "initializer cannot be null");
            return new VarDeclaration(type, name, initializer);
        }
    }

    public record FunctionDeclaration(Type returnType, String name, List<Parameter> parameters)
            implements Declaration {
        public static FunctionDeclaration of(Type returnType, String name, List<Parameter> parameters) {
            Objects.requireNonNull(returnType, "returnType cannot be null");
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(parameters, "parameters cannot be null");
            return new FunctionDeclaration(returnType, name, parameters);
        }
    }

    public record IndexDeclaration(String variable, Expression lowerBound, Expression upperBound) {
        public static IndexDeclaration of(String variable, Expression lowerBound, Expression upperBound) {
            Objects.requireNonNull(variable, "variable cannot be null");
            Objects.requireNonNull(lowerBound, "lowerBound cannot be null");
            Objects.requireNonNull(upperBound, "upperBound cannot be null");
            return new IndexDeclaration(variable, lowerBound, upperBound);
        }
    }

    public record Parameter(Type type, String name) {
        public static Parameter of(Type type, String name) {
            Objects.requireNonNull(type, "type cannot be null");
            Objects.requireNonNull(name, "name cannot be null");
            return new Parameter(type, name);
        }
    }

    public sealed interface Type permits IntegerType, DoubleType, BooleanType, StringType {
    }

    public record IntegerType() implements Type {
        public static IntegerType of() {
            return new IntegerType();
        }
    }

    public record DoubleType() implements Type {
        public static DoubleType of() {
            return new DoubleType();
        }
    }

    public record BooleanType() implements Type {
        public static BooleanType of() {
            return new BooleanType();
        }
    }

    public record StringType() implements Type {
        public static StringType of() {
            return new StringType();
        }
    }

    public enum EType {
        INTEGER,
        DOUBLE,
        BOOLEAN,
        STRING
    }

    public sealed interface Expression permits BinaryExpr, UnaryExpr, CastExpr, IdentifierExpr,
            IntLiteral, DoubleLiteral, BooleanLiteral, FunctionCallExpr {
    }

    public record BinaryExpr(Expression left, BinaryOperator operator, Expression right)
            implements Expression {
        public static BinaryExpr of(Expression left, BinaryOperator operator, Expression right) {
            Objects.requireNonNull(left, "left cannot be null");
            Objects.requireNonNull(operator, "operator cannot be null");
            Objects.requireNonNull(right, "right cannot be null");
            return new BinaryExpr(left, operator, right);
        }
    }

    public enum BinaryOperator {
        ADD,
        SUB,
        MUL,
        DIV,
        MOD,
        LT,
        LE,
        GT,
        GE,
        EQ,
        NE,
        AND,
        OR
    }

    public record UnaryExpr(UnaryOperator operator, Expression expression) implements Expression {
        public static UnaryExpr of(UnaryOperator operator, Expression expression) {
            Objects.requireNonNull(operator, "operator cannot be null");
            Objects.requireNonNull(expression, "expression cannot be null");
            return new UnaryExpr(operator, expression);
        }
    }

    public enum UnaryOperator {
        PLUS,
        MINUS,
        NOT
    }

    public record CastExpr(Type targetType, Expression expression) implements Expression {
        public static CastExpr of(Type targetType, Expression expression) {
            Objects.requireNonNull(targetType, "targetType cannot be null");
            Objects.requireNonNull(expression, "expression cannot be null");
            return new CastExpr(targetType, expression);
        }
    }

    public record IdentifierExpr(String name) implements Expression {
        public static IdentifierExpr of(String name) {
            Objects.requireNonNull(name, "name cannot be null");
            return new IdentifierExpr(name);
        }
    }

    public record IntLiteral(int value) implements Expression {
        public static IntLiteral of(int value) {
            return new IntLiteral(value);
        }
    }

    public record DoubleLiteral(double value) implements Expression {
        public static DoubleLiteral of(double value) {
            return new DoubleLiteral(value);
        }
    }

    public record BooleanLiteral(boolean value) implements Expression {
        public static BooleanLiteral of(boolean value) {
            return new BooleanLiteral(value);
        }
    }

    public record FunctionCallExpr(String name, List<Expression> arguments) implements Expression {
        public static FunctionCallExpr of(String name, List<Expression> arguments) {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(arguments, "arguments cannot be null");
            return new FunctionCallExpr(name, arguments);
        }
    }

    public record Variable(String name, List<Expression> indexes) {
        public static Variable of(String name, List<Expression> indexes) {
            Objects.requireNonNull(name, "name cannot be null");
            Objects.requireNonNull(indexes, "indexes cannot be null");
            return new Variable(name, indexes);
        }
    }

    public record VariableSet(Variable variable, List<IndexDeclaration> indexes, Expression filter) {
        public static VariableSet of(Variable variable, List<IndexDeclaration> indexes, Expression filter) {
            Objects.requireNonNull(variable, "variable cannot be null");
            Objects.requireNonNull(indexes, "indexes cannot be null");
            Objects.requireNonNull(filter, "filter cannot be null");
            return new VariableSet(variable, indexes, filter);
        }
    }

    public record Index(String variable, Expression lowerBound, Expression upperBound) {
        public static Index of(String variable, Expression lowerBound, Expression upperBound) {
            Objects.requireNonNull(variable, "variable cannot be null");
            Objects.requireNonNull(lowerBound, "lowerBound cannot be null");
            Objects.requireNonNull(upperBound, "upperBound cannot be null");
            return new Index(variable, lowerBound, upperBound);
        }
    }

    public record Set_of<T>(T element, List<Index> indexes, Expression filter) {
        public static <T> Set_of<T> of(T element, List<Index> indexes, Expression filter) {
            Objects.requireNonNull(element, "element cannot be null");
            Objects.requireNonNull(indexes, "indexes cannot be null");
            Objects.requireNonNull(filter, "filter cannot be null");
            return new Set_of<>(element, indexes, filter);
        }
    }

    public sealed interface LinearTerm permits LinearFactor, Sum {
    }

    public record LinearExpr(List<LinearTerm> terms) {
        public static LinearExpr of(List<LinearTerm> terms) {
            Objects.requireNonNull(terms, "terms cannot be null");
            return new LinearExpr(terms);
        }
    }

    public record LinearFactor(Expression coefficient, Variable variable) implements LinearTerm {
        public static LinearFactor of(Expression coefficient, Variable variable) {
            Objects.requireNonNull(coefficient, "coefficient cannot be null");
            Objects.requireNonNull(variable, "variable cannot be null");
            return new LinearFactor(coefficient, variable);
        }
    }

    public record Sum(Set_of<LinearFactor> terms) implements LinearTerm {
        public static Sum of(Set_of<LinearFactor> terms) {
            Objects.requireNonNull(terms, "terms cannot be null");
            return new Sum(terms);
        }
    }

    public sealed interface Bound permits OneSideBound, TwoSideBound {
    }

    public record OneSideBound(Variable variable, RelOperator operator, Expression expression) implements Bound {
        public static OneSideBound of(Variable variable, RelOperator operator, Expression expression) {
            Objects.requireNonNull(variable, "variable cannot be null");
            Objects.requireNonNull(operator, "operator cannot be null");
            Objects.requireNonNull(expression, "expression cannot be null");
            return new OneSideBound(variable, operator, expression);
        }
    }

    public record TwoSideBound(Expression lower, Variable variable, Expression upper) implements Bound {
        public static TwoSideBound of(Expression lower, Variable variable, Expression upper) {
            Objects.requireNonNull(lower, "lower cannot be null");
            Objects.requireNonNull(variable, "variable cannot be null");
            Objects.requireNonNull(upper, "upper cannot be null");
            return new TwoSideBound(lower, variable, upper);
        }
    }

    public sealed interface Constraint permits RelationalConstraint, OrConstraint, ImplicationConstraint,
            DifferentValueConstraint, IndicatorConstraint, EqualsConstraint, AllDifferentConstraint,
            PermutationConstraint, MembershipConstraint, MaxConstraint, MinConstraint, OrBinaryConstraint,
            AndBinaryConstraint, AbsConstraint, PiecewiseLinearConstraint {
    }

    public record RelationalConstraint(LinearExpr left, RelOperator op, Expression right) implements Constraint {
        public static RelationalConstraint of(LinearExpr left, RelOperator op, Expression right) {
            Objects.requireNonNull(left, "left cannot be null");
            Objects.requireNonNull(op, "op cannot be null");
            Objects.requireNonNull(right, "right cannot be null");
            return new RelationalConstraint(left, op, right);
        }
    }

    public record OrConstraint(RelOperator operator, int threshold, List<RelationalConstraint> constraints)
            implements Constraint {
        public static OrConstraint of(RelOperator operator, int threshold,
                List<RelationalConstraint> constraints) {
            Objects.requireNonNull(operator, "operator cannot be null");
            Objects.requireNonNull(constraints, "constraints cannot be null");
            return new OrConstraint(operator, threshold, constraints);
        }
    }

    public record ImplicationConstraint(RelationalConstraint antecedent, RelationalConstraint consequent)
            implements Constraint {
        public static ImplicationConstraint of(RelationalConstraint antecedent, RelationalConstraint consequent) {
            Objects.requireNonNull(antecedent, "antecedent cannot be null");
            Objects.requireNonNull(consequent, "consequent cannot be null");
            return new ImplicationConstraint(antecedent, consequent);
        }
    }

    public record DifferentValueConstraint(Variable left, Variable right) implements Constraint {
        public static DifferentValueConstraint of(Variable left, Variable right) {
            Objects.requireNonNull(left, "left cannot be null");
            Objects.requireNonNull(right, "right cannot be null");
            return new DifferentValueConstraint(left, right);
        }
    }

    public record IndicatorConstraint(Variable indicator, int value, RelationalConstraint consequence)
            implements Constraint {
        public static IndicatorConstraint of(Variable indicator, int value, RelationalConstraint consequence) {
            Objects.requireNonNull(indicator, "indicator cannot be null");
            Objects.requireNonNull(consequence, "consequence cannot be null");
            return new IndicatorConstraint(indicator, value, consequence);
        }
    }

    public record EqualsConstraint(Variable left, Variable right) implements Constraint {
        public static EqualsConstraint of(Variable left, Variable right) {
            Objects.requireNonNull(left, "left cannot be null");
            Objects.requireNonNull(right, "right cannot be null");
            return new EqualsConstraint(left, right);
        }
    }

    public record AllDifferentConstraint(Set_of<Variable> variables) implements Constraint {
        public static AllDifferentConstraint of(Set_of<Variable> variables) {
            Objects.requireNonNull(variables, "variables cannot be null");
            return new AllDifferentConstraint(variables);
        }
    }

    public record PermutationConstraint(Set_of<Variable> variables, Set_of<Expression> values)
            implements Constraint {
        public static PermutationConstraint of(Set_of<Variable> variables, Set_of<Expression> values) {
            Objects.requireNonNull(variables, "variables cannot be null");
            Objects.requireNonNull(values, "values cannot be null");
            return new PermutationConstraint(variables, values);
        }
    }

    public record MembershipConstraint(Variable variable, Set_of<Expression> set) implements Constraint {
        public static MembershipConstraint of(Variable variable, Set_of<Expression> set) {
            Objects.requireNonNull(variable, "variable cannot be null");
            Objects.requireNonNull(set, "set cannot be null");
            return new MembershipConstraint(variable, set);
        }
    }

    public record MaxConstraint(Variable result, Set_of<Variable> variables) implements Constraint {
        public static MaxConstraint of(Variable result, Set_of<Variable> variables) {
            Objects.requireNonNull(result, "result cannot be null");
            Objects.requireNonNull(variables, "variables cannot be null");
            return new MaxConstraint(result, variables);
        }
    }

    public record MinConstraint(Variable result, Set_of<Variable> variables) implements Constraint {
        public static MinConstraint of(Variable result, Set_of<Variable> variables) {
            Objects.requireNonNull(result, "result cannot be null");
            Objects.requireNonNull(variables, "variables cannot be null");
            return new MinConstraint(result, variables);
        }
    }

    public record OrBinaryConstraint(Variable result, Set_of<Constraint> constraints) implements Constraint {
        public static OrBinaryConstraint of(Variable result, Set_of<Constraint> constraints) {
            Objects.requireNonNull(result, "result cannot be null");
            Objects.requireNonNull(constraints, "constraints cannot be null");
            return new OrBinaryConstraint(result, constraints);
        }
    }

    public record AndBinaryConstraint(Variable result, Set_of<Constraint> constraints) implements Constraint {
        public static AndBinaryConstraint of(Variable result, Set_of<Constraint> constraints) {
            Objects.requireNonNull(result, "result cannot be null");
            Objects.requireNonNull(constraints, "constraints cannot be null");
            return new AndBinaryConstraint(result, constraints);
        }
    }

    public record AbsConstraint(Variable result, Set_of<Variable> variables) implements Constraint {
        public static AbsConstraint of(Variable result, Set_of<Variable> variables) {
            Objects.requireNonNull(result, "result cannot be null");
            Objects.requireNonNull(variables, "variables cannot be null");
            return new AbsConstraint(result, variables);
        }
    }

    public record PiecewiseLinearConstraint(Variable result, Variable source, List<Pair> points)
            implements Constraint {
        public static PiecewiseLinearConstraint of(Variable result, Variable source, List<Pair> points) {
            Objects.requireNonNull(result, "result cannot be null");
            Objects.requireNonNull(source, "source cannot be null");
            Objects.requireNonNull(points, "points cannot be null");
            return new PiecewiseLinearConstraint(result, source, points);
        }
    }

    public enum RelOperator {
        LT,
        LE,
        GT,
        GE,
        EQ
    }

    public record Pair(int x, int y) {
        public static Pair of(int x, int y) {
            return new Pair(x, y);
        }
    }
}
