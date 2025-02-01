import model.Sudoku;
import parser.SplitBlocksParser;

public class Main {
    public static void main(String[] args) {
        boolean hard = false;
        int taille = 3;
        Sudoku s;
        s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        System.out.println(s.basicToString());
        System.out.println("Solving...\n\n\n");
        long time = System.currentTimeMillis();
//        s.blocks.forEach(System.out::println);
        s.solveUsingRules();
//        s.solveUsingBacktracking();
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println(s);
        System.out.println(s.basicToString());
    }
}
