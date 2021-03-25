package com.company.analizadores;

import java.io.IOException;

/**
 *
 * @author Temporal
 */
public final class Parser {

    private final int ID = 1;
    private final int NUM = 2;
    private final int PAR_ABIERTO = 3;
    private final int PAR_CERRADO = 4;
    private final int PLUS = 5;
    private final int TIMES = 6;
    private final int EOF = 0;

    private Lexer lexer;
    private Token token;
    private boolean accept;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        accept = true;
        avanzar();
    }

    //aqui inicia el parseo //produccion inicial
    public void parse() {
        E();
    }

    private void consume(int tk) {
        if (tk == token.tipo) {

            avanzar();
        } else {

            System.out.println("Error  valor: " + token.info + ", Linea: " + token.linea + ", Columna: " + token.columna);
            System.out.println("tipo: " + tk);
            accept = false;
        }
    }

    private void avanzar() {

        try {

            token = lexer.yylex();

            if (token.tipo != 0) {

                System.out.println("siguiente ==> " + token.info);

            }

        } catch (IOException ex) {

            ex.printStackTrace(System.out);

        }
    }

    //no terminal E
    private void E() {
        switch (token.tipo) {
            case ID:
            case NUM:
            case PAR_ABIERTO:
                //produce
                T();
                Ep();

                break;
            default:

                error(token);
        }

        if (token.tipo == 0) {

            if (accept) {

                System.out.println("Cadena aceptada");

            }
        }
    }
//produccion T

    private void T() {

        switch (token.tipo) {
            case ID:
            case NUM:
            case PAR_ABIERTO:
                F();
                Tp();
                break;
            default:

                error(token);
        }
    }

    private void Ep() {
        switch (token.tipo) {
            case PAR_CERRADO:
            case EOF:

                break;

            case PLUS:

                consume(PLUS);
                T();
                Ep();
                break;
            default:
                //Error
                error(token);
        }
    }

    private void F() {
        switch (token.tipo) {
            case ID:
                
                consume(ID);
                
                break;

            case NUM:
                
                consume(NUM);
                
                break;

            case PAR_ABIERTO:
                
                consume(PAR_ABIERTO);
                
                E();
                
                consume(PAR_CERRADO);
                
                break;
            default:
                error(token);
        }
    }

    private void Tp() {
        switch (token.tipo) {
            case PAR_CERRADO:
            case PLUS:
            case EOF:
                // lambda
                break;

            case TIMES:
                // Verificar consumir(TIMES);
                consume(TIMES);
                F();
                Tp();
                break;

            default:
                //Error
                error(token);
        }
    }

    private void error(Token t) {
        
        System.out.println("Error, Valor: " + t.info + ", linea: " + t.linea + ", columna: " + t.columna);
        
        accept = false;
    }
}
