package fes.aragon.utilerias;

public class Conjunto {
	public static boolean cero(char c) {
		int valor=c;
		boolean valido=false;
		if(valor==48) {
			valido=true;
		}
		return valido;
	}  
	
	public static boolean uno(char c) {
		int valor=c;
		boolean valido=false;
		if(valor==49) {
			valido=true;
		}
		return valido;
	}
}
