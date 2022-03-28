package fes.aragon.test;

import java.io.IOException;
import java.util.ArrayList;

import fes.aragon.utilerias.Conjunto;
import fes.aragon.utilerias.ErrorLexico;
import fes.aragon.utilerias.Herramientas;

public class Test {
	public static void main(String[] args) {
		int estado = 0;
		int linea = 0;
		char simbolo = ' ';
		Herramientas hr = new Herramientas();
		ArrayList<String> lineas = null;
		int[][] tabla = {{1,2,0},{1,2,0},{6,3,0},{4,5,1},{4,5,1},{4,5,1},{6,6,0}};
		int entrada = 0;
		char fc = ';';
		boolean finDeCadena = false;
		try {
			lineas = hr.lectura();
			for (int i = 0; i < lineas.size(); i++) {
				linea = i;
				hr.setPalabra(lineas.get(i));
				try {
					do {
						simbolo = hr.siguienteCaracter();
						if(Conjunto.cero(simbolo)) {
							entrada = 0;
						} else if(Conjunto.uno(simbolo)) {
							entrada = 1; 
						} else if(simbolo == fc) {
							entrada = 2;
							finDeCadena=true;
						} else {
							throw new ErrorLexico("Error, símbolo no reconocido: "+(linea+1));
						}
						estado=tabla[estado][entrada];
					} while(!finDeCadena);
				} catch (ErrorLexico ex) {
					ex.printStackTrace();
				}  
				if (finDeCadena && estado == 1) {
					System.out.println("Palabra válida " + (linea + 1));
				} else {
					System.out.println("Palabra no válida " + (linea + 1));
				}
				estado = 0;
				simbolo = ' ';
				finDeCadena = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
