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
		int entrada = 0;
		char fc = ';';
		boolean finCadena = false;
		try {
			Tabla tb = new Tabla(new Herramientas().lectura());
			lineas = hr.lectura();
			int[][] tabla = tb.getTabla();
			for (int i = 0; i < lineas.size(); i++) {
				hr.setPalabra(lineas.get(i));
				linea = i;
				try {
					do {
						simbolo = hr.siguienteCaracter();
						boolean valido = false;
						for (int c=0;c<tb.sizeAlfabeto();c++) {
							if (tb.getAlfabeto(c).equals("LE") && Conjunto.letras(simbolo)) {
								entrada = c;
								valido = true;
							}else if (tb.getAlfabeto(c).equals("DI") && Conjunto.entero(simbolo)) {
								entrada = c; 
								valido = true;
							}else if(tb.getAlfabeto(c).equals(simbolo+"")) {
								entrada = c;
								valido = true;
							}else if(simbolo == fc) {
								finCadena = true;
								entrada = tb.sizeAlfabeto();
								valido = true;
							}
						}
						if (!valido) {
							throw new ErrorLexico("Error, simbolo no reconocido: "+(linea+1));
						}
						estado = tabla[estado][entrada];
					} while(!finCadena);
				} catch (ErrorLexico ex) {
					ex.printStackTrace();
				}
				if (finCadena && estado == 1) {
					System.out.println("Palabra valida " +"(linea: "+(linea + 1)+")");
				} else {
					System.out.println("Palabra no valida " +"(linea: "+(linea + 1)+")");
				}

				estado = 0;
				simbolo = ' ';
				finCadena = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
