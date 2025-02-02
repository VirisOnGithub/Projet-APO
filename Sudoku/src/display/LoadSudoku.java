package display;

import model.Sudoku;
import parser.SplitBlocksParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoadSudoku extends AbstractAction {
    private SudokuFrame frame;
    private TextArea sudoku;

    public LoadSudoku(SudokuFrame frame, String txt) {
        super(txt);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Load the sudoku from the file
        Sudoku s = (new SplitBlocksParser()).parse((System.getProperty("os.name").equals("Linux") ? "src/resources/" : "Sudoku\\src\\resources\\") + frame.getTextField().getText());
        frame.getSudoku().setText(s.basicToString());
    }
}