package fes.aragon.inicio;

import java.util.Stack;

import fes.aragon.codigo.Tokens;

public class SLR {
	private final int NO_ENCONTRADO = -1;
	private Stack<Integer> pilaEstados = new Stack<Integer>();
	private Stack<String> pilaSimbolos = new Stack<String>();
	private int linea;
	private String accion;
	private Tokens token;
	private Boolean rechazada;
	private final String[][] tablaAccion = { { " ", " ", "D 8", "D 10", "D 11", "D 12", " ", " " },
			{ "D 2", " ", " ", " ", " ", " ", " ", "ACEPTAR" }, { " ", " ", "D 8", "D 10", "D 11", "D 12", " ", " " },
			{ "R 1", "D 5", " ", " ", " ", " ", "R 1", "R 1" }, { "R 2", "D 5", " ", " ", " ", " ", "R 2", "R 2" },
			{ " ", " ", "D 8", "D 10", "D 11", "D 12", " ", " " }, { "R 3", "R 3", " ", " ", " ", " ", "R 3", "R 3" },
			{ "R 4", "R 4", " ", " ", " ", " ", "R 4", "R 4" }, { " ", " ", "D 8", "D 10", "D 11", "D 12", " ", " " },
			{ "R 5", "R 5", " ", " ", " ", " ", "R 5", "R 5" }, { "R 6", "R 6", " ", " ", " ", " ", "R 6", "R 6" },
			{ "R 7", "R 7", " ", " ", " ", " ", "R 7", "R 7"}, { " ", " ", "D 8", "D 10", "D 11", "D 12", " ", " " },
			{ "D 2", " ", " ", " ", " ", " ", "D 14", " " }, { "R 8", "R 8", " ", " ", " ", " ", "R 8", "R 8" }};
	private final int[][] tablaIrA = { { 1, 4, 7 }, { -1, -1, -1 }, { -1, 3, 7 }, { -1, -1, -1 }, { -1, -1, -1 },
			{ -1, -1, 6 }, { -1, -1, -1 }, { -1, -1, -1 }, { -1, -1, 9 }, { -1, -1, -1 }, { -1, -1, -1 }, { -1, -1, -1 },
			{ 13, 4, 7 }, { -1, -1, -1 },{ -1, -1, -1 }};
	
	public SLR() {
		pilaEstados.push(0);
		pilaSimbolos.push(";");
		linea = 1;
		rechazada = false;
	}
	
	public void setAccion(Tokens newToken) {
		this.token = newToken;
		accion = tablaAccion[pilaEstados.peek()][token.getLexema()];
	}
	
	public String getAccion() {
		return accion;
	}
	
	public boolean esRechadaza() {
		return rechazada;
	}
	
	public void hacerNada() {
		if (getIndice() != NO_ENCONTRADO) {
			if (tablaIrA[pilaEstados.peek()][getIndice()] != -1) {
				int esUn = tablaIrA[pilaEstados.peek()][getIndice()];
				pilaEstados.pop();
				System.out.println("Linea: " + linea + ", Pila: " + pilaEstados.toString() + ", Simbolo: " + pilaSimbolos.toString()+ ", Entrada: " + token.getToken() + ", Accion: Es un " + pilaEstados.push(esUn));
			}else {
				System.out.println("Linea: " + linea + ", Pila: " + pilaEstados.toString() + ", Simbolo: " + pilaSimbolos.toString()+ ", Entrada: " + token.getToken() + ", Accion: Nada, eliminar " + pilaEstados.pop());
			}
		}else {
			rechazada = true;
		}
		linea++;
	}
	
	public void D(int num) {
		System.out.println("Linea: " + linea + ", Pila: " + pilaEstados.toString() + ", Simbolo: "+ pilaSimbolos.toString() + ", Entrada: " + pilaSimbolos.push(token.getToken()) + ", Accion: D(" + pilaEstados.push(num) + ")");
		linea++;
	}
	
	public void R(int num) {
		System.out.println("Linea: " + linea + ", Pila: " + pilaEstados.toString() + ", Simbolo: "+ pilaSimbolos.toString() + ", Entrada: " + token.getToken() + ", Accion: R(" + num + ")");
		for (int i = 0; i < reglas.r[num][1].split(" ").length; i++) {
			pilaSimbolos.pop();
			pilaEstados.pop();
		}
		pilaSimbolos.push(reglas.r[num][0]);
		ir_A();
		linea++;
	}
	
	public void aceptar() {
		System.out.println("Linea: "+linea+", Pila: "+pilaEstados.toString()+", Simbolo: "+pilaSimbolos.toString()+", Entrada: "+token.getToken()+", Accion: ACEPTAR");
	}
	
	private void ir_A() {
		if (tablaIrA[pilaEstados.peek()][getIndice()] != NO_ENCONTRADO) {
			pilaEstados.push(tablaIrA[pilaEstados.peek()][getIndice()]);
		}
	}
	
	private int getIndice() {
		String[] col = { "E", "T", "F" };
		for (int i = 0; i < col.length; i++) {
			if (pilaSimbolos.peek().equals(col[i])) {
				return i;
			}
		}
		return NO_ENCONTRADO;
	}
}
