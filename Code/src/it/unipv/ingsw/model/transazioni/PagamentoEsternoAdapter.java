package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public class PagamentoEsternoAdapter implements IPagamento {
	private PagamentoCarta pagamentoCarta;
	
	
	public PagamentoEsternoAdapter(PagamentoCarta pagamentoCarta) {
		this.pagamentoCarta = pagamentoCarta;
	}

	@Override
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{
		pagamentoCarta.pagaCarta(amount);
		
	}
}

