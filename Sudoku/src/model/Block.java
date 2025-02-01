package model;

import java.util.ArrayList;

public class Block {
  private ArrayList<Pair<Integer,Integer>> cases;

  public Block() {
    this.cases = new ArrayList<>();
  }

  public ArrayList<Integer> getCases() {
    ArrayList<Integer> cases = new ArrayList<>();
    for (Pair<Integer, Integer> aCase : this.cases) {
      cases.add(aCase.first);
    }
    return cases;
  }

  public void addCase(Integer value, Integer indexBlock) {
    cases.add(new Pair<>(value, indexBlock));
  }

  @Override
  public String toString() {
    return "Block{" +
            "cases=" + cases +
            '}';
  }
}
