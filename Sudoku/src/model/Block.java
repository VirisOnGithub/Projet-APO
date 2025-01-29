package model;

import java.util.ArrayList;

public class Block {
  private ArrayList<Integer> cases;

  public Block() {
    this.cases = new ArrayList<>();
  }

  public ArrayList<Integer> getCases() {
    return cases;
  }

  public void addCase(Integer indexCase) {
    this.cases.add(indexCase);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Integer aCase : cases) {
      sb.append(aCase).append("-");
    }
    return sb.toString();
  }
}
