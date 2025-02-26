package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public interface IPagamento {

	public void effettuaPagamento(double amount, int puntiApp, Utente u) throws PaymentException;
}