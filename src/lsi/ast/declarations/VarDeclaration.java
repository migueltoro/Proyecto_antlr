package lsi.ast.declarations;

import lsi.ast.expressions.Expression;
import lsi.ast.types.Type;

public record VarDeclaration(
        Type type,
        String name,
        Expression initializer) implements Declaration {
}
