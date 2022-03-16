package fes.aragon.test;
import java.util.ArrayList;

public class tabla {
	
	private int tabla[][];
	private String alfabeto[];

	public tabla(ArrayList<String> lineas) {
		int filas = Integer.parseInt(lineas.get(0).split(" ")[0]);
		int columnas = Integer.parseInt(lineas.get(0).split(" ")[1]);
		this.tabla = new int[filas][columnas];
		this.alfabeto = lineas.get(1).split(" ");
		for (int i=0;i<filas;i++) {
			String[] datos = lineas.get(i+2).split(" ");
			for (int j=0;j<columnas;j++) {
				tabla[i][j] = Integer.parseInt(datos[j]);
			}
		}
	}
	
	public int[][] getTabla(){ return this.tabla; }
	
	public String getAlfabeto(int index) { return this.alfabeto[index]; }
	
	public int sizeAlfabeto() { return this.alfabeto.length; }
}
