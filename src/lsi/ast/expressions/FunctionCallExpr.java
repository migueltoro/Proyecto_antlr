package lsi.ast.expressions;

import java.util.List;

public record FunctionCallExpr(
        String name,
        List<Expression> arguments
) implements Expression {
}
