package lsi.ast.expressions;

import lsi.ast.types.Type;

public record CastExpr(
        Type targetType,
        Expression expression
) implements Expression {
}
