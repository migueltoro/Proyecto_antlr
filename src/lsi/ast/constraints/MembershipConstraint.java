package lsi.ast.constraints;

import lsi.ast.common.Set_of;
import lsi.ast.expressions.Expression;
import lsi.ast.variables.Variable;

public record MembershipConstraint(
                Variable variable,
                Set_of<Expression> set) implements Constraint {
}
