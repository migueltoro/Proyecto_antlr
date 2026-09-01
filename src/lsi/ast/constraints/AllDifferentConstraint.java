package lsi.ast.constraints;

import lsi.ast.common.Set_of;
import lsi.ast.variables.Variable;

public record AllDifferentConstraint(
        Set_of<Variable> variables) implements Constraint {
}
