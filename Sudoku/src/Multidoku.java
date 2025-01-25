import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Multidoku {
    private ArrayList<Sudoku> sudokus;
    private ArrayList<ArrayList<Integer>> bindBlocks;

    public Multidoku(ArrayList<Sudoku> sudokus, ArrayList<ArrayList<Integer>> bindBlocks) {
        this.sudokus = sudokus;
        this.bindBlocks = bindBlocks;
    }

    public boolean checkCoherence(){
        for (ArrayList<Integer> bindBlock : bindBlocks) {
            int indexSudoku1 = bindBlock.get(0);
            int blockSudoku1 = bindBlock.get(1);
            int indexSudoku2 = bindBlock.get(2);
            int blockSudoku2 = bindBlock.get(3);
            List<Integer> block1 = sudokus.get(indexSudoku1).getBlock(blockSudoku1);
            List<Integer> block2 = sudokus.get(indexSudoku2).getBlock(blockSudoku2);
            for (int i = 0; i < block1.size(); i++) {
                if (block1.get(i) != 0 && block2.get(i) != 0 && !Objects.equals(block1.get(i), block2.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
