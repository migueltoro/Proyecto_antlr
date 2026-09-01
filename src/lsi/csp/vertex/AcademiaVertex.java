package lsi.csp.vertex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import lsi.csp.alg.Vertex;
import lsi.csp.alg.Busqueda;



/**
 * Implementacion concreta del problema de asignacion de alumnos a grupos
 * descrito en ejemplos/academia.mod y ejemplos/academia.dat.
 *
 * <p>Los alumnos se recorren en orden 1..N. En cada vertice, la accion posible
 * es elegir el grupo para el siguiente alumno no asignado.
 */
public class AcademiaVertex implements Vertex<Integer> {

    private final int numAlumnos;
    private final int numGrupos;
    private final int[][] afinidad;
    private final int currentAlumno;
    private final int maxTamGrupo;
    private final List<Integer> gruposAsignados;

    public AcademiaVertex(int[][] afinidad) {
        this(afinidad, 0, List.of());
    }

    private AcademiaVertex(int[][] afinidad, int currentAlumno, List<Integer> gruposAsignados) {
        this.numAlumnos = afinidad.length;
        this.numGrupos = afinidad[0].length;
        this.afinidad = afinidad;
        this.currentAlumno = currentAlumno;
        this.maxTamGrupo = numAlumnos / numGrupos;
        this.gruposAsignados = List.copyOf(gruposAsignados);
    }

    /**
     * Crea el problema desde el fichero de datos de ejemplo.
     */
    public static AcademiaVertex fromDatFile(String path) throws IOException {
        int[][] matriz = parseAfinidad(Path.of(path));
        return new AcademiaVertex(matriz);
    }

    /**
     * Devuelve hasta N soluciones validas del problema de academia.
     */
    public static Set<Vertex<Integer>> buscarSolucionesDesdeDat(String path, int n) throws IOException {
        AcademiaVertex inicial = fromDatFile(path);
        return Busqueda.buscarN(inicial, n);
    }

    /**
     * Devuelve las soluciones junto con su afinidad total.
     */
    public static List<AcademiaSolution> buscarSolucionesConAfinidad(String path, int n) throws IOException {
        Set<Vertex<Integer>> vertices = buscarSolucionesDesdeDat(path, n);
        List<AcademiaSolution> soluciones = new ArrayList<>();
        for (Vertex<Integer> v : vertices) {
            if (v instanceof AcademiaVertex academia) {
                soluciones.add(new AcademiaSolution(academia, academia.totalAfinidad()));
            }
        }
        soluciones.sort(Comparator.comparingInt(AcademiaSolution::afinidadTotal).reversed());
        return soluciones;
    }

    @Override
    public boolean goal() {
        return currentAlumno == numAlumnos;
    }

    @Override
    public boolean goalHasSolution() {
        if (!goal()) {
            return false;
        }
        return isConsistent();
    }

    @Override
    public boolean isConsistent() {
        if (currentAlumno < 0 || currentAlumno > numAlumnos) {
            return false;
        }
        if (gruposAsignados.size() != currentAlumno) {
            return false;
        }
        for (Integer grupo : gruposAsignados) {
            if (grupo == null || grupo < 0 || grupo >= numGrupos) {
                return false;
            }
        }
        for (int grupo = 0; grupo < numGrupos; grupo++) {
            if (countAssignments(grupo) > maxTamGrupo) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Integer> actions() {
        if (goal()) {
            return Collections.emptyList();
        }

        int alumno = currentAlumno;
        List<Integer> acciones = new ArrayList<>();
        for (int grupo = 0; grupo < numGrupos; grupo++) {
            if (isActionValid(alumno, grupo)) {
                acciones.add(grupo);
            }
        }
        acciones.sort((g1, g2) -> Integer.compare(afinidad[alumno][g2], afinidad[alumno][g1]));
        return acciones;
    }

    @Override
    public Vertex<Integer> neighbor(Integer action) {
        int alumno = currentAlumno;
        if (!isActionValid(alumno, action)) {
            throw new IllegalArgumentException(
                    "La accion grupo=" + (action + 1) + " no es valida para el alumno " + (alumno + 1));
        }
        List<Integer> nuevaAsignacion = new ArrayList<>(gruposAsignados);
        nuevaAsignacion.add(action);
        return new AcademiaVertex(afinidad, currentAlumno + 1, nuevaAsignacion);
    }

    /**
     * Devuelve la afinidad total de la asignacion actual.
     * Si la asignacion es parcial, suma solo los alumnos ya fijados.
     */
    public int totalAfinidad() {
        int total = 0;
        for (int alumno = 0; alumno < gruposAsignados.size(); alumno++) {
            total += afinidad[alumno][gruposAsignados.get(alumno)];
        }
        return total;
    }

    @Override
    public String stateDescription() {
        StringBuilder sb = new StringBuilder();
        int alumnoMostrado = Math.min(currentAlumno + 1, numAlumnos);
        sb.append("Alumno actual = ").append(alumnoMostrado).append(" de ").append(numAlumnos)
                .append(System.lineSeparator());
        for (int alumno = 0; alumno < numAlumnos; alumno++) {
            sb.append("Alumno ").append(alumno + 1).append(" -> ");
            if (alumno < gruposAsignados.size()) {
                sb.append("Grupo ").append(gruposAsignados.get(alumno) + 1);
            } else {
                sb.append("?");
            }
            sb.append(";").append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public int getNumGrupos() {
        return numGrupos;
    }

    public int[][] getAfinidad() {
        return afinidad;
    }

    public List<Integer> getGruposAsignados() {
        return gruposAsignados;
    }

    public int getCurrentAlumno() {
        return currentAlumno;
    }

    private boolean isActionValid(int alumno, Integer grupo) {
        return alumno >= 0
                && alumno < numAlumnos
                && grupo != null
                && grupo >= 0
                && grupo < numGrupos
                && countAssignments(grupo) < maxTamGrupo;
    }

    private int countAssignments(int grupo) {
        int count = 0;
        for (Integer grupoAsignado : gruposAsignados) {
            if (grupoAsignado == grupo) {
                count++;
            }
        }
        return count;
    }

    private static int[][] parseAfinidad(Path path) throws IOException {
        List<String> lineas = Files.readAllLines(path);
        int n = -1;
        int m = -1;
        List<int[]> filas = new ArrayList<>();
        boolean leyendoAfinidad = false;

        for (String linea : lineas) {
            String lineaLimpia = linea.trim();
            if (lineaLimpia.isEmpty() || lineaLimpia.startsWith("#")) {
                continue;
            }

            if (lineaLimpia.startsWith("param N")) {
                n = Integer.parseInt(extractNumber(lineaLimpia));
                continue;
            }

            if (lineaLimpia.startsWith("param M")) {
                m = Integer.parseInt(extractNumber(lineaLimpia));
                continue;
            }

            if (lineaLimpia.startsWith("param afinidad")) {
                leyendoAfinidad = true;
                continue;
            }

            if (leyendoAfinidad) {
                if (lineaLimpia.equals(";")) {
                    break;
                }
                String[] tokens = lineaLimpia.split("\\s+");
                if (tokens.length != m + 1) {
                    throw new IllegalArgumentException("Formato de afinidad incorrecto en: " + linea);
                }
                int[] fila = new int[m];
                for (int j = 0; j < m; j++) {
                    fila[j] = Integer.parseInt(tokens[j + 1]);
                }
                filas.add(fila);
            }
        }

        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("No se han encontrado N o M validos en " + path);
        }
        if (filas.size() != n) {
            throw new IllegalArgumentException(
                    "Se esperaban " + n + " filas pero se leyeron " + filas.size() + " en " + path);
        }

        int[][] matriz = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(filas.get(i), 0, matriz[i], 0, m);
        }
        return matriz;
    }

    private static String extractNumber(String linea) {
        String[] tokens = linea.split(":");
        String derecha = tokens.length > 1 ? tokens[1].trim() : linea.trim();
        String valor = derecha.replace(";", "").trim();
        String[] partes = valor.split("\\s+");
        return partes[partes.length - 1];
    }

    /**
     * Contenedor de una solucion con su afinidad total asociada.
     */
    public record AcademiaSolution(AcademiaVertex vertex, int afinidadTotal) {
        @Override
        public String toString() {
            return "Afinidad total = " + afinidadTotal + System.lineSeparator() + vertex.stateDescription();
        }
    }

    public static void main(String[] args) throws Exception {
        String ruta = "ejemplos\\academia.dat";
        List<AcademiaSolution> soluciones = buscarSolucionesConAfinidad(ruta, 20);
        System.out.println("Soluciones encontradas: " + soluciones.size());
        int i = 1;
        for (AcademiaSolution solucion : soluciones) {
            System.out.println("Solucion " + i + ":");
            System.out.println("Afinidad total = " + solucion.afinidadTotal());
            System.out.println(solucion.vertex().stateDescription());
            i++;
        }
    }
}
