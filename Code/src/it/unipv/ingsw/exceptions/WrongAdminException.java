package it.unipv.ingsw.exceptions;

public class WrongAdminException  extends Exception {
	private static String msgErrore = "Admin non valido!";
	
	public WrongAdminException() {
		super(msgErrore);

	}
}