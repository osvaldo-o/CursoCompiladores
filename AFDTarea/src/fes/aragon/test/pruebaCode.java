package fes.aragon.test;

import java.io.IOException;
import java.util.ArrayList;

import fes.aragon.utilerias.Herramientas;

public class pruebaCode {
	public static void main(String[] args) {
		Herramientas hr = new Herramientas();
		try {
			ArrayList<String> lineas = hr.lectura();
			tabla test = new tabla(lineas);
			System.out.println(test.getTabla()[1][1]);
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}
