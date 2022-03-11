package fes.aragon.utilerias;

public class Conjunto {
	public static boolean letras(char c) {
		int valor = c;
		boolean valido = false;
		if ((valor >= 65 && valor <= 90) || (valor >= 97 && valor <= 122)) {
			valido = true;
		}
		return valido;
	}
	
	public static boolean entero(char c) {
		int valor = c;
		boolean valido = false;
		if (valor >= 48 && valor <= 57) {
			valido = true;
		}
		return valido;
	}
	
	public static boolean finCadena(char caracter) {
		return caracter == 59;
	}
}
