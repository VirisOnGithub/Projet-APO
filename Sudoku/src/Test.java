public class Test {
    public static void main(String[] args) {
        Multidoku m = Parser.parseMultidoku("src/multidoku.csv");
        System.out.println(m.checkCoherence());
    }
}
