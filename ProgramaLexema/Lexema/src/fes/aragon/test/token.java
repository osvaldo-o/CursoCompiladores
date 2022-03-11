package fes.aragon.test;

public class Token {
	private int tipoToken;
	private int numeroColumna;
	private int numeroLinea;
	private int posicionSimbolo;
	private String token;
	private String nombreVariable;
	
	public Token() {
	}

	public Token(int tipoToken, int numeroColumna, int numeroLinea, int posicionSimbolo, String token, String nombreVariable) {
		this.tipoToken = tipoToken;
		this.numeroColumna = numeroColumna;
		this.numeroLinea = numeroLinea;
		this.posicionSimbolo = posicionSimbolo;
		this.token = token;
		this.nombreVariable = nombreVariable;
	}
	
	public Token(int tipoToken, int numeroColumna, int numeroLinea, int posicionSimbolo, String token) {
		super();
		this.tipoToken = tipoToken;
		this.numeroColumna = numeroColumna;
		this.numeroLinea = numeroLinea;
		this.posicionSimbolo = posicionSimbolo;
		this.token = token;
	}

	public int getTipoToken() {
		return tipoToken;
	}

	public void setTipoToken(int tipoToken) {
		this.tipoToken = tipoToken;
	}

	public int getNumeroColumna() {
		return numeroColumna;
	}

	public void setNumeroColumna(int numeroColumna) {
		this.numeroColumna = numeroColumna;
	}

	public int getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public int getPosicionSimbolo() {
		return posicionSimbolo;
	}

	public void setPosicionSimbolo(int posicionSimbolo) {
		this.posicionSimbolo = posicionSimbolo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNombreVariable() {
		return nombreVariable;
	}

	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	
	
}
