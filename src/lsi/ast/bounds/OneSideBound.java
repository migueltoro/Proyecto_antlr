package lsi.ast.bounds;

import lsi.ast.expressions.Expression;
import lsi.ast.variables.Variable;
import lsi.ast.constraints.RelOperator;

public record OneSideBound(
        Variable variable,
        RelOperator operator,
        Expression expression) implements Bound {
}
