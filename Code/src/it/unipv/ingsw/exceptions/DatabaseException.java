package it.unipv.ingsw.exceptions;


public class DatabaseException extends Exception {

	private static String msgErrore = "C'è stato un problema nella comunicazione col database";

	public DatabaseException() {
		
		super(msgErrore);

	}

	public void mostraPopup() {
		PopUpManager.showPopup(msgErrore);
	}

}