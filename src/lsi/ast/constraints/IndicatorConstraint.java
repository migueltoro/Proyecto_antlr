package lsi.ast.constraints;

import lsi.ast.variables.Variable;

public record IndicatorConstraint(
        Variable indicator,
        int value,
        RelationalConstraint consequence) implements Constraint {
}
