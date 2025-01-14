import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class Parser {
    public static ArrayList<ArrayList<Integer>> parseGrid(String path){
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
        return grid;
    }

    public static Sudoku parseNXN(String path) {
        ArrayList<ArrayList<Integer>> grid = parseGrid(path);
        ArrayList<Pair<Integer, Integer>> cells = new ArrayList<>();

        int size = grid.size();

        // Si la size n'est pas un carré parfait
        if(Math.sqrt(size) % 1 != 0){
            System.err.println("Illegal Height - Not a perfect square " + size);
            exit(1);
        }

        for (int i = 0; i < size; i++) {
            if(grid.get(i).size() != size){
                System.err.println("Illegal width for line " + (i+1));
                exit(1);
            }
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                if(grid.get(i).get(j) < 0 || grid.get(i).get(j) > size){
                    System.err.println("Illegal value at (" + (i+1) + "," + (j+1) + ")");
                    exit(1);
                }
                cells.add(new Pair<>(grid.get(i).get(j), i/(int)Math.sqrt(size)*(int)Math.sqrt(size)+j/(int)Math.sqrt(size)));
            }
        }

        return new Sudoku(cells, Pair.create(size, size));
    }
}
