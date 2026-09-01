package lsi.ast.linear;

import lsi.ast.common.Set_of;

public record Sum(Set_of<LinearFactor> terms) implements LinearTerm {
    
}
