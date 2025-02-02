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
  public ArrayList<Integer> getCases() {
    ArrayList<Integer> cases = new ArrayList<>();
    for (Pair<Integer, Integer> aCase : this.cases) {
      cases.add(aCase.first);
    }
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
