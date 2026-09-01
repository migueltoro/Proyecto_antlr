package lsi.ast.constraints;

import lsi.ast.variables.Variable;

public record EqualsConstraint(
        Variable left,
        Variable right) implements Constraint {
}
