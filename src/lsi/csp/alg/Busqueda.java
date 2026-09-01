package lsi.csp.alg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Algoritmo de busqueda de N soluciones para problemas CSP mediante
 * backtracking con exploracion aleatoria del orden de las acciones.
 */
public class Busqueda {

    /**
     * Busca hasta {@code n} soluciones partiendo del vertice inicial dado.
     *
     * @param initial vertice inicial del problema CSP
     * @param n       numero maximo de soluciones deseadas (n >= 1)
     * @return conjunto con hasta {@code n} vertices que representan soluciones validas
     */
    public static <A> Set<Vertex<A>> buscarN(Vertex<A> initial, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n debe ser >= 1");
        }
        Set<Vertex<A>> soluciones = new HashSet<>();
        backtrack(initial, n, soluciones);
        return soluciones;
    }

    private static <A> void backtrack(Vertex<A> v, int n, Set<Vertex<A>> soluciones) {
        if (soluciones.size() >= n) {
            return;
        }
        if (!v.isConsistent()) {
            return;
        }
        if (v.goal()) {
            if (v.goalHasSolution()) {
                soluciones.add(v);
            }
            return;
        }

        List<A> acciones = new ArrayList<>(v.actions());
        Collections.shuffle(acciones);

        for (A accion : acciones) {
            if (soluciones.size() >= n) {
                return;
            }
            Edge<A> arista = v.edge(accion);
            Vertex<A> sucesor = arista.target();
            if (!sucesor.isConsistent()) {
                continue;
            }
            backtrack(sucesor, n, soluciones);
        }
    }
}
