package lsi.csp.alg;

import java.util.Objects;

/**
 * Arista del espacio de estados de un CSP.
 *
 * @param <A> tipo de la accion que origina la transicion
 */
public record Edge<A>(Vertex<A> source, Vertex<A> target, A action) {

    public Edge {
        Objects.requireNonNull(source, "source no puede ser null");
        Objects.requireNonNull(target, "target no puede ser null");
        Objects.requireNonNull(action, "action no puede ser null");
    }
}
