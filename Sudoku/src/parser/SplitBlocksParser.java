package parser;

import model.Block;
import model.Pair;
import model.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.exit;

/**
 * The SplitBlocksParser class provides a method for parsing a Sudoku puzzle with split blocks from a file (format: value:blockIndex).
 */
public class SplitBlocksParser implements Parser {
    /**
     * Parse a Sudoku puzzle with split blocks from a file
     * @param path The path to the file containing the Sudoku puzzle
     * @return The parsed Sudoku puzzle
     */
    @Override
    public Sudoku parse(String path) {
        ArrayList<Pair<Integer, Integer>> grid = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myScan = new Scanner(myObj);
            AtomicReference<Integer> count = new AtomicReference<>(0);
            while (myScan.hasNextLine()) {
                String data = myScan.nextLine();
                grid.addAll(Arrays.stream(data.split(","))
                        .map(expr -> {
                            String[] split = expr.split(":");
                            int value = Integer.parseInt(split[0]);
                            int blockIndex = Integer.parseInt(split[1]);
                            if (blocks.size() <= blockIndex) {
                                adjustSize(blocks, blockIndex);
                            }
                            if (value != 0) {
                                blocks.get(blockIndex).addCase(count.get(), blockIndex);
                            }
                            count.getAndSet(count.get() + 1);
                            return new Pair<>(value, blockIndex);
                        })
                        .toList());
            }
            myScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.err.println("File not found: " + e.getMessage());
        }

        int size = grid.size();

        double sqrt = Math.sqrt(size);

        // Si la size n'est pas un carré parfait
        if (sqrt % 1 != 0) {
            System.err.println("Illegal Height - Not a perfect square " + size);
            exit(1);
        }



        return new Sudoku(grid, new Pair<>((int) sqrt,(int) sqrt), blocks);
    }

    /**
     * Adjust the size of the blocks list
     * @param blocks The list of blocks
     * @param size The size to adjust to
     */
    private void adjustSize(ArrayList<Block> blocks, int size) {
        while (blocks.size() <= size) {
            blocks.add(new Block());
        }
    }
}
