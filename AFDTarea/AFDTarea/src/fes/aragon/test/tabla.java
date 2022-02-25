package fes.aragon.test;
import java.util.ArrayList;

public class tabla {
	
	private int tabla[][];
	private int indexLetra;
	private int indexDigito;
	
	public tabla(ArrayList<String> lineas) {
		ArrayList<Integer> dimensiones = convertir(lineas.get(0));
		this.tabla = new int[dimensiones.get(0)][dimensiones.get(1)];
		String[] head = lineas.get(1).split(" ");
		for (int i=0;i<head.length;i++) {
			if (head[i].equals("L")) {
				this.indexLetra = i;
			}else if(head[i].equals("D")) {
				this.indexDigito = i;
			}
		}
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
	
	public int getIndexLetra() {
		return this.indexLetra;
	}
	
	public int getIndexDigito() {
		return this.indexDigito;
	}
	
	private ArrayList<Integer> convertir(String lineas) {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		for(String caracter : lineas.split(" ")) {
			numeros.add(Integer.parseInt(caracter));
		}
		return numeros;
	}
	
}
