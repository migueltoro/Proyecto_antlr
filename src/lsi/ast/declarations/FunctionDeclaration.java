package lsi.ast.declarations;

import java.util.List;

import lsi.ast.types.Type;

public record FunctionDeclaration(
        Type returnType,
        String name,
        List<Parameter> parameters
) implements Declaration {
}
