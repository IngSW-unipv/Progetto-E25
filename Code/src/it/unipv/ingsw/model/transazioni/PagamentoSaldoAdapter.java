package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public class PagamentoSaldoAdapter implements IPagamento{
	private PagamentoSaldo pagamentoSaldo;

	public PagamentoSaldoAdapter(PagamentoSaldo ps) {
		this.pagamentoSaldo = ps;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{
		pagamentoSaldo.effettuaPagamento(amount, puntiApp, utente);
	}
}
