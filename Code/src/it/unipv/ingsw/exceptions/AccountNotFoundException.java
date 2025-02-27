package it.unipv.ingsw.exceptions;

public class AccountNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private static String msgErrore = "Account non trovato!";

	public AccountNotFoundException() {
		super(msgErrore);
	}
}
