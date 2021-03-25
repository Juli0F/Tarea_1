package com.company.analizadores;

/**
 *
 * @author cesar31
 */
public class Token {

    public int tipo;
    public int linea;
    public int columna;
    public Object info;

    public Token(int type, int linea, int columna) {
        
        this.tipo = type;
        this.linea = linea;
        this.columna = columna;
        
    }

    public Token(int tipo, int linea, int column, Object info) {
        
        this.tipo = tipo;
        this.linea = linea;
        this.columna = column;
        this.info = info;
        
    }
}
