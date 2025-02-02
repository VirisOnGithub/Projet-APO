package model;

import java.util.ArrayList;

/**
 * The Block class represents a block of a Sudoku puzzle.
 */
public class Block {
  private ArrayList<Pair<Integer,Integer>> cases;

    /**
     * Create a block
     */
  public Block() {
    this.cases = new ArrayList<>();
  }
    /**
     * Get the cases of the block
     * @return The cases of the block
     */
  public ArrayList<Pair<Integer,Integer>> getCases() {
    return cases;
  }

  /**
   * Add a case to the block
   * @param value The value of the case
   * @param indexBlock The index of the block
   */
  public void addCase(Integer value, Integer indexBlock) {
    cases.add(new Pair<>(value, indexBlock));
  }

    public void removeCase(Integer value, Integer indexBlock) {
        cases.remove(new Pair<>(value, indexBlock));
    }

  public boolean isValid(Integer number) {
    for (Pair<Integer, Integer> aCase : cases) {
      if (aCase.getFirst().equals(number)) {
        return false;
      }
    }
    return true;
  }
    /**
     * Print the block to the console
     */
  @Override
  public String toString() {
    return "Block{" +
            "cases=" + cases +
            '}';
  }
}
