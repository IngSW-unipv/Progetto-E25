package it.unipv.ingsw.exceptions;

public class WrongFieldException extends Exception{
	private static String msgErrore = "Errata compilazione dei campi!";

	public WrongFieldException() {
		super(msgErrore);
	}

	public void mostraPopup() {
		PopUpManager.showPopup(msgErrore);
	}
	
	public void mostraPopup(String s) {
		PopUpManager.showPopup(s);
	}
}
