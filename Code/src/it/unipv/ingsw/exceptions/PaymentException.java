package it.unipv.ingsw.exceptions;

public class PaymentException extends Exception{
	
	public PaymentException() {
		super("Errore durante il pagamento, riprova!");
	}
}