package display;
import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    private JTextField textField;

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
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Sudoku Panel"));
        TextArea sudoku = new TextArea(20, 20);
        JButton bouton = new JButton(new LoadSudoku(this, "Charger depuis le fichier", sudoku));
        panel.add(bouton);
        panel.add(sudoku);
        textField = new JTextField(20);
        panel.add(textField);
        return panel;
    }

    public JTextField getTextField() {
        return textField;
    }
}
