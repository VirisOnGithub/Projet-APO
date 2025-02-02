package display;

import model.Sudoku;
import parser.SplitBlocksParser;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SolveUsingRules extends AbstractAction {
    private SudokuFrame frame;

    public SolveUsingRules(SudokuFrame frame, String txt) {
        super(txt);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Solve the sudoku
        Sudoku s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + frame.getTextField().getText());
        s.solveUsingRules();
        frame.getSudoku().setText(s.basicToString());
    }
}
