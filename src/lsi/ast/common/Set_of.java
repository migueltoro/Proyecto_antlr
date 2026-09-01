package lsi.ast.common;

import java.util.List;

import lsi.ast.expressions.Expression;

public record Set_of<T>(
        T element,
        List<Index> indexes,
        Expression filter) {
}
