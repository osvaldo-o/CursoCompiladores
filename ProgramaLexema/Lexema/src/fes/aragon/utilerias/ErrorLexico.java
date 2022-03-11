package fes.aragon.utilerias;

public class ErrorLexico extends Exception{
	private int numeroColumna;
	private int numeroLinea;
	private String error;

	public ErrorLexico(String error) {
		super(error);
		this.error = error;
	}
	
	public ErrorLexico(String error,int numeroColumna, int numeroLinea) {
		super(error);
		this.error = error;
		this.numeroColumna = numeroColumna;
		this.numeroLinea = numeroLinea;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
