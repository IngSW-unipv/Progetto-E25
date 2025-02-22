package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class CompositePuntiSaldoAdapter implements IPagamento{
	private CompositePuntiSaldo pagamentoPuntiSaldo;

	public CompositePuntiSaldoAdapter(CompositePuntiSaldo ppa) {
		this.pagamentoPuntiSaldo = ppa;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		pagamentoPuntiSaldo.effettuaPagamento(amount, puntiApp);
	}
}
