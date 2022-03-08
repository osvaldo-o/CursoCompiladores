package fes.aragon.test;
import java.util.ArrayList;

public class tabla {
	
	private int tabla[][];
	private String cabezera[];

	public tabla(ArrayList<String> lineas) {
		int dimensiones[] = new int[2];
		dimensiones[0] = Integer.parseInt(lineas.get(0).split(" ")[0]);
		dimensiones[1] = Integer.parseInt(lineas.get(0).split(" ")[1]);
		this.tabla = new int[dimensiones[0]][dimensiones[1]];
		this.cabezera = lineas.get(1).split(" ");
		for (int i=0;i<dimensiones[0];i++) {
			String[] datos = lineas.get(i+2).split(" ");
			for (int j=0;j<dimensiones[1];j++) {
				tabla[i][j] = Integer.parseInt(datos[j]);
			}
		}
	}
	
	public int[][] getTabla(){ return this.tabla; }
	
	public String getCabezera(int index) { return this.cabezera[index]; }
	
	public String[] getCabezera() { return this.cabezera; }
}
