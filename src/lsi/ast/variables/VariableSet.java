package lsi.ast.variables;

import java.util.List;

import lsi.ast.declarations.IndexDeclaration;
import lsi.ast.expressions.Expression;

public record VariableSet(
        Variable variable,
        List<IndexDeclaration> indexes,
        Expression filter) {
}
