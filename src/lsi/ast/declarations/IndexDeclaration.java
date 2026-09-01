package lsi.ast.declarations;

import lsi.ast.expressions.Expression;

public record IndexDeclaration(
        String variable,
        Expression lowerBound,
        Expression upperBound) {
}
