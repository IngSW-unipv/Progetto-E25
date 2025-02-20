package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public interface IPagamentoEsterno {

	public void pagaCarta(double amount,int puntiCarta) throws PaymentException;
	
	public void trasferisciSaldo(); //da implementare
}
