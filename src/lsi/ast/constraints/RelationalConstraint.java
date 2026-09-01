package lsi.ast.constraints;

import lsi.ast.linear.LinearExpr;
import lsi.ast.expressions.Expression;

public record RelationalConstraint(
        LinearExpr left,
        RelOperator op,
        Expression right) implements Constraint {
}
