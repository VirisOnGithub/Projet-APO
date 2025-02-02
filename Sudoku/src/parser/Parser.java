package parser;

import model.Doku;

public interface Parser {
    Doku parse(String path);
}
