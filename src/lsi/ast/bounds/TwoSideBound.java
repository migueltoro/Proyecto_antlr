package lsi.ast.bounds;

import lsi.ast.expressions.Expression;
import lsi.ast.variables.Variable;

public record TwoSideBound(
        Expression lower,
        Variable variable,
        Expression upper
) implements Bound {
}
