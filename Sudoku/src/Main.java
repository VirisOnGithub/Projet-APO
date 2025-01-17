public class Main {
    public static void main(String[] args) {
        Sudoku s = Parser.parseNXN("src/4x4.csv");
        System.out.println(s.basicToString());
        System.out.println("Solving...\n\n\n");
        long time = System.currentTimeMillis();
        s.solveUsingBacktracking();
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println(s.basicToString());
    }
}
