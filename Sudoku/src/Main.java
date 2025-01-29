import model.Sudoku;

public class Main {
    public static void main(String[] args) {
        boolean hard = false;
        int taille = 4;
        Sudoku s;
        s = (new ParserNxN()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        System.out.println(s.basicToString());
        System.out.println("Solving...\n\n\n");
        long time = System.currentTimeMillis();
        s.blocks.forEach(System.out::println);
        s.solveUsingRules();
        // s.solveUsingBacktracking();
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println(s);
        System.out.println(s.basicToString());
    }
}
