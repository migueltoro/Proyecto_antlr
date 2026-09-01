package lsi.ast.common;

import lsi.ast.expressions.Expression;

public record Index(
        String variable,
        Expression lowerBound,
        Expression upperBound) {
}
