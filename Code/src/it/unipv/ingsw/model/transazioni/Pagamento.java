package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Utente;

public class Pagamento {

    private IPagamento metodoPagamento;
    
    public Pagamento(IPagamento metodoPagamento) {
    	this.metodoPagamento=metodoPagamento;
    }

    public boolean provaPagamento(double amount,int puntiApp,IPagamento metodoPagamento,Utente utente) throws PaymentException{
    	boolean result=false;
    	if(amount<0 || puntiApp<0|| metodoPagamento==null) {
    		throw new PaymentException();
    	}
    	else {
    		metodoPagamento.effettuaPagamento(amount, puntiApp, utente); //lancia il metodo di IPagamento
        	result=true;
    	}
        return result;
    } 
}