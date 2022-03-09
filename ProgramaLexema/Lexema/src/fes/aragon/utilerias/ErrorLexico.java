package fes.aragon.utilerias;

public class ErrorLexico extends Exception{
	private static final long serialVersionUID = 1L;
	private int numeroColumna;
	private int numeroLinea;
	private String error;

	public ErrorLexico(String msg) {
		super(msg);
	}
}
