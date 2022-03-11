package fes.aragon.test;

import java.io.IOException;
import java.util.ArrayList;

import fes.aragon.utilerias.Conjunto;
import fes.aragon.utilerias.ErrorLexico;
import fes.aragon.utilerias.Herramientas;

public class Lexico {
	private final int PALABRA_RESERVADA = -1;
	private final int PALABRA_NO_RESERVADA = 0;
	private ArrayList<String> codigo;
	private ArrayList<ErrorLexico> errores;
	private ArrayList<Simbolo> simbolos;
	private int numeroLinea;
	private int numeroErrores;
	private int estado;
	private int indiceSimbolos;
	private String tokenReconocidos;
	private Herramientas hr;
	private char simbolo;
	private String palabra;
	public Lexico() throws ErrorLexico {
		hr = new Herramientas();
		this.numeroLinea = 0;
		this.numeroErrores = 0;
		this.tokenReconocidos = "";
		this.estado = 0;
		this.indiceSimbolos++;
		this.errores = new ArrayList<>();
		this.simbolos = new ArrayList<>();
		this.palabra = "";
		try {
			this.codigo = hr.lectura();
			hr.setPalabra(codigo.get(this.numeroLinea));
			simbolo = hr.siguienteCaracter();
		} catch (IOException e) {
			numeroErrores++;
			this.errores.add(new ErrorLexico("Error en el archivo",hr.getIndicePalabra()+1,numeroLinea+1));
			throw new ErrorLexico("Error en el archivo");
		}
	}
	
	public Token siguienteToken() {
		boolean tokenEncontrado = false;
		Token t = null;
		Simbolo s = null;
		this.palabra = "";
		while (numeroLinea < this.codigo.size() && numeroErrores < 5 && tokenEncontrado == false) {
			switch (this.estado) {
			case 0:
				if (simbolo == ' ' || simbolo == '\t' || simbolo == '\n') {
					simbolo = hr.siguienteCaracter();
					while (simbolo == ' ') {
						simbolo = hr.siguienteCaracter();
					}
					if (simbolo == '\n') {
						this.estado = 0;
						this.numeroLinea++;
						if (numeroLinea < this.codigo.size()) {
							hr.setPalabra(codigo.get(this.numeroLinea));
							simbolo = hr.siguienteCaracter();
						}
					}
				}else if (Conjunto.letras(simbolo)) {
					this.estado = 1;
				}else if (Conjunto.entero(simbolo)) {
					this.estado = 3;
				}
				break;
			case 1:
				do {
					if (Conjunto.letras(simbolo) || Conjunto.entero(simbolo)) {
						this.palabra += simbolo;
					}
					simbolo = hr.siguienteCaracter();
				} while (Conjunto.letras(simbolo) || Conjunto.entero(simbolo));
				this.estado = 2;
				break;
			case 2:
				this.estado = 0;
				tokenEncontrado = true;
				t = identificadorAsignarId(palabra);
				break;
			}
		}
		
	}
	
	private Token identificadorAsignarId(String palabra) {
		String tipoToken = "";
		Token t = null;
		Simbolo s = null;
		boolean encontrado = false;
		if (PalabrasReservadas.esPalabraReservada(palabra)) {
			tipoToken = "PALABRA RESERVADA";
			encontrado = true;
		}
		if (encontrado) {
			t = new Token(0,hr.getIndicePalabra()+1,numeroLinea+1,this.PALABRA_RESERVADA,this.palabra,tipoToken);
			this.tokenReconocidos += t.getToken() + "\n";
		}else {
			encontrado = false;
			if (palabra.length() < 16) {
				//int j = 0;
				for (int i=0; i<this.simbolos.size(); i++) {
					if (palabra.equals(this.simbolos.get(i).getSimbolo())) {
						encontrado = true;
						//j = i;
						break;
					}
				}
				if (encontrado) {
					t = new Token(1,((hr.getIndicePalabra()-this.palabra.length())+1),this.numeroLinea+1,this.PALABRA_NO_RESERVADA,this.palabra,"IDENTIFICADOR");
					this.tokenReconocidos += t.getToken() + "\n";
				}else {
					s = new Simbolo(1,((hr.getIndicePalabra()-this.palabra.length())+1),this.numeroLinea+1,this.palabra,"",false,false);
					simbolos.add(s);
					t = new Token(1,((hr.getIndicePalabra()-this.palabra.length())+1),this.numeroLinea+1,this.PALABRA_NO_RESERVADA,this.palabra,"IDENTIFICADOR");
					this.tokenReconocidos += t.getToken() + "\n";
				}
			}else {
				numeroErrores++;
				ErrorLexico error = new ErrorLexico("Identificador demasiado largo",((hr.getIndicePalabra()-this.palabra.length())+1),this.numeroLinea+1);
				this.errores.add(error);
				
			}
		}
		return t;
	}
	
}
