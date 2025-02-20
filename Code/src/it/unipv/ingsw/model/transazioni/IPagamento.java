package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public interface IPagamento {

	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException;
}