package lsi.ast.constraints;

import lsi.ast.common.Set_of;
import lsi.ast.expressions.Expression;
import lsi.ast.variables.Variable;

public record PermutationConstraint(
        Set_of<Variable> variables,
        Set_of<Expression> values) implements Constraint {
}
