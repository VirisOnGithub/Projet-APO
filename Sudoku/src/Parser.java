import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Parser {
    public static ArrayList<Pair<Integer, Integer>> parse3x3() {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> cells = new ArrayList<>();
        
        try {
            File myObj = new File("../src/grid.csv");
            Scanner myScan = new Scanner(myObj);
            while (myScan.hasNextLine()) {
                String data = myScan.nextLine();
                grid.add(new ArrayList<>(new ArrayList<>(Arrays.asList(data.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList()))));
            }
            myScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        if(grid.size() != 9){
            System.err.println("Illegal Height");
        }

        for (int i = 0; i < 9; i++) {
            if(grid.get(i).size() != 9){
                System.err.println("Illegal width for line" + (i+1));
            }
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                if(grid.get(i).get(j) < 0 || grid.get(i).get(j) > 9){
                    System.err.println("Illegal value at (" + (i+1) + "," + (j+1) + ")");
                }
                cells.add(new Pair<>(grid.get(i).get(j), i/3*3+j/3));
            }
        }

        // for(Pair<ArrayList<Integer>, Integer> cell : cells){
        //     System.out.println(cell.first + " " + cell.second);
        // }

        return cells;
    }
}
