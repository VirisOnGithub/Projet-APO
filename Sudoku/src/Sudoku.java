import java.util.ArrayList;
import java.util.HashMap;

public class Sudoku {
    private ArrayList<Pair<Integer, Integer>> sudoku;
    private Pair<Integer, Integer> dimensions;
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
                sb.append(sudoku.get(index).first);
                sb.append(ANSI_RESET);
                if (j < 8) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
