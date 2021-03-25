package com.company.analizadores;

import java.io.StringReader;

/**
 *
 * @author Temporal
 */
public class Principal {

    public static void main(String[] args) {
        
        
        
        Lexer lexer = new Lexer(new StringReader("5 + id + id2 * 3 + (5 * id3)"));
        
        Parser parser = new Parser(lexer);
        parser.parse();
    }
}
