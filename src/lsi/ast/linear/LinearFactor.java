package lsi.ast.linear;

import lsi.ast.expressions.Expression;
import lsi.ast.variables.Variable;

public final class LinearFactor implements LinearTerm {
    private final Expression coefficient;
    private final Variable variable;

    public LinearFactor(Expression coefficient, Variable variable) {
        this.coefficient = coefficient;
        this.variable = variable;
    }

    public Expression coefficient() {
        return coefficient;
    }

    public Variable variable() {
        return variable;
    }
}

