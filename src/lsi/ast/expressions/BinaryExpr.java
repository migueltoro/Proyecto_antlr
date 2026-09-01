package lsi.ast.expressions;

public record BinaryExpr(
        Expression left,
        BinaryOperator operator,
        Expression right
) implements Expression {
}
