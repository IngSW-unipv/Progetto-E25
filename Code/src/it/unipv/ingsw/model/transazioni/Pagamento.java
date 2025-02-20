package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class Pagamento {

    private IPagamento metodoPagamento;
    
    public Pagamento(IPagamento metodoPagamento) {
    	this.metodoPagamento=metodoPagamento;
    }

    public boolean iniziaPagamento(double amount,int puntiApp) throws PaymentException{
    	boolean result=false;
    	if(amount<=0||metodoPagamento==null||puntiApp<=0) {
    		throw new PaymentException();
    	}
    	else {
    		metodoPagamento.effettuaPagamento(amount, puntiApp); //lancia il metodo di IPagamento
        	result=true;
    	}
        return result;
    }
}