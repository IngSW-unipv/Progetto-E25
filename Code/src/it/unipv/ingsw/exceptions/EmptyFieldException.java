package it.unipv.ingsw.exceptions;

public class EmptyFieldException extends Exception {

	private static final long serialVersionUID = 1L;
	private static String msgErrore = "Ci sono dei campi vuoti!";

	public EmptyFieldException() {
		super(msgErrore);
	}
}	


