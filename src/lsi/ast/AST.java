package lsi.ast;

import java.util.List;

import lsi.ast.constraints.Constraint;
import lsi.ast.declarations.Declaration;
import lsi.ast.linear.LinearExpr;
import lsi.ast.variables.Variable;
import lsi.ast.common.Set_of;
import lsi.ast.bounds.Bound;

public record AST(
        List<Declaration> declarations,
        Objective objective,
        LinearExpr objectiveExpr,
        List<Set_of<Constraint>> constraints,
        List<Set_of<Bound>> bounds,
        List<Set_of<Variable>> binaryVars,
        List<Set_of<Variable>> integerVars,
        List<Set_of<Variable>> freeVars,
        List<Set_of<Variable>> semiContinuousVars
) {}