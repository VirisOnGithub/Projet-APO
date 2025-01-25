public class Main {
    public static void main(String[] args) {
        boolean hard = false;
        int taille = 5;
        Sudoku s = Parser.parseNXN("src/" + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        System.out.println(s.basicToString());
        System.out.println("Solving...\n\n\n");
        long time = System.currentTimeMillis();
        s.solveUsingBacktracking();
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println(s);
    }
}
