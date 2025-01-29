import model.Multidoku;
import model.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ParserMultidoku implements Parser {
    @Override
    public Sudoku parse(String path) {
        // TODO: Multidoku parser
        return null;
    }
    //    public static Multidoku parseMultidoku(String path) {
//        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
//        ArrayList<Sudoku> sudokus = new ArrayList<>();
//        ArrayList<ArrayList<Integer>> bindBlocks = new ArrayList<>();
//        try {
//            File myObj = new File(path);
//            Scanner myScan = new Scanner(myObj);
//            while (myScan.hasNextLine()) {
//                String data = myScan.nextLine();
//                if(Objects.equals(data, "---")){
//                    break;
//                }
//                if(data.isEmpty()){
//                    sudokus.add(createNXN(grid));
//                    grid = new ArrayList<>();
//                } else {
//                    grid.add(
//                            new ArrayList<>(
//                                    new ArrayList<>(
//                                            Arrays.stream(data.split(","))
//                                                    .parallel()
//                                                    .map(Integer::parseInt)
//                                                    .collect(Collectors.toList())
//                                    )
//                            )
//                    );
//                }
//            }
//            while (myScan.hasNextLine()) {
//                String data = myScan.nextLine();
//                bindBlocks.add(
//                        new ArrayList<>(
//                                new ArrayList<>(
//                                        Arrays.stream(data.split(","))
//                                                .parallel()
//                                                .map(Integer::parseInt)
//                                                .collect(Collectors.toList())
//                                )
//                        )
//                );
//            }
//            myScan.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Error");
//            System.err.println("File not found: " + e.getMessage());
//        }
//        return new Multidoku(sudokus, bindBlocks);
//    }
}
