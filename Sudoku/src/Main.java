public class Main {
    public static void main(String[] args) {
        Sudoku s = new Sudoku(Parser.parse3x3(), Pair.create(9, 9));
        System.out.println(s);
    }
}
