import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Parser {
    public static void parse() {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> blocks = new ArrayList<>();
        
        try {
            File myObj = new File("grid.csv");
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
            blocks.add(new ArrayList<>());
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                if(grid.get(i).get(j) < 0 || grid.get(i).get(j) > 9){
                    System.err.println("Illegal value at (" + (i+1) + "," + (j+1) + ")");
                }
                blocks.get(i/3*3+j/3).add(new ArrayList<>(Arrays.asList(grid.get(i).get(j), i, j)));
            }
        }

            for (ArrayList<ArrayList<Integer>> block : blocks) {
                for (ArrayList<Integer> cell : block) {
                    System.out.println(cell);
                }
                System.out.println("\n");
            }
    }
}
