package fes.aragon.inicio;

import java.util.Stack;

public class ASDP {
	private final String[][] tabla = { { "A B", "A B", "error", "error" }, { "a", " ", "error", "error" },
			{ "error", "b C d", "error", "error" }, { "error", "error", "c", " " } };
	private Stack<String> pila= new Stack<String>();
	private String reglas;
	private String entrada;
	
	public ASDP() {
		pila.push(";");
		pila.push("S");
		reglas = "";
		entrada = "";
	}
	
	public String toStringPila() {
		return pila.toString();
	}
	
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	
	public boolean sonTerminales() {
		return pila.peek().equals(entrada);
	}
	
	public void consumir() {
		System.out.println("Pila: "+pila.toString()+",Entrada: "+entrada+",Regla: consumir("+pila.pop()+")");
	}
	
	public boolean puedeRemplazar() {
		return indexFila(pila.peek()) != -1;
	}
	
	public void remplazar(int entrada) {
		String info = "Pila: "+pila.toString()+","+"Entrada: "+this.entrada+",";
		reglas = tabla[indexFila(pila.pop())][entrada];
		info = info+("Regla: "+reglas);
		for (String regla : invertir(reglas).split(" ")) {
			pila.push(regla);
		}
		System.out.println(info);
	}
	
	private static String invertir(String palabra) {
		StringBuilder pal = new StringBuilder(palabra);
		return pal.reverse().toString();
	}
	
	private static int indexFila(String dato) {
		String[] filas = { "S", "A", "B", "C" };
		for (int i = 0; i < filas.length; i++) {
			if (dato.equals(filas[i])) {
				return i;
			}
		}
		return -1;
	}
}
