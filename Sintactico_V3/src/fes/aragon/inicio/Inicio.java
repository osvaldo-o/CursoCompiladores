/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.inicio;

import fes.aragon.codigo.ErrorSintactico;
import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author MASH
 */
public class Inicio {
    private Tokens tokens = null;
    private Lexico analizador = null;
    
    public static void main(String[] args) {
        Inicio ap = new Inicio();
        BufferedReader buf;
        try {
            buf = new BufferedReader(
                    new FileReader(System.getProperty("user.dir")
                            + "/archivo.txt"));
            ap.analizador = new Lexico(buf);
            ap.siguienteToken();
            do {
            	try {
            		ap.secuencia();
            	}
        		catch (ErrorSintactico e) {
        			// TODO Auto-generated catch block
        			//e.printStackTrace();
        			System.out.println(e.getMessage());
        			ap.siguienteToken();
        		}
            }while(ap.tokens.getLexema()!= Sym.EOF);        

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    private void secuencia() throws ErrorSintactico {
    	expresion();
    	if(tokens.getLexema() != Sym.PUNTOCOMA){
    		throw new ErrorSintactico("Error sintactico "+(tokens.getLinea()+1));
    	}else {
    		System.out.println("linea correcta "+(tokens.getLinea()+1));
    		siguienteToken();
    	}
    }
    
    private void expresion() throws ErrorSintactico {
    	exprSimple();
    	if (tokens.getLexema() == Sym.IGUAL || tokens.getLexema() == Sym.ME || tokens.getLexema() == Sym.MEI || tokens.getLexema() == Sym.DIST || tokens.getLexema() == Sym.MAI || tokens.getLexema() == Sym.MA) {
    		siguienteToken();
    		exprSimple();
    	}
    }
    
    private void exprSimple() throws ErrorSintactico {
    	if (tokens.getLexema() == Sym.MAS || tokens.getLexema() == Sym.MENOS) {
    		siguienteToken();
    	}
    	termino();
    	while(tokens.getLexema() == Sym.MAS || tokens.getLexema() == Sym.MENOS || tokens.getLexema() == Sym.OR) {
    		siguienteToken();
    		termino();
    	}
    }
    
    private void termino() throws ErrorSintactico {
    	factor();
    	while (tokens.getLexema() == Sym.POR || tokens.getLexema() == Sym.DIV || tokens.getLexema() == Sym.DIV_ENT || tokens.getLexema() == Sym.MOD || tokens.getLexema() == Sym.AND) {
    		siguienteToken();
    		factor();
    	}
    }
    
    private void factor() throws ErrorSintactico {
    	switch (tokens.getLexema()) {
    	case Sym.ID:
    		siguienteToken();
    		break;
    	case Sym.NUM:
    		siguienteToken();
    		break;
    	case Sym.NOT:
    		siguienteToken();
    		factor();
    		break;
    	case Sym.PAR:
    		siguienteToken();
    		expresion();
    		if (tokens.getLexema() != Sym.PARC) {
    			throw new ErrorSintactico("Error sintactico "+(tokens.getLinea()+1));
    		}else {
    			siguienteToken();
    		}
    		break;
    	default:
    		throw new ErrorSintactico("Error sintactico "+(tokens.getLinea()+1));
    	}
    }
    
    private void siguienteToken() {
        try {
            tokens = analizador.yylex();
            if (tokens == null) {
                tokens = new Tokens("EOF", Sym.EOF, 0, 0);
                throw new IOException("Fin Archivo");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}