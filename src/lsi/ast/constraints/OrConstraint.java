package lsi.ast.constraints;

import java.util.List;

public record OrConstraint(
        RelOperator operator,
        int threshold,
        List<RelationalConstraint> constraints) implements Constraint {
}
