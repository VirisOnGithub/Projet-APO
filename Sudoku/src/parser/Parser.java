package parser;

import model.Doku;

/**
 * The Parser interface provides a method for parsing a Sudoku puzzle from a file.
 */
public interface Parser {
    Doku parse(String path);
}
