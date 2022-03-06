package fes.aragon.test;

import java.io.IOException;
import java.util.ArrayList;

import fes.aragon.utilerias.Herramientas;

public class Test {
	private final int aceptado=1;
	private final int error=2;
	private Herramientas hr=new Herramientas();
	public static void main(String[] args) {	
		ArrayList<String> lineas=null;
		Test app=new Test();
		try {
			lineas=app.hr.lectura();
			for (int i = 0; i < lineas.size(); i++) {
				app.hr.setPalabra(lineas.get(i));
				int valor = app.estado_A();
				if(valor==app.aceptado) {
					System.out.println("Palabra válida");
				}else {
					System.out.println("Palabra no válida");
				}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	private int estado_A() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_B();
		case '1':
			return estado_C();
		case';':
			return aceptado;
		default:
			return error;
		}
	}
	
	private int estado_B() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_D();
		case '1':
			return estado_E();
		case';':
			return aceptado;
		default:
			return error;
		}
	}
	
	private int estado_C() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_Z();
		case '1':
			return estado_F();
		default:
			return error;
			
		}
	}
	
	private int estado_D() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_D();
		case '1':
			return estado_Z();
		case';':
			return aceptado;
		default:
			return error;	
		}
	}
	
	private int estado_E() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_B();
		case '1':
			return estado_G();
		case';':
			return aceptado;			
		default:
			return error;			
		}
	}
	
	private int estado_F() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_B();
		case '1':
			return estado_C();
		case';':
			return aceptado;
			
		default:
			return error;			
		}
	}
	
	private int estado_G() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_B();
		case '1':
			return estado_H();
		case';':
			return aceptado;			
		default:
			return error;			
		}
	}
	
	private int estado_H() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_B();
		case '1':
			return estado_G();
		case';':
			return aceptado;	
		default:
			return error;			
		}
	}
	private int estado_Z() {
		char c = hr.siguienteCaracter();
		switch (c) {
		case '0':
			return estado_Z();
		case '1':
			return estado_Z();
		default:
			return error;			
		}
	}
}
