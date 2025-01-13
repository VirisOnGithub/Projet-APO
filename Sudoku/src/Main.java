public class Main {
    public static void main(String[] args) {
        Sudoku s = new Sudoku(Parser.parse3x3(), Pair.create(9, 9));
        Sudoku s2 = new Sudoku(Parser.parse3x3(), Pair.create(9, 9));
        System.out.println(s);

        // Using rules
        s.solveUsingRules();
        System.out.println("\n\n\n\n");
        if(s.solved){
            System.out.println(s);
        } else {
            System.out.println("No solution found");
        }

        // Using backtracking
        s2.solveUsingBacktracking();
        System.out.println("\n\n\n\n");
        System.out.println(s2);
    }
}
