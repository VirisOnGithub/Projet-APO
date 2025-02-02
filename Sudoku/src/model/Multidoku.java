package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Multidoku class represents a Multidoku puzzle.
 */
public class Multidoku implements Doku {
    private ArrayList<Sudoku> sudokus;
    private ArrayList<ArrayList<Integer>> bindBlocks;

    /**
     * Create a Multidoku puzzle
     * @param sudokus The Sudokus of the Multidoku puzzle
     * @param bindBlocks The bind blocks of the Multidoku puzzle
     */
    public Multidoku(ArrayList<Sudoku> sudokus, ArrayList<ArrayList<Integer>> bindBlocks) {
        this.sudokus = sudokus;
        this.bindBlocks = bindBlocks;
    }

    /**
     * Check if a block is already binded
     * @param indexSudoku1 The index of the first Sudoku
     * @param blockSudoku1 The index of the block in the first Sudoku
     * @return The index of the second Sudoku and the index of the block in the second Sudoku if the block is already binded, null otherwise
     */
    public Pair<Integer, Integer> hasBindBlock(int indexSudoku1, int blockSudoku1) {
        for (ArrayList<Integer> bindBlock : bindBlocks) {
            if (bindBlock.get(0) == indexSudoku1 && bindBlock.get(1) == blockSudoku1) {
                return new Pair<>(bindBlock.get(2), bindBlock.get(3));
            }
            if(bindBlock.get(2) == indexSudoku1 && bindBlock.get(3) == blockSudoku1){
                return new Pair<>(bindBlock.get(0), bindBlock.get(1));
            }
        }
        return null;
    }

    /**
     * Check if the Multidoku puzzle is coherent
     * @return True if the Multidoku puzzle is coherent, false otherwise
     */
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

    /**
     * Get the Sudokus of the Multidoku puzzle
     * @return The Sudokus of the Multidoku puzzle
     */
    public ArrayList<Sudoku> getSudokus() {
        return sudokus;
    }

    /**
     * Get the bind blocks of the Multidoku puzzle
     * @return The bind blocks of the Multidoku puzzle
     */
    public ArrayList<ArrayList<Integer>> getBindBlocks() {
        return bindBlocks;
    }

    /**
     * Solve the Multidoku puzzle using rules 
     */
    public void solveUsingRules() {
        boolean solved = false;
        for(Sudoku sudoku : sudokus){
            boolean progressMade = true;
            while (!solved && progressMade) {
                solved = true;
                progressMade = false;
                for (int i = 0; i < sudoku.dimensions.first; i++) {
                    for (int j = 0; j < sudoku.dimensions.second; j++) {
                        int index = i * sudoku.dimensions.second + j;
                        if (sudoku.sudoku.get(index).first == 0) {
                            solved = false;

                            ArrayList<Integer> possibleValues = getPossibleValues(sudoku, i, j);

                            Pair<Integer, Integer> bindBlock = hasBindBlock(sudokus.indexOf(sudoku), sudoku.sudoku.get(index).second);
                            if (bindBlock != null) {
                                List<Integer> block = sudokus.get(bindBlock.first).getBlock(bindBlock.second);
                                for (Integer integer : block) {
                                    if (integer != 0) {
                                        possibleValues.remove((Integer) integer);
                                    }
                                }
                            }

                            if (possibleValues.size() == 1) {
                                sudoku.sudoku.set(index, new Pair<>(possibleValues.get(0), sudoku.sudoku.get(index).second));
                                sudoku.blocks.get(sudoku.sudoku.get(index).second).getCases().add(new Pair<>(possibleValues.get(0), index));
                                progressMade = true;
                            } else if (possibleValues.isEmpty()) {
                                return;
                            }
                        }
                    }
                }
                for (ArrayList<Integer> bindBlock : bindBlocks) {
                    int indexSudoku1 = bindBlock.get(0);
                    int blockSudoku1 = bindBlock.get(1);
                    int indexSudoku2 = bindBlock.get(2);
                    int blockSudoku2 = bindBlock.get(3);
                    List<Integer> block1 = sudokus.get(indexSudoku1).getBlock(blockSudoku1);
                    List<Integer> block2 = sudokus.get(indexSudoku2).getBlock(blockSudoku2);
                    for (int i = 0; i < block1.size(); i++) {
                        if (block1.get(i) == 0 && block2.get(i) != 0) {
                            block1.set(i, block2.get(i));
                        } else if (block1.get(i) != 0 && block2.get(i) == 0) {
                            block2.set(i, block1.get(i));
                        }
                    }
                }
            }
        }
    }

    /**
     * Get the possible values for a case
     * @param sudoku The Sudoku puzzle
     * @param i The row of the case
     * @param j The column of the case
     * @return The possible values for the case
     */
    private ArrayList<Integer> getPossibleValues(Sudoku sudoku, int i, int j) {
        ArrayList<Integer> possibleValues = new ArrayList<>();
        // Add all possibilities to the case
        for (int k = 1; k <= sudoku.dimensions.first; k++) {
            possibleValues.add(k);
        }

        // Check if value in column is already taken
        for (int k = 0; k < sudoku.dimensions.first; k++) {
            int index1 = k * sudoku.dimensions.second + j;
            possibleValues.remove((Integer) sudoku.sudoku.get(index1).first);
        }

        // Check if value in row is already taken
        for (int k = 0; k < sudoku.dimensions.second; k++) {
            int index1 = i * sudoku.dimensions.second + k;
            possibleValues.remove((Integer) sudoku.sudoku.get(index1).first);
        }
        int index = i * sudoku.dimensions.second + j;
        // Check if value in block is already taken
        for (Pair<Integer,Integer> indexSudoku : sudoku.blocks.get(sudoku.sudoku.get(index).second).getCases()) {
            if (indexSudoku.first != index) {
                possibleValues.remove((Integer) sudoku.sudoku.get(indexSudoku.first).first);
            }
        }
        return possibleValues;
    }


    /**
     * Prints the Multidoku puzzle to the output
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sudoku sudoku : sudokus) {
            sb.append(sudoku.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
