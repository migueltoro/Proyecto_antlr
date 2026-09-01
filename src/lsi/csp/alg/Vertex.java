package lsi.csp.alg;

import java.util.List;

/**
 * Representa un estado en el espacio de busqueda de un problema CSP.
 *
 * <p>Un vertice expone las acciones validas que pueden aplicarse sobre el
 * estado actual. Cada accion determina de forma univoca una arista y un
 * vertice sucesor.
 *
 * @param <A> tipo de las acciones del problema
 */
public interface Vertex<A> {

    /**
     * Devuelve true cuando todas las variables han recibido un valor.
     * No implica que la asignacion sea valida (ver goalHasSolution()).
     */
    boolean goal();

    /**
     * Devuelve true cuando el estado es goal y la asignacion satisface
     * todas las restricciones del problema CSP.
     */
    boolean goalHasSolution();

    /**
     * Devuelve las acciones posibles desde este vertice. Solo deben incluirse
     * aquellas acciones que conducen a vertices validos.
     */
    List<A> actions();

    /**
     * Devuelve el vertice sucesor resultante de aplicar la accion indicada.
     */
    Vertex<A> neighbor(A action);

    /**
     * Construye la arista determinada por la accion dada.
     */
    default Edge<A> edge(A action) {
        return new Edge<>(this, neighbor(action), action);
    }

    /**
     * Devuelve una representacion legible de la asignacion actual,
     * util para mostrar soluciones.
     */
    String stateDescription();

    /**
     * Indica si este estado parcial es consistente con las restricciones del CSP.
     * Permite podar ramas invalidas antes de expandirlas.
     */
    default boolean isConsistent() {
        return true;
    }
}
