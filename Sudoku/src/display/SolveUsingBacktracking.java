package display;

import model.Sudoku;
import parser.SplitBlocksParser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The SolveUsingBacktracking class represents the action of solving a Sudoku puzzle using backtracking.
 */
public class SolveUsingBacktracking extends AbstractAction {
    private SudokuFrame frame;

    /**
     * Create the action of solving a Sudoku puzzle using backtracking
     * @param frame The Sudoku frame
     * @param txt The text of the action
     */
    public SolveUsingBacktracking(SudokuFrame frame, String txt) {
        super(txt);
        this.frame = frame;
    }

    /**
     * Solve the Sudoku puzzle using backtracking
     * @param e The event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Solve the sudoku
        Sudoku s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + frame.getTextField().getText());
        s.solveUsingBacktracking();
        frame.getSudoku().setText(s.basicToString());
    }
}
