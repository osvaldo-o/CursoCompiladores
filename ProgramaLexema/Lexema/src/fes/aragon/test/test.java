package fes.aragon.test;

import java.io.IOException;
import java.util.ArrayList;
import fes.aragon.test.token;
import fes.aragon.utilerias.Conjunto;
import fes.aragon.utilerias.Herramientas;

public class test {
	private Herramientas hr = new Herramientas();
	private int numeroLinea = 0;
	private token token;
	private boolean termino = false;
	private String palabra = "";
	private final int PALABRA_RESERVADA = 0;
	private final int INDENTIFICADOR = 1;
	public static void main(String[] args) {
		test app = new test();
		try {
			ArrayList<String> lineas = app.hr.lectura();
			while(!app.termino) {
				app.hr.setPalabra(lineas.get(app.numeroLinea));
				app.q0();
				app.numeroLinea++;
				app.palabra = "";
				System.out.println(app.token.getToken());
				if (app.numeroLinea==lineas.size()) {
					app.termino = true;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void estadoFinalQ2() {
		if (palabrasReservadas.esPalabraReservada(palabra)) {
			token = new token(PALABRA_RESERVADA,hr.getIndicePalabra(),numeroLinea,-1,"PALABRA RESERVADA");
		}else {
			token = new token(INDENTIFICADOR,hr.getIndicePalabra(),numeroLinea,0,"ASIGNACION", palabra);
		}
	}
	
	private void q0() {
		char simbolo = hr.siguienteCaracter();
		if (Conjunto.letras(simbolo)) {
			palabra = palabra+simbolo;
			q1();
		}else if (Conjunto.entero(simbolo)) {
			palabra = palabra+simbolo;
			q3();
		}
	}
	
	private void q1() {
		char simbolo = hr.siguienteCaracter();
		if (Conjunto.letras(simbolo)) {
			palabra = palabra+simbolo;
			q1();
		}else if(Conjunto.entero(simbolo)) {
			palabra = palabra+simbolo;
			q1();
		}else {
			estadoFinalQ2();
		}
	}
	
	private void q3() {
		char simbolo = hr.siguienteCaracter();
		if (Conjunto.entero(simbolo)) {
			palabra = palabra+simbolo;
			q3();
		}else {
			estadoFinalQ2();
		}
	}
}
