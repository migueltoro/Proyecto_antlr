package lsi.csp.vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lsi.csp.alg.Busqueda;
import lsi.csp.alg.Vertex;

/**
 * CSP Sudoku 9x9 usando el modelo de acciones/vertices de lsi.csp.alg.
 *
 * El estado recorre las celdas en orden secuencial mediante currentCell.
 * La accion en cada paso es el valor (1..9) asignado a la celda actual.
 */
public class Sudoku implements Vertex<Integer> {

    private static final int SIZE = 9;
    private static final int BOX = 3;
    private static final int LAST_CELL = SIZE * SIZE;

    private final int[][] fixed;
    private final int[][] board;
    private final int currentCell;

    public Sudoku(int[][] fixed) {
        this(fixed, new int[SIZE][SIZE], 0);
    }

    private Sudoku(int[][] fixed, int[][] board, int currentCell) {
        this.fixed = copyGrid(fixed);
        this.board = copyGrid(board);
        this.currentCell = currentCell;
    }

    public static Sudoku defaultProblem() {
        int[][] fixed = new int[SIZE][SIZE];
        addFixed(fixed, 5, 7, 3);
        addFixed(fixed, 7, 7, 8);
        addFixed(fixed, 8, 7, 5);
        addFixed(fixed, 2, 6, 1);
        addFixed(fixed, 4, 6, 2);
        addFixed(fixed, 3, 5, 5);
        addFixed(fixed, 5, 5, 7);
        addFixed(fixed, 2, 4, 4);
        addFixed(fixed, 6, 4, 1);
        addFixed(fixed, 1, 3, 9);
        addFixed(fixed, 0, 2, 5);
        addFixed(fixed, 7, 2, 7);
        addFixed(fixed, 8, 2, 3);
        addFixed(fixed, 4, 0, 4);
        addFixed(fixed, 8, 0, 9);
        return new Sudoku(fixed);
    }

    private static void addFixed(int[][] fixed, int row, int col, int value) {
        if (fixed[row][col] != 0 && fixed[row][col] != value) {
            throw new IllegalArgumentException("Dato fijo contradictorio en (" + row + "," + col + ")");
        }
        fixed[row][col] = value;
    }

    @Override
    public boolean goal() {
        return currentCell == LAST_CELL;
    }

    @Override
    public boolean goalHasSolution() {
        return goal() && isConsistent();
    }

    @Override
    public boolean isConsistent() {
        if (currentCell < 0 || currentCell > LAST_CELL) {
            return false;
        }
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = board[row][col];
                if (value < 0 || value > 9) {
                    return false;
                }
                int fixedValue = fixed[row][col];
                if (fixedValue != 0 && value != 0 && value != fixedValue) {
                    return false;
                }
            }
        }
        return rowsValid() && colsValid() && boxesValid();
    }

    @Override
    public List<Integer> actions() {
        if (goal()) {
            return Collections.emptyList();
        }
        int row = currentCell / SIZE;
        int col = currentCell % SIZE;
        int fixedValue = fixed[row][col];
        List<Integer> values = new ArrayList<>();
        if (fixedValue != 0) {
            if (canPlace(row, col, fixedValue)) {
                values.add(fixedValue);
            }
            return values;
        }
        for (int value = 1; value <= 9; value++) {
            if (canPlace(row, col, value)) {
                values.add(value);
            }
        }
        return values;
    }

    @Override
    public Vertex<Integer> neighbor(Integer action) {
        if (action == null || action < 1 || action > 9) {
            throw new IllegalArgumentException("La accion debe ser un valor entre 1 y 9");
        }
        if (goal()) {
            throw new IllegalStateException("No hay vecino desde un estado goal");
        }
        int row = currentCell / SIZE;
        int col = currentCell % SIZE;
        int fixedValue = fixed[row][col];
        if (fixedValue != 0 && action.intValue() != fixedValue) {
            throw new IllegalArgumentException("Celda fija en (" + row + "," + col + ") requiere valor " + fixedValue);
        }
        if (!canPlace(row, col, action)) {
            throw new IllegalArgumentException("Accion invalida para celda (" + row + "," + col + "): " + action);
        }

        int[][] newBoard = copyGrid(board);
        newBoard[row][col] = action;
        return new Sudoku(fixed, newBoard, currentCell + 1);
    }

    @Override
    public String stateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("currentCell=").append(currentCell).append(System.lineSeparator());
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                sb.append(board[row][col] == 0 ? "." : Integer.toString(board[row][col]));
                if (col < SIZE - 1) {
                    sb.append(' ');
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private boolean canPlace(int row, int col, int value) {
        if (board[row][col] != 0) {
            return false;
        }
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == value) {
                return false;
            }
        }
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == value) {
                return false;
            }
        }
        int boxRow = (row / BOX) * BOX;
        int boxCol = (col / BOX) * BOX;
        for (int r = boxRow; r < boxRow + BOX; r++) {
            for (int c = boxCol; c < boxCol + BOX; c++) {
                if (board[r][c] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowsValid() {
        for (int row = 0; row < SIZE; row++) {
            boolean[] seen = new boolean[10];
            for (int col = 0; col < SIZE; col++) {
                int v = board[row][col];
                if (v == 0) {
                    continue;
                }
                if (seen[v]) {
                    return false;
                }
                seen[v] = true;
            }
        }
        return true;
    }

    private boolean colsValid() {
        for (int col = 0; col < SIZE; col++) {
            boolean[] seen = new boolean[10];
            for (int row = 0; row < SIZE; row++) {
                int v = board[row][col];
                if (v == 0) {
                    continue;
                }
                if (seen[v]) {
                    return false;
                }
                seen[v] = true;
            }
        }
        return true;
    }

    private boolean boxesValid() {
        for (int startRow = 0; startRow < SIZE; startRow += BOX) {
            for (int startCol = 0; startCol < SIZE; startCol += BOX) {
                boolean[] seen = new boolean[10];
                for (int row = startRow; row < startRow + BOX; row++) {
                    for (int col = startCol; col < startCol + BOX; col++) {
                        int v = board[row][col];
                        if (v == 0) {
                            continue;
                        }
                        if (seen[v]) {
                            return false;
                        }
                        seen[v] = true;
                    }
                }
            }
        }
        return true;
    }

    private static int[][] copyGrid(int[][] grid) {
        int[][] copy = new int[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            System.arraycopy(grid[r], 0, copy[r], 0, SIZE);
        }
        return copy;
    }

    public static void main(String[] args) {
        Sudoku initial = Sudoku.defaultProblem();
        Set<Vertex<Integer>> solutions = Busqueda.buscarN(initial, 1);
        if (solutions.isEmpty()) {
            System.out.println("No se encontro ninguna solucion.");
            return;
        }
        Vertex<Integer> solution = solutions.iterator().next();
        System.out.println("Se encontro al menos una solucion:");
        System.out.println(solution.stateDescription());
    }
}
