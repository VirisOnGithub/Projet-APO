import model.Sudoku;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Générer un sudoku");
            System.out.println("2. Résoudre un sudoku");
            System.out.println("3. Sortir");
            System.out.print("Choisissez une option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1 -> generateSudoku(sc);
                case 2 -> solveSudoku(sc);
                case 3 -> exit = true;
                default -> System.out.println("Option invalide");
            }

            if (!exit) {
                System.out.print("Voulez-vous continuer (oui/non)? ");
                String response = sc.next();
                if (response.equalsIgnoreCase("non")) {
                    exit = true;
                }
            }
        }
        sc.close();
    }

    private static void generateSudoku(Scanner sc) {
        System.out.print("Entrer la taille du bloc du Sudoku: ");
        int blockSize = sc.nextInt();
        System.out.print("Choisir la difficulté (facile, moyen, difficile): ");
        String difficulty = sc.next();
        System.out.print("Voulez-vous des blocs irréguliers (oui/non)? ");
        boolean irregularBlocks = sc.next().equalsIgnoreCase("oui");
        String[][] sudoku = SudokuGenerator.generateSudoku(blockSize, difficulty, irregularBlocks);
        SudokuGenerator.writeSudokuToFile(sudoku, "src/resources/" + blockSize + "x" + blockSize + ".csv");
        System.out.println("Sudoku généré avec succès et enregistré dans src/resources/" + blockSize + "x" + blockSize + ".csv");
    }

    private static void solveSudoku(Scanner sc) {
        boolean hard = false;
        Sudoku s;
        System.out.println("Taille du sudoku (2, 3, 4, 5, 6, 7, 8, 9) ?");
        int taille = sc.nextInt();
        System.out.println("Sudoku avec blocs normaux ou diformes ? (1/2)");
        int choix = sc.nextInt();
        if (choix == 2) {
            s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        } else {
            s = (new ParserNxN()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + taille + "x" + taille + (hard ? "hard" : "") + ".csv");
        }
        System.out.println("Choisissez une méthode de résolution (backtracking/règles) (1/2)");
        choix = sc.nextInt();
        System.out.println("Solving...\n\n\n");
        long time = System.currentTimeMillis();
        if (choix == 1) {
            s.solveUsingBacktracking();
        } else {
            s.solveUsingRules();
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println(s);
        System.out.println(s.basicToString());
    }
}