package it.unipv.ingsw.exceptions;

public class PaymentException extends Exception{
	private static String msgErrore = "Errore durante il pagamento, riprova!";
	
	public PaymentException() {
		super(msgErrore);
	}
}