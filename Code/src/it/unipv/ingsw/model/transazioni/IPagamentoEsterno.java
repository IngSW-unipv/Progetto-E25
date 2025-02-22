package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public interface IPagamentoEsterno {

	public void pagaCarta(double amount) throws PaymentException;
	
	public boolean trasferisciSaldo() throws PaymentException; //da implementare
}
