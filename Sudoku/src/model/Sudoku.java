package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * The Sudoku class represents a Sudoku puzzle.
 */
public class Sudoku implements Doku {
    public ArrayList<Pair<Integer, Integer>> sudoku;
    public final ArrayList<Block> blocks;
    final Pair<Integer, Integer> dimensions;
    public boolean solved = false;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final HashMap<Integer, String> ANSI_HASH_MAP = new HashMap<>();

    // Create a map of ANSI colors
    static {
        ANSI_HASH_MAP.put(0, "\u001B[30m");
        ANSI_HASH_MAP.put(1, "\u001B[31m");
        ANSI_HASH_MAP.put(2, "\u001B[32m");
        ANSI_HASH_MAP.put(3, "\u001B[33m");
        ANSI_HASH_MAP.put(4, "\u001B[34m");
        ANSI_HASH_MAP.put(5, "\u001B[35m");
        ANSI_HASH_MAP.put(6, "\u001B[36m");
        ANSI_HASH_MAP.put(7, "\u001B[37m");
        ANSI_HASH_MAP.put(8, "\u001B[38;5;208m");
        ANSI_HASH_MAP.put(9, "\u001B[38;5;196m");
        ANSI_HASH_MAP.put(10, "\u001B[38;5;202m");
        ANSI_HASH_MAP.put(11, "\u001B[38;5;214m");
        ANSI_HASH_MAP.put(12, "\u001B[38;5;220m");
        ANSI_HASH_MAP.put(13, "\u001B[38;5;226m");
        ANSI_HASH_MAP.put(14, "\u001B[38;5;118m");
        ANSI_HASH_MAP.put(15, "\u001B[38;5;46m");
    }

    /**
     * Create a Sudoku puzzle
     * @param sudoku The Sudoku puzzle
     * @param dimensions The dimensions of the Sudoku puzzle
     * @param blocks The blocks of the Sudoku puzzle
     */
    public Sudoku(ArrayList<Pair<Integer, Integer>> sudoku, Pair<Integer, Integer> dimensions, ArrayList<Block> blocks) {
        this.sudoku = sudoku;
        this.dimensions = dimensions;
        this.blocks = blocks;
    }

    /**
     * Prints the colored Sudoku puzzle with ANSI escape codes
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int maxSizeNumber = 0;
        for (int i = 0; i < dimensions.first; i++) {
            for (int j = 0; j < dimensions.second; j++) {
                int index = i * dimensions.second + j;
                int sizeNumber = String.valueOf(sudoku.get(index).first).length();
                if (sizeNumber > maxSizeNumber) {
                    maxSizeNumber = sizeNumber;
                }
            }
        }
        for (int i = 0; i < dimensions.first; i++) {
            for (int j = 0; j < dimensions.second; j++) {
                int index = i * dimensions.second + j;
                sb.append(ANSI_HASH_MAP.get(sudoku.get(index).second));
                if (sudoku.get(index).first == 0) {
                    sb.append(" ".repeat(maxSizeNumber));
                } else {
                    int sizeNumber = String.valueOf(sudoku.get(index).first).length();
                    sb.append(" ".repeat(Math.max(0, maxSizeNumber - sizeNumber)));
                    sb.append(sudoku.get(index).first);
                }
                sb.append(ANSI_RESET);
                if (j < dimensions.second - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the Sudoku puzzle without colors
     */
    public String basicToString() {
        // Find the maximum number of digits in the sudoku
        int maxSizeNumber = 0;
        for (int i = 0; i < dimensions.first; i++) {
            for (int j = 0; j < dimensions.second; j++) {
                int index = i * dimensions.second + j;
                int sizeNumber = String.valueOf(sudoku.get(index).first).length();
                if (sizeNumber > maxSizeNumber) {
                    maxSizeNumber = sizeNumber;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimensions.first; i++) {
            for (int j = 0; j < dimensions.second; j++) {
                int index = i * dimensions.second + j;
                if (sudoku.get(index).first == 0) {
                    sb.append(" ".repeat(maxSizeNumber));
                } else {
                    int sizeNumber = String.valueOf(sudoku.get(index).first).length();
                    sb.append(" ".repeat(Math.max(0, maxSizeNumber - sizeNumber)));
                    sb.append(sudoku.get(index).first);
                }
                if (j < dimensions.second - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Solve the Sudoku puzzle using the rules (line, column, block)
     */
    public void solveUsingRules() {
        boolean progressMade = true;
        while (!solved && progressMade) {
            solved = true;
            progressMade = false;
            for (int i = 0; i < dimensions.first; i++) {
                for (int j = 0; j < dimensions.second; j++) {
                    int index = i * dimensions.second + j;
                    if (sudoku.get(index).first == 0) {
                        solved = false;
                        ArrayList<Integer> possibleValues = new ArrayList<>();

                        // Add all possibilities to the case
                        for (int k = 1; k <= dimensions.first; k++) {
                            possibleValues.add(k);
                        }

                        // Check if value in column is already taken
                        for (int k = 0; k < dimensions.first; k++) {
                            int index1 = k * dimensions.second + j;
                            possibleValues.remove((Integer) sudoku.get(index1).first);
                        }

                        // Check if value in row is already taken
                        for (int k = 0; k < dimensions.second; k++) {
                            int index1 = i * dimensions.second + k;
                            possibleValues.remove((Integer) sudoku.get(index1).first);
                        }
                        // Check if value in block is already taken
                        for (int indexSudoku : blocks.get(sudoku.get(index).second).getCases()) {
                            if (indexSudoku != index) {
                                possibleValues.remove((Integer) sudoku.get(indexSudoku).first);
                            }
                        }

                        if (possibleValues.size() == 1) {
                            sudoku.set(index, new Pair<>(possibleValues.get(0), sudoku.get(index).second));
                            blocks.get(sudoku.get(index).second).getCases().add(index);
                            progressMade = true;
                        } else if (possibleValues.isEmpty()) {
                            return;
                        }
                    }
                }
            }
            if (!progressMade) {
                return;
            }
        }
    }

    /**
     * Solve the Sudoku puzzle using backtracking
     */
    public void solveUsingBacktracking() {
        solveUsingBacktracking(0);
    }

    /**
     * Solve the Sudoku puzzle using backtracking
     * @param index The index of the case to solve
     */
    private void solveUsingBacktracking(int index) {
        if (index == dimensions.first * dimensions.second) {
            solved = true;
            return;
        }
        if (sudoku.get(index).first == 0) {
            for (int i = 1; i <= dimensions.first; i++) {
                sudoku.set(index, new Pair<>(i, sudoku.get(index).second));
                if (isValid(index)) {
                    solveUsingBacktracking(index + 1);
                    if (solved) {
                        return;
                    }
                }
            }
            sudoku.set(index, new Pair<>(0, sudoku.get(index).second));
        } else {
            solveUsingBacktracking(index + 1);
        }
    }

    /**
     * Check if the value at the index is valid
     * @param index The index of the case to check
     * @return True if the value is valid, false otherwise
     */
    private boolean isValid(int index) {
        int i = index / dimensions.second;
        int j = index % dimensions.second;
        int blockSize = (int) Math.sqrt(dimensions.first);
        for (int k = 0; k < dimensions.first; k++) {
            int index1 = k * dimensions.second + j;
            if (index1 != index && Objects.equals(sudoku.get(index1).first, sudoku.get(index).first)) {
                return false;
            }
        }
        for (int k = 0; k < dimensions.second; k++) {
            int index1 = i * dimensions.second + k;
            if (index1 != index && Objects.equals(sudoku.get(index1).first, sudoku.get(index).first)) {
                return false;
            }
        }
        int boxRow = i / blockSize;
        int boxCol = j / blockSize;
        for (int k = 0; k < blockSize; k++) {
            for (int l = 0; l < blockSize; l++) {
                int index1 = (boxRow * blockSize + k) * dimensions.second + (boxCol * blockSize + l);
                if (index1 != index && Objects.equals(sudoku.get(index1).first, sudoku.get(index).first)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the block of the Sudoku puzzle
     * @param index The index of the block
     * @return The block
     */
    public List<Integer> getBlock(int index) {
        return sudoku.stream().filter(pair -> pair.second == index).map(pair -> pair.first).toList();
    }
}