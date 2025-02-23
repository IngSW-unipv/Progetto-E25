package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class PagamentoSaldoAdapter implements IPagamento{
	private PagamentoSaldo pagamentoSaldo;

	public PagamentoSaldoAdapter(PagamentoSaldo ps) {
		this.pagamentoSaldo = ps;
	}
	
	@Override
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		pagamentoSaldo.effettuaPagamento(amount, puntiApp);
	}
}
