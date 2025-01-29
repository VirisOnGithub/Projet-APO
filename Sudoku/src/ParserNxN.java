import model.Block;
import model.Multidoku;
import model.Pair;
import model.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class ParserNxN implements Parser {
    public Sudoku parse(String path) {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myScan = new Scanner(myObj);
            while (myScan.hasNextLine()) {
                String data = myScan.nextLine();
                grid.add(
                        new ArrayList<>(
                                new ArrayList<>(
                                        Arrays.stream(data.split(","))
                                                .parallel()
                                                .map(Integer::parseInt)
                                                .collect(Collectors.toList())
                                )
                        )
                );
            }
            myScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.err.println("File not found: " + e.getMessage());
        }

        ArrayList<Pair<Integer, Integer>> cells = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();

        int size = grid.size();

        for (int i = 0; i < size; i++) {
            blocks.add(new Block());
        }

        // Si la size n'est pas un carré parfait
        if (Math.sqrt(size) % 1 != 0) {
            System.err.println("Illegal Height - Not a perfect square " + size);
            exit(1);
        }

        for (int i = 0; i < size; i++) {
            if (grid.get(i).size() != size) {
                System.err.println("Illegal width for line " + (i + 1));
                exit(1);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid.get(i).get(j) < 0 || grid.get(i).get(j) > size) {
                    System.err.println("Illegal value at (" + (i + 1) + "," + (j + 1) + ")");
                    exit(1);
                }
                int blockIndex = i + j / (int) Math.sqrt(size);
                cells.add(new Pair<>(grid.get(i).get(j), blockIndex));
                blocks.get(blockIndex).addCase(blockIndex);
            }
        }

        return new Sudoku(cells, new Pair<>(size, size), blocks);
    }
}