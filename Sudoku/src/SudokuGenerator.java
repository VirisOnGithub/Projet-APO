import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.Sudoku;
import model.Pair;
import model.Block;

public class SudokuGenerator {
    private static final Random RANDOM = new Random();

    public static String[][] generateSudoku(int blockSize, String difficulty) {
        int size = blockSize * blockSize;
        int[][] grid = new int[size][size];
        fillGrid(grid, size);
        String[][] formattedGrid = formatGridWithBlocks(grid, size);
        removeNumbers(formattedGrid, size, difficulty);
        if (!isSolvable(formattedGrid, size)) {
            return generateSudoku(blockSize, difficulty);
        }
        return formattedGrid;
    }

    private static boolean fillGrid(int[][] grid, int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == 0) {
                    List<Integer> numbers = getShuffledNumbers(size);
                    for (int number : numbers) {
                        if (isValid(grid, row, col, number, size)) {
                            grid[row][col] = number;
                            if (fillGrid(grid, size)) {
                                return true;
                            }
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Integer> getShuffledNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private static boolean isValid(int[][] grid, int row, int col, int number, int size) {
        for (int i = 0; i < size; i++) {
            if (grid[row][i] == number || grid[i][col] == number) {
                return false;
            }
        }
        int subgridSize = (int) Math.sqrt(size);
        int startRow = row - row % subgridSize;
        int startCol = col - col % subgridSize;
        for (int i = 0; i < subgridSize; i++) {
            for (int j = 0; j < subgridSize; j++) {
                if (grid[startRow + i][startCol + j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String[][] formatGridWithBlocks(int[][] grid, int size) {
        String[][] formattedGrid = new String[size][size];
        int subgridSize = (int) Math.sqrt(size);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int blockIndex = (row / subgridSize) * subgridSize + (col / subgridSize);
                formattedGrid[row][col] = grid[row][col] + ":" + blockIndex;
            }
        }
        return formattedGrid;
    }

    private static void removeNumbers(String[][] grid, int size, String difficulty) {
        int cellsToRemove = switch (difficulty.toLowerCase()) {
            case "facile" -> size * size / 4;
            case "moyen" -> size * size / 2;
            case "difficile" -> size * size * 3 / 4;
            default -> throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        };
        while (cellsToRemove > 0) {
            int row = RANDOM.nextInt(size);
            int col = RANDOM.nextInt(size);
            if (!grid[row][col].startsWith("0:")) {
                String[] parts = grid[row][col].split(":");
                grid[row][col] = "0:" + parts[1];
                cellsToRemove--;
            }
        }
    }

    private static boolean isSolvable(String[][] grid, int size) {
        ArrayList<Pair<Integer, Integer>> cells = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            blocks.add(new Block());
        }
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String[] parts = grid[row][col].split(":");
                int value = Integer.parseInt(parts[0]);
                int blockIndex = Integer.parseInt(parts[1]);
                cells.add(new Pair<>(value, blockIndex));
                if (value != 0) {
                    blocks.get(blockIndex).addCase(row * size + col);
                }
            }
        }
        Sudoku sudoku = new Sudoku(cells, new Pair<>(size, size), blocks);
        sudoku.solveUsingBacktracking();
        return sudoku.solved;
    }

    public static void writeSudokuToFile(String[][] sudoku, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : sudoku) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}