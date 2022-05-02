package fes.aragon.inicio;

import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inicio {
	private Tokens tokens = null;
	private Lexico analizador = null;
	private SLR sintactico;

	public static void main(String[] args) {
		Inicio ap = new Inicio();
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
			ap.analizador = new Lexico(buf);
			ap.sintactico = new SLR();
			ap.siguienteToken();
			do {
				ap.sintactico.setAccion(ap.tokens);
				ap.hacerAccion();
			} while (ap.tokens.getLexema() != Sym.EOF);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private void hacerAccion() {
		String []accion = sintactico.getAccion().split(" ");
		if (sintactico.esRechadaza()) {
			System.out.println("Rechazada");
			errorSintactico();
			siguienteToken();
			reset();
		}else if (sintactico.getAccion().equals(" ")){
			sintactico.hacerNada();
		}else if (accion[0].equals("D")) {
			sintactico.D(Integer.parseInt(accion[1]));
			siguienteToken();
		}else if (accion[0].equals("R")) {
			sintactico.R(Integer.parseInt(accion[1]));
		}else if (accion[0].equals("ACEPTAR")) {
			sintactico.aceptar();
			siguienteToken();
			reset();
		}
	}
	
	private void reset() {
		sintactico = new SLR();
		System.out.println("");
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

	private void errorSintactico() {
		do {
			if (tokens.getLexema() != Sym.PUNTOCOMA) {
				siguienteToken();
			}
		} while (tokens.getLexema() != Sym.PUNTOCOMA && tokens.getLexema() != Sym.EOF);
	}

}