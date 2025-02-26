package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public class PagamentoPuntiAppAdapter implements IPagamento{
	private PagamentoPuntiApp pagamentoPuntiApp;

	public PagamentoPuntiAppAdapter(PagamentoPuntiApp ppa) {
		this.pagamentoPuntiApp = ppa;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{
		pagamentoPuntiApp.effettuaPagamento(amount, puntiApp,utente);
	}
}