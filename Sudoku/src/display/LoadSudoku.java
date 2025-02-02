package display;

import model.Sudoku;
import parser.SplitBlocksParser;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The LoadSudoku class represents the action of loading a Sudoku puzzle from a file.
 */
public class LoadSudoku extends AbstractAction {
    private SudokuFrame frame;

    /**
     * Create the action of loading a Sudoku puzzle from a file
     * @param frame The Sudoku frame
     * @param txt The text of the action
     */
    public LoadSudoku(SudokuFrame frame, String txt) {
        super(txt);
        this.frame = frame;
    }

    /**
     * Load the Sudoku puzzle from a file
     * @param e The event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Load the sudoku from the file
        Sudoku s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + frame.getTextField().getText());
        frame.getSudoku().setText(s.basicToString());
    }
}