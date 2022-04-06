package fes.aragon.inicio;

import fes.aragon.codigo.ErrorSintactico;
import fes.aragon.codigo.Lexico;
import fes.aragon.codigo.Sym;
import fes.aragon.codigo.Tokens;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Inicio {
	private Tokens tokens = null;
	private Lexico analizador = null;
	private Stack<String> pilaSimbolos = new Stack<String>();
	private static String entrada;
	private static String reglas;
	
	public static void main(String[] args) {
		Inicio ap = new Inicio();
		BufferedReader buf;
		String[][] tabla = { { "A B", "A B", "error", "error" }, { "a", " ", "error", "error" },
				{ "error", "b C d", "error", "error" }, { "error", "error", "c", " " } };
		ap.pilaSimbolos.push(";");
		ap.pilaSimbolos.push("S");
		String info =  "";
		entrada = "";
		reglas = "";
		try {
			buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
			ap.analizador = new Lexico(buf);
			ap.siguienteToken();
			do {
				entrada = ap.tokens.getToken();
				if (ap.pilaSimbolos.peek().equals(entrada)) {
					System.out.println("Pila: "+ap.pilaSimbolos.toString()+","+"Entrada: "+entrada+","+"Regla: consumir("+ap.pilaSimbolos.pop()+")");
					if(ap.tokens.getLexema() == Sym.PUNTOCOMA) {
						System.out.println("Aceptada");
						ap.reset();
					}
					ap.siguienteToken();
				}else if(ap.indexFila(ap.pilaSimbolos.peek()) != -1){
					info = info+("Pila de Simbolos: "+ap.pilaSimbolos.toString()+","+"Entrada: "+entrada+",");
					reglas = tabla[ap.indexFila(ap.pilaSimbolos.pop().toString())][ap.tokens.getLexema()];
					info = info+("Regla: "+reglas);
					for (String regla : ap.invertir(reglas).split(" ")) {
						ap.pilaSimbolos.push(regla);
					}
					System.out.println(info);
					info = "";
				}else {
					System.out.println("Error, No aceptada");
					ap.errorSintactico();
					ap.siguienteToken();
				}
			} while (ap.tokens.getLexema() != Sym.EOF);
		} catch (IOException e) {
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
	
	private int indexFila(String dato) throws IOException {
		String[] filas = { "S", "A", "B", "C" };
		for (int i = 0; i < filas.length; i++) {
			if (dato.equals(filas[i])) {
				return i;
			}
		}
		return -1;
	}

	private String invertir(String palabra) {
		StringBuilder pal = new StringBuilder(palabra);
		return pal.reverse().toString();
	}
	
	private void errorSintactico() {
		do {
			if (tokens.getLexema() != Sym.PUNTOCOMA) {
				siguienteToken();
			}
		} while (tokens.getLexema() != Sym.PUNTOCOMA && tokens.getLexema() != Sym.EOF);
		reset();
	}
	
	private void reset() {
		pilaSimbolos = new Stack<String>();
		pilaSimbolos.push(";");
		pilaSimbolos.push("S");
		entrada = "";
		reglas = "";
	}
}