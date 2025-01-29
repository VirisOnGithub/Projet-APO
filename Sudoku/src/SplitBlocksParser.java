import model.Block;
import model.Pair;
import model.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class SplitBlocksParser implements Parser {
    @Override
    public Sudoku parse(String path) {
        ArrayList<Pair<Integer, Integer>> grid = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myScan = new Scanner(myObj);
            while (myScan.hasNextLine()) {
                String data = myScan.nextLine();
                grid.addAll(Arrays.stream(data.split(","))
                        .parallel()
                        .map(expr -> {
                            if (expr.equals("0")) {
                                return new Pair<>(0, 0);
                            } else if (expr.contains(":")) {
                                String[] split = expr.split(":");
                                int blockIndex = Integer.parseInt(split[0]);
                                int value = Integer.parseInt(split[1]);
                                if (blocks.size() <= blockIndex) {
                                    adjustSize(blocks, blockIndex);
                                }
                                blocks.get(blockIndex).addCase(value);
                                return new Pair<>(value, blockIndex);
                            } else {
                                throw new Error("Invalid input");
                            }
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

    private void adjustSize(ArrayList<Block> blocks, int size) {
        while (blocks.size() < size) {
            blocks.add(new Block());
        }
    }
}
