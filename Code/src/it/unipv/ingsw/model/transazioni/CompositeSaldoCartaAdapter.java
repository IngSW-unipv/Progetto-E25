package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public class CompositeSaldoCartaAdapter implements IPagamento{
	private CompositeSaldoCarta pagamentoSaldoCarta;

	public CompositeSaldoCartaAdapter(CompositeSaldoCarta ppa) {
		this.pagamentoSaldoCarta = ppa;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{
		pagamentoSaldoCarta.effettuaPagamento(amount, puntiApp, utente);
	}

}
