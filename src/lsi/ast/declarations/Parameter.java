package lsi.ast.declarations;

import lsi.ast.types.Type;

public record Parameter(
        Type type,
        String name
) {}
