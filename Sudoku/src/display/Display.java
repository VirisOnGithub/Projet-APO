package display;

import javax.swing.*;

/**
 * The Display class is the main
 */
public class Display {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuFrame();
            }
        });
    }
}
