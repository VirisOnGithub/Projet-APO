import java.util.ArrayList;

public class Multidoku {
    private ArrayList<Sudoku> sudokus;
    private ArrayList<ArrayList<Integer>> bindBlocks;

    public Multidoku(ArrayList<Sudoku> sudokus, ArrayList<ArrayList<Integer>> bindBlocks) {
        this.sudokus = sudokus;
        this.bindBlocks = bindBlocks;
    }

    public boolean checkCoherence(){
        for (ArrayList<Integer> bindBlock : bindBlocks) {
            ArrayList<Integer> values = new ArrayList<>();
            // TO FINISH
        }
        return true;
    }
}
