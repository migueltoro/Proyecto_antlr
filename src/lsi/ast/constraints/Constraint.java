package lsi.ast.constraints;

public sealed interface Constraint
        permits RelationalConstraint,
                OrConstraint,
                ImplicationConstraint,
                DifferentValueConstraint,
                IndicatorConstraint,
                EqualsConstraint,
                AllDifferentConstraint,
                PermutationConstraint,
                MembershipConstraint,
                MaxConstraint,
                MinConstraint,
                OrBinaryConstraint,
                AndBinaryConstraint,
                AbsConstraint,
                PiecewiseLinearConstraint {
}
