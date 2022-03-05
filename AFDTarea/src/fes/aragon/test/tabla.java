package fes.aragon.test;
import java.util.ArrayList;

public class tabla {
	
	private int tabla[][];
	private String cabezera[];
	
	public tabla(ArrayList<String> lineas) {
		ArrayList<Integer> dimensiones = convertir(lineas.get(0));
		this.tabla = new int[dimensiones.get(0)][dimensiones.get(1)];
		this.cabezera = lineas.get(1).split(" ");
		for (int i=0;i<dimensiones.get(0);i++) {
			ArrayList<Integer> aux = convertir(lineas.get(i+2));
			for (int j=0;j<dimensiones.get(1);j++) {
				tabla[i][j] = aux.get(j);
			}
		}
	}
	
	public int[][] getTabla(){
		return this.tabla;
	}
	
	public String getCabezera(int index) {
		return this.cabezera[index];
	}
	
	public String[] getCabezera() {
		return this.cabezera;
	}

	private ArrayList<Integer> convertir(String lineas) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(String caracter : lineas.split(" ")) {
			numeros.add(Integer.parseInt(caracter));
		}
		return numeros;
	}
	
}
