package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class PagamentoCarta implements IPagamentoEsterno{
	private double amount; 
	private int puntiApp;  

	@Override
	public void pagaCarta(double amount) throws PaymentException{
		 //FARE o LOGICA ESTERNA?
	}
	
	public boolean trasferisciSaldo() throws PaymentException {
		Pagamento pp= new Pagamento(PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()));
        return pp.provaPagamento(amount,puntiApp);
	}
}	