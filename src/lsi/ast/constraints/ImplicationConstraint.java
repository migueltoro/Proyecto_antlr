package lsi.ast.constraints;

public record ImplicationConstraint(
        RelationalConstraint antecedent,
        RelationalConstraint consequent) implements Constraint {
}
