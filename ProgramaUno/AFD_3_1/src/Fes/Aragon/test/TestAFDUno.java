package Fes.Aragon.test;

import java.io.IOException;
import java.util.ArrayList;

import Fes.Aragon.utilerias.ErrorLexico;
import Fes.Aragon.utilerias.Herramientas;

public class TestAFDUno {
	public static void main(String[] args) {
		int estado = 0;
		int linea = 0;
		char simbolo = ' ';
		Herramientas hr = new Herramientas();
		ArrayList<String> lineas = null;
		try {
			lineas = hr.lectura();	
			for(int i=0;i<lineas.size();i++) {
				//System.out.println(lineas.get(i));
				hr.setPalabra(lineas.get(i));
				simbolo = hr.siguienteCaracter();
				while(simbolo != ';') {
					switch(estado) {
					case 0:
						if(simbolo == '0') {
							estado = 0;
						}else if(simbolo == '1') {
							estado = 1;
						}else {
							throw new ErrorLexico("Caracter no valido, linea = "+(linea+1));   
						}
						break;
					case 1:
						if(simbolo == '0') {
							estado = 0;
						}else if(simbolo == '1') {
							estado = 2;
						}else {
							throw new ErrorLexico("Caracter no valido, linea = "+(linea+1));   
						}
						break;
					case 2:
						if(simbolo == '0') {
							estado = 0;
						}else if(simbolo == '1') {
							estado = 2;
						}else {
							throw new ErrorLexico("Caracter no valido, linea = "+(linea+1));   
						}
						break;
					}
					simbolo = hr.siguienteCaracter();
				}
				linea = i;
				if(estado == 0 || estado == 1) {
					System.out.println("Palabra no valida " +(linea+1));
				}else {
					System.out.println("Palabra valida " +(linea+1));
				}
				linea = i;
				estado = 0;
				simbolo = ' ';
			}
		}catch (IOException e) {
		// TODO 
		e.printStackTrace();
		} catch (ErrorLexico e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
