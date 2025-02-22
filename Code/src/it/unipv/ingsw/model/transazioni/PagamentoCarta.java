package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.exceptions.TransferException;

public class PagamentoCarta implements IPagamentoEsterno{ 

	@Override
	public void pagaCarta(double amount) throws PaymentException{
		 //FARE o LOGICA ESTERNA?
	}
	
	public boolean trasferisciSaldo(double amount) throws TransferException {
		Pagamento pp= new Pagamento(PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()));
        return pp.effettuaTrasferimento(amount);
	}
}	