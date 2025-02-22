package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class CompositeSaldoCartaAdapter implements IPagamento{
	private CompositeSaldoCarta pagamentoSaldoCarta;

	public CompositeSaldoCartaAdapter(CompositeSaldoCarta ppa) {
		this.pagamentoSaldoCarta = ppa;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		pagamentoSaldoCarta.effettuaPagamento(amount, puntiApp);
	}

}
