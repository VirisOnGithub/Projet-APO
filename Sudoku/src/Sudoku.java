import java.util.ArrayList;
import java.util.HashMap;

public class Sudoku {
    private ArrayList<Pair<Integer, Integer>> sudoku;
    private final Pair<Integer, Integer> dimensions;
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

    public boolean solveUsingRules() {
        boolean isSolved = false;
        boolean progressMade = true;
        while (!isSolved && progressMade) {
            isSolved = true;
            progressMade = false;
            for (int i = 0; i < dimensions.first; i++) {
                for (int j = 0; j < dimensions.second; j++) {
                    int index = i * dimensions.second + j;
                    if (sudoku.get(index).first == 0) {
                        isSolved = false;
                        ArrayList<Integer> possibleValues = new ArrayList<>();
                        for (int k = 1; k <= 9; k++) {
                            possibleValues.add(k);
                        }
                        for (int k = 0; k < dimensions.first; k++) {
                            int index1 = k * dimensions.second + j;
                            possibleValues.remove((Integer) sudoku.get(index1).first);
                        }
                        for (int k = 0; k < dimensions.second; k++) {
                            int index1 = i * dimensions.second + k;
                            possibleValues.remove((Integer) sudoku.get(index1).first);
                        }
                        int boxRow = i / 3;
                        int boxCol = j / 3;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                int index1 = (boxRow * 3 + k) * dimensions.second + (boxCol * 3 + l);
                                possibleValues.remove((Integer) sudoku.get(index1).first);
                            }
                        }
                        if (possibleValues.size() == 1) {
                            sudoku.set(index, new Pair<>(possibleValues.get(0), 9));
                            progressMade = true;
                        } else if (possibleValues.size() == 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return isSolved;
    }
}