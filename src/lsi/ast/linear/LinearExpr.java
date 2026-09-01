package lsi.ast.linear;

import java.util.List;

public record LinearExpr(
        List<LinearTerm> terms) {
}
