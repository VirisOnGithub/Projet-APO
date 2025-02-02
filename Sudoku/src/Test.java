import model.Multidoku;
import parser.ParserMultidoku;

public class Test {
    public static void main(String[] args) {
        Multidoku md = (new ParserMultidoku()).parse("src/resources/multidoku.csv");
        System.out.println(md.checkCoherence());
        md.solveUsingRules();
        System.out.println(md.checkCoherence());
        System.out.println(md);
    }
}
