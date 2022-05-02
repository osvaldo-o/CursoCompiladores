package fes.aragon.inicio;

import java.util.Stack;

public class ASDP {
	private final String[][] tabla = { { "A B", "A B", "error", "error" }, { "a", "lambda", "error", "error" },
			{ "error", "b C d", "error", "error" }, { "error", "error", "c", "lambda" } };
	private Stack<String> pila= new Stack<String>();
	private String regla;
	private String entrada;
	
	public ASDP() {
		pila.push(";");
		pila.push("S");
		regla = "";
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
	
	public boolean puedeReemplazar() {
		return Integer.parseInt(indexFila(pila.peek())[0]) != -1;
	}
	
	public void reemplazar(int entrada) {
		String info = "Pila: "+pila.toString()+","+"Entrada: "+this.entrada+",";
		String[] fila = indexFila(pila.pop());
		regla = tabla[Integer.parseInt(fila[0])][entrada];
		info = info+("Regla: "+fila[1]+"::="+regla);
		if (!regla.equals("lambda")) {
			for (String regla : invertir(regla).split(" ")) {
				pila.push(regla);
			}
		}
		System.out.println(info);
	}
	
	private static String invertir(String palabra) {
		StringBuilder pal = new StringBuilder(palabra);
		return pal.reverse().toString();
	}
	
	private static String[] indexFila(String dato) {
		String[] filas = { "S", "A", "B", "C" };
		for (int i = 0; i < filas.length; i++) {
			if (dato.equals(filas[i])) {
				return new String[] {i+"",filas[i]};
			}
		}
		return new String[] {"-1"};
	}
}
