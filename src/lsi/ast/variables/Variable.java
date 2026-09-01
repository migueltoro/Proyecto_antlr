package lsi.ast.variables;

import java.util.List;

import lsi.ast.expressions.Expression;

public record Variable(
        String name,
        List<Expression> indexes) {
}