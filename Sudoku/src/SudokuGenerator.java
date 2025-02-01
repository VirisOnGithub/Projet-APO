import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.Pair;
import model.Block;

public class SudokuGenerator {
    private static final Random RANDOM = new Random();

    public static ArrayList<Pair<Integer, Integer>> generateSudoku(int blockSize, String difficulty, boolean irregularBlocks) {
        int size = blockSize * blockSize;
        int[][] grid = new int[size][size];
        ArrayList<Block> blocks = new ArrayList<>();
        fillGrid(grid, size);
        ArrayList<Pair<Integer, Integer>> formattedGrid = irregularBlocks ? formatGridWithRandomBlocks(grid, size) : formatGridWithBlocks(grid, size, blocks);
        removeNumbers(formattedGrid, size, difficulty);
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
                    return false; // Return false if no number is valid
                }
            }
        }
        return true; // Return true if the grid is completely filled
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

    private static ArrayList<Pair<Integer, Integer>> formatGridWithBlocks(int[][] grid, int size, ArrayList<Block> blocks) {
        ArrayList<Pair<Integer, Integer>> formattedGrid = new ArrayList<>();
        int subgridSize = (int) Math.sqrt(size);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int blockIndex = (row / subgridSize) * subgridSize + (col / subgridSize);
                formattedGrid.add(new Pair<>(grid[row][col], blockIndex));
                if (blocks.size() <= blockIndex) {
                    adjustSize(blocks, blockIndex);
                }
                blocks.get(blockIndex).addCase(grid[row][col], blockIndex);
            }
        }
        return formattedGrid;
    }

    private static void adjustSize(ArrayList<Block> blocks, int size) {
        while (blocks.size() <= size) {
            blocks.add(new Block());
        }
    }

    private static ArrayList<Pair<Integer, Integer>> formatGridWithRandomBlocks(int[][] grid, int size) {
        ArrayList<Pair<Integer, Integer>> formattedGrid = new ArrayList<>();
        List<Integer> blockIndices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                blockIndices.add(i);
            }
        }
        Collections.shuffle(blockIndices);

        for (int i = 0; i < size * size; i++) {
            int row = i / size;
            int col = i % size;
            formattedGrid.add(new Pair<>(grid[row][col], blockIndices.get(i)));
        }
        return formattedGrid;
    }

    private static void removeNumbers(ArrayList<Pair<Integer, Integer>> grid, int size, String difficulty) {
        int cellsToRemove = switch (difficulty.toLowerCase()) {
            case "facile" -> size * size / 4;
            case "moyen" -> size * size / 2;
            case "difficile" -> size * size * 3 / 4;
            default -> throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        };

        while (cellsToRemove > 0) {
            int index = RANDOM.nextInt(size * size);
            Pair<Integer, Integer> originalPair = grid.get(index);
            if (originalPair.first != 0) {
                grid.set(index, new Pair<>(0, originalPair.second));
                cellsToRemove--;
            }
        }
    }

    public static void writeSudokuToFile(ArrayList<Pair<Integer, Integer>> sudoku, String filePath, int blockSize) {
        int size = blockSize * blockSize;
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int index = i * size + j;
                    writer.write(sudoku.get(index).first + ":" + sudoku.get(index).second);
                    if (j < size - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}