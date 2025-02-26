package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public class CompositePuntiSaldoAdapter implements IPagamento{
	private CompositePuntiSaldo pagamentoPuntiSaldo;

	public CompositePuntiSaldoAdapter(CompositePuntiSaldo ppa) {
		this.pagamentoPuntiSaldo = ppa;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{
		pagamentoPuntiSaldo.effettuaPagamento(amount, puntiApp,utente);
	}
}
