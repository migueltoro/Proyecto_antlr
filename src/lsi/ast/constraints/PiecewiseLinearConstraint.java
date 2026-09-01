package lsi.ast.constraints;

import lsi.ast.variables.Variable;
import java.util.List;

public record PiecewiseLinearConstraint(
        Variable result,
        Variable source,
        List<Pair> points) implements Constraint {
}
