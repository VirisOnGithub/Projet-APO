package display;

import model.Sudoku;
import parser.SplitBlocksParser;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SolveUsingBacktracking extends AbstractAction {
    private SudokuFrame frame;

    public SolveUsingBacktracking(SudokuFrame frame, String txt) {
        super(txt);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Solve the sudoku
        Sudoku s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + frame.getTextField().getText());
        s.solveUsingBacktracking();
        frame.getSudoku().setText(s.basicToString());
    }
}
