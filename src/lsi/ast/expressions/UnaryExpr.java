package lsi.ast.expressions;

public record UnaryExpr(
        UnaryOperator operator,
        Expression expression
) implements Expression {
} 


