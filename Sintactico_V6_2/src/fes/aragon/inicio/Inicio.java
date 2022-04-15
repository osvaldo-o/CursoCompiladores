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
	private Stack<Integer> pilaEstados = new Stack<Integer>();
	private Stack<String> pilaSimbolos = new Stack<String>();
	private final int[][] tablaIrA = {{1,2,3},{-1,-1,-1},{-1,-1,-1},{-1,-1,-1},{8,2,3},{-1,-1,-1},{-1,9,3},{-1,-1,10},{-1,-1,-1},{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
	private final String[][] tablaAccion = {{"D 5"," "," ","D 4"," "," "},
											{" ","D 6"," "," "," ","ACEPTAR"},
											{" ","R 2","D 7"," ","R 2","R 2"},
											{" ","R 4","R 4"," ","R 4","R 4"},
											{"D 5"," "," ","D 4"," "," "},
											{" ","R 6","R 6"," ","R 6","R 6"},
											{"D 5"," "," ","D 4"," "," "},
											{"D 5"," "," ","D 4"," "," "},
											{" ","D 6"," "," ","D 11"," "},
											{" ","R 1","D 7"," ","R 1","R 1"},
											{" ","R 3","R 3"," ","R 3","R 3"},
											{" ","R 5","R 5"," ","R 5","R 5"}};
	private Tokens tokens = null;
	private Lexico analizador = null;
	private int linea = 1;
	
	public static void main(String[] args) {
		String regla = "";
		Inicio ap = new Inicio();
		ap.pilaEstados.push(0);
		ap.pilaSimbolos.push(";");
		BufferedReader buf;
		try {
			buf = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/archivo.txt"));
			ap.analizador = new Lexico(buf);
			ap.siguienteToken();
			do {
				regla = ap.tablaAccion[ap.pilaEstados.peek()][ap.tokens.getLexema()];
				ap.hacerAccion(regla);
			} while (ap.tokens.getLexema() != Sym.EOF);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error, rechazada");
		}
	}

	private void hacerAccion(String accion) throws ErrorSintactico{
		if (tokens.getLexema() == Sym.PUNTOCOMA){
			System.out.println("Error");
			errorSintactico();
			siguienteToken();
		}else if (accion.equals(" ")) {
			nada();
			linea = linea+1;
		}else if (!accion.equals(" ")){
			String []a = accion.split(" ");
			switch(a[0]) {
			case "D":
				D(Integer.parseInt(a[1]));
				linea = linea+1;
				break;
			case "R":
				R(Integer.parseInt(a[1]));
				linea = linea+1;
				break;
			case "ACEPTAR":
				System.out.println("("+linea+","+pilaEstados.toString()+","+pilaSimbolos.toString()+","+tokens.getToken()+","+accion);
				siguienteToken();
				break;
			default:
				System.out.println("Error");
				errorSintactico();
				siguienteToken();
			}
		}
	}
	
	private void D(int num) {
		System.out.println("("+linea+","+pilaEstados.toString()+","+pilaSimbolos.toString()+","+tokens.getToken()+",D("+num+"))");
		pilaEstados.push(num);
		pilaSimbolos.push(tokens.getToken());
		siguienteToken();
	}
	
	private void R(int regla) {
		System.out.println("("+linea+","+pilaEstados.toString()+","+pilaSimbolos.toString()+","+tokens.getToken()+",R("+regla+"))");
		for (int i=0;i<reglas.r[regla][1].length();i++) {
			pilaSimbolos.pop();
		}
		pilaSimbolos.push(reglas.r[regla][0]);
		pilaEstados.pop(); 
		ir_A();
	}
	
	private void ir_A() {
		if (tablaIrA[pilaEstados.peek()][getIndice()] != -1) {
			pilaEstados.push(tablaIrA[pilaEstados.peek()][getIndice()]);
		}else if (tablaAccion[pilaEstados.peek()][tokens.getLexema()].equals(" ")){
			linea = linea + 1;
			System.out.println("("+linea+","+pilaEstados.toString()+","+pilaSimbolos.toString()+","+tokens.getToken()+",Nada eliminar "+pilaEstados.peek()+")");
			pilaEstados.pop();
		}
	}
	
	private void nada() {
		if (tablaIrA[pilaEstados.peek()][getIndice()] != -1) {
			System.out.println("("+linea+","+pilaEstados.toString()+","+pilaSimbolos.toString()+","+tokens.getToken()+",Nada eliminar "+pilaEstados.peek()+")");
			pilaEstados.pop();
			pilaEstados.push(tablaIrA[pilaEstados.peek()][getIndice()]);
		}
	}
	
	private int getIndice() {
		String[] col = {"E","T","F"};
		for (int i=0;i<col.length;i++) {
			if (pilaSimbolos.peek().equals(col[i])) {
				return i;
			}
		}
		return -1;
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