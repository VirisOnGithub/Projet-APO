package model;

import java.util.ArrayList;

public class Block {
  private ArrayList<Pair<Integer,Integer>> cases;

  public Block() {
    this.cases = new ArrayList<>();
  }

  public ArrayList<Pair<Integer,Integer>> getCases() {
    return cases;
  }

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

  @Override
  public String toString() {
    return "Block{" +
            "cases=" + cases +
            '}';
  }
}
