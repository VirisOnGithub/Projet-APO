import model.Sudoku;

public class Main {
    public static void main(String[] args) {
        boolean hard = false;
        int taille = 3;
        Sudoku s;
        if(System.getProperty("os.name").equals("Linux")) {
            s = Parser.parseNXN("src/resources" + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        } else {
            s = Parser.parseNXN("Sudoku\\src\\resources\\" + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        }
        System.out.println(s.basicToString());
        System.out.println("Solving...\n\n\n");
        long time = System.currentTimeMillis();
        s.solveUsingRules();
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println(s);
    }
}
