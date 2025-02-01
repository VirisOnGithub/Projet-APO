package display;
import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private JTextField textField;
    private TextArea sudoku;

    public SudokuFrame() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setContentPane(sudokuPanel());
    }

    private JPanel sudokuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Sudoku Panel"), BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new GridLayout(1, 3, 10, 0));

        JButton bouton = new JButton(new LoadSudoku(this, "Charger depuis le fichier"));
        southPanel.add(bouton);
        JButton bouton2 = new JButton(new SolveUsingRules(this, "Résoudre avec règles"));
        southPanel.add(bouton2);
        JButton bouton3 = new JButton(new SolveUsingBacktracking(this, "Backtracking"));
        southPanel.add(bouton3);

        panel.add(southPanel, BorderLayout.SOUTH);

        sudoku = new TextArea(9, 9);
        sudoku.setEditable(false);
        panel.add(sudoku, BorderLayout.CENTER);

        JPanel westPanel = new JPanel(new GridLayout(25, 1, 10, 0));

        textField = new JTextField("3x3.csv", 20);
        westPanel.add(textField, BorderLayout.WEST);

        panel.add(westPanel, BorderLayout.WEST);
        return panel;
    }

    public JTextField getTextField() {
        return textField;
    }
    public TextArea getSudoku() {
        return sudoku;
    }
}
