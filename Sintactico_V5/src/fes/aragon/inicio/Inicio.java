package fes.aragon.inicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;

public class Inicio {
	private Tokens tokens = null;
	private Lexico analizador = null;
	
	public static void main(String[] args) {
		Inicio ap = new Inicio();
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
			ap.analizador = new Lexico(buf);
			ap.siguienteToken();
			ASDP sintactico = new ASDP();
			do {
				sintactico.setEntrada(ap.tokens.getToken());
				if (sintactico.sonTerminales()) {
					sintactico.consumir();
					if (ap.tokens.getLexema() == Sym.PUNTOCOMA) {
						System.out.println("Aceptada"+"\n");
						sintactico = new ASDP();
					}
					ap.siguienteToken();
				}else if (sintactico.puedeReemplazar()){
					sintactico.reemplazar(ap.tokens.getLexema());
				}else {
					System.out.println("Error, rechazada"+"\n");
					ap.errorSintactico();
					ap.siguienteToken();
					sintactico = new ASDP();
				}
			} while (ap.tokens.getLexema() != Sym.EOF);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	private void errorSintactico() {
		do {
			if (tokens.getLexema() != Sym.PUNTOCOMA) {
				siguienteToken();
			}
		} while (tokens.getLexema() != Sym.PUNTOCOMA && tokens.getLexema() != Sym.EOF);
	}
}
