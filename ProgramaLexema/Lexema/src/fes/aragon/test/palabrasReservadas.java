package fes.aragon.test;

public class PalabrasReservadas {
	public static boolean esPalabraReservada(String palabra) {
		String[] palabrasLenguaje = {"inicio","fin","entero","real","mientras","fin-mientras","si","sino"};
		for (String p:palabrasLenguaje) {
			if (p.equals(palabra)) {
				return true;
			}
		}
		return false;
	}
}
