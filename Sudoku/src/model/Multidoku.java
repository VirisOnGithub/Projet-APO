package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Multidoku implements Doku {
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

    public ArrayList<Sudoku> getSudokus() {
        return sudokus;
    }

    public ArrayList<ArrayList<Integer>> getBindBlocks() {
        return bindBlocks;
    }

    public void solveUsingRules() {
        boolean solved = false;
        for(Sudoku sudoku : sudokus){
            boolean progressMade = true;
            int blockSize = (int) Math.sqrt(sudoku.dimensions.first);
            while (!solved && progressMade) {
                solved = true;
                progressMade = false;
                for (int i = 0; i < sudoku.dimensions.first; i++) {
                    for (int j = 0; j < sudoku.dimensions.second; j++) {
                        int index = i * sudoku.dimensions.second + j;
                        if (sudoku.sudoku.get(index).first == 0) {
                            solved = false;
                            ArrayList<Integer> possibleValues = new ArrayList<>();
                            for (int k = 1; k <= sudoku.dimensions.first; k++) {
                                possibleValues.add(k);
                            }
                            for (int k = 0; k < sudoku.dimensions.first; k++) {
                                int index1 = k * sudoku.dimensions.second + j;
                                possibleValues.remove(sudoku.sudoku.get(index1).first);
                            }
                            for (int k = 0; k < sudoku.dimensions.second; k++) {
                                int index1 = i * sudoku.dimensions.second + k;
                                possibleValues.remove(sudoku.sudoku.get(index1).first);
                            }
                            int boxRow = i / blockSize;
                            int boxCol = j / blockSize;
                            for (int k = 0; k < blockSize; k++) {
                                for (int l = 0; l < blockSize; l++) {
                                    int index1 = (boxRow * blockSize + k) * sudoku.dimensions.second + (boxCol * blockSize + l);
                                    possibleValues.remove(sudoku.sudoku.get(index1).first);
                                }
                            }
                            if (possibleValues.size() == 1) {
                                sudoku.sudoku.set(index, new Pair<>(possibleValues.getFirst(), 9));
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


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sudoku sudoku : sudokus) {
            sb.append(sudoku.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
