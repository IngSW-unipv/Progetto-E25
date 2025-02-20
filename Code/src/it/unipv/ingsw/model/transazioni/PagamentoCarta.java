package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class PagamentoCarta implements IPagamentoEsterno{
	
	public void pagaCarta(double amount,int puntiApp) throws PaymentException{
		//FARE o LOGICA ESTERNA?
	}
	
	public void trasferisciSaldo() {}
		
}
