package Sudoku.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> grid = new ArrayList<>();
        
        try {
            File myObj = new File("grid.csv");
            Scanner myScan = new Scanner(myObj);
            while (myScan.hasNextLine()) {
                String data = myScan.nextLine();
                grid.add(new ArrayList<String>(Arrays.asList(data.split(","))));
            }
            myScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }


    }
}
