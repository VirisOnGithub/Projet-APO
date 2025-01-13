public class Main {
    public static void main(String[] args) {
        Sudoku s = new Sudoku(Parser.parse3x3(), Pair.create(9, 9));
        System.out.println(s);
        boolean isSolved = s.solveUsingRules();
        System.out.println("\n\n\n\n");
        if(isSolved){
            System.out.println(s);
        } else {
            System.out.println("No solution found");
        }
    }
}
