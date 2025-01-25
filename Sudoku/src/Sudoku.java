import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Sudoku {
    ArrayList<Pair<Integer, Integer>> sudoku;
    final Pair<Integer, Integer> dimensions;
    public boolean solved = false;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final HashMap<Integer, String> ANSI_HASH_MAP = new HashMap<>();

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
    }

    public Sudoku(ArrayList<Pair<Integer, Integer>> sudoku, Pair<Integer, Integer> dimensions) {
        this.sudoku = sudoku;
        this.dimensions = dimensions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimensions.first; i++) {
            for (int j = 0; j < dimensions.second; j++) {
                int index = i * dimensions.second + j;
                sb.append(ANSI_HASH_MAP.get(sudoku.get(index).second));
                if (sudoku.get(index).first == 0) {
                    sb.append(" ");
                } else {
                    sb.append(sudoku.get(index).first);
                }
                sb.append(ANSI_RESET);
                if (j < 8) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

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

    public void solveUsingRules() {
        boolean progressMade = true;
        int blockSize = (int) Math.sqrt(dimensions.first);
        while (!solved && progressMade) {
            solved = true;
            progressMade = false;
            for (int i = 0; i < dimensions.first; i++) {
                for (int j = 0; j < dimensions.second; j++) {
                    int index = i * dimensions.second + j;
                    if (sudoku.get(index).first == 0) {
                        solved = false;
                        ArrayList<Integer> possibleValues = new ArrayList<>();
                        for (int k = 1; k <= dimensions.first; k++) {
                            possibleValues.add(k);
                        }
                        for (int k = 0; k < dimensions.first; k++) {
                            int index1 = k * dimensions.second + j;
                            possibleValues.remove(sudoku.get(index1).first);
                        }
                        for (int k = 0; k < dimensions.second; k++) {
                            int index1 = i * dimensions.second + k;
                            possibleValues.remove(sudoku.get(index1).first);
                        }
                        int boxRow = i / blockSize;
                        int boxCol = j / blockSize;
                        for (int k = 0; k < blockSize; k++) {
                            for (int l = 0; l < blockSize; l++) {
                                int index1 = (boxRow * blockSize + k) * dimensions.second + (boxCol * blockSize + l);
                                possibleValues.remove(sudoku.get(index1).first);
                            }
                        }
                        if (possibleValues.size() == 1) {
                            sudoku.set(index, new Pair<>(possibleValues.getFirst(), sudoku.get(index).second));
                            progressMade = true;
                        } else if (possibleValues.isEmpty()) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void solveUsingBacktracking() {
        solveUsingBacktracking(0);
    }

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
            sudoku.set(index, new Pair<>(0, 9));
        } else {
            solveUsingBacktracking(index + 1);
        }
    }

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

    public List<Integer> getBlock(int index) {
        return sudoku.stream().filter(pair -> pair.second == index).map(pair -> pair.first).toList();
    }
}