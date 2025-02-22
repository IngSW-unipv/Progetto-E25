package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class PagamentoPuntiAppAdapter implements IPagamento{
	private PagamentoPuntiApp pagamentoPuntiApp;

	public PagamentoPuntiAppAdapter(PagamentoPuntiApp ppa) {
		this.pagamentoPuntiApp = ppa;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		pagamentoPuntiApp.effettuaPagamento(amount, puntiApp);
	}
}