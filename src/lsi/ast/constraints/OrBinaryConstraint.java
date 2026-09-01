package lsi.ast.constraints;

import lsi.ast.common.Set_of;
import lsi.ast.variables.Variable;

public record OrBinaryConstraint(
        Variable result,
        Set_of<Constraint> constraints) implements Constraint {
}
