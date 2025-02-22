package it.unipv.ingsw.exceptions;

public class TransferException extends Exception{
	private static String msgErrore = "Errore durante il trasferimento, riprova!";
	
	public TransferException() {
		super(msgErrore);
	}
}
