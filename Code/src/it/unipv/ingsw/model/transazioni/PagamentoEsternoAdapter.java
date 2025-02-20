package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class PagamentoEsternoAdapter implements IPagamento {
	private PagamentoCarta pagamentoCarta;
	
	
	public PagamentoEsternoAdapter(PagamentoCarta pagamentoCarta) {
		this.pagamentoCarta = pagamentoCarta;
	}

	@Override
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		pagamentoCarta.pagaCarta(amount, puntiApp);
	}
}

