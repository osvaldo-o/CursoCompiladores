package fes.aragon.test;

import java.util.Iterator;

import fes.aragon.utilerias.ErrorLexico;

public class pruebas {
	public static void main(String[] args) {
		try {
			Lexico test = new Lexico();
			for (int i=0;i<8;i++) {
				Token t = test.siguienteToken();
				System.out.println(t.toString());
			}
		} catch (ErrorLexico e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
}
