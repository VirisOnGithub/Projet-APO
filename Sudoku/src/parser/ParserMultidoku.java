package parser;

import model.Block;
import model.Multidoku;
import model.Pair;
import model.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The ParserMultidoku class provides a method for parsing a Multidoku puzzle from a file.
 */
public class ParserMultidoku implements Parser {
    /**
     * Parse a Multidoku puzzle from a file
     * @param path The path to the file containing the Multidoku puzzle
     * @return The parsed Multidoku puzzle
     */
    @Override
    public Multidoku parse(String path) {
        ArrayList<Sudoku> sudokus = new ArrayList<>();
        ArrayList<ArrayList<Integer>> bindBlocks = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myScan = new Scanner(myObj);
            ArrayList<Pair<Integer, Integer>> grid = new ArrayList<>();
            AtomicReference<ArrayList<Block>> blocks = new AtomicReference<>(new ArrayList<>());
            while (myScan.hasNextLine()) {
                String data = myScan.nextLine();
                if(data.equals("---")){
                    break;
                }
                if(data.isEmpty()){
                    sudokus.add(new Sudoku(grid, new Pair<>((int) Math.sqrt(grid.size()), (int) Math.sqrt(grid.size())), blocks.get()));
                    grid = new ArrayList<>();
                    blocks.set(new ArrayList<>());
                    continue;
                }
                AtomicReference<Integer> count = new AtomicReference<>(0);
                grid.addAll(Arrays.stream(data.split(","))
                    .map(expr -> {
                        String[] split = expr.split(":");
                        int value = Integer.parseInt(split[0]);
                        int blockIndex = Integer.parseInt(split[1]);
                        if (blocks.get().size() <= blockIndex) {
                            adjustSize(blocks, blockIndex);
                        }
                        if (value != 0) {
                            blocks.get().get(blockIndex).addCase(count.get(), blockIndex);
                        }
                        count.getAndSet(count.get() + 1);
                        return new Pair<>(value, blockIndex);
                    })
                    .toList());
            }
            while(myScan.hasNextLine()){
                String data = myScan.nextLine();
                bindBlocks.add(new ArrayList<>(Arrays.stream(data.split(","))
                    .map(Integer::parseInt)
                    .toList()));
            }
            myScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.err.println("File not found: " + e.getMessage());
        }
        return new Multidoku(sudokus, bindBlocks);
    }

    /**
     * Adjust the size of the blocks list
     * @param blocks The list of blocks
     * @param size The size to adjust to
     */
    private void adjustSize(AtomicReference<ArrayList<Block>> blocks, int size) {
        while (blocks.get().size() <= size) {
            blocks.get().add(new Block());
        }
    }
}
