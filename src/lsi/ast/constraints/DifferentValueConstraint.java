package lsi.ast.constraints;

import lsi.ast.variables.Variable;

public record DifferentValueConstraint(
        Variable left,
        Variable right) implements Constraint {
}
