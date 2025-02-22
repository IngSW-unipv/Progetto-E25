package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.exceptions.TransferException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class Pagamento {

    private IPagamento metodoPagamento;
    
    public Pagamento(IPagamento metodoPagamento) {
    	this.metodoPagamento=metodoPagamento;
    }

    public boolean provaPagamento(double amount,int puntiApp) throws PaymentException{
    	boolean result=false;
    	if(amount<0 || puntiApp<0|| metodoPagamento==null) {
    		throw new PaymentException();
    	}
    	else {
    		metodoPagamento.effettuaPagamento(amount, puntiApp); //lancia il metodo di IPagamento
        	result=true;
    	}
        return result;
    }
    
    public boolean effettuaTrasferimento(double bonifico) throws TransferException{
    	boolean result=false;
    	Saldo sal;
    	if(bonifico<0) {
    		throw new TransferException();
    	}
    	else { //effettua bonifico
    		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
			sal=new Saldo(bonifico+m.getSaldo().getDenaro(),m.getSaldo().getPuntiApp()); //aggiungo al saldo del mittente il bonifico
			m.setSaldo(sal);
        	result=true;
    	}
        return result;
    }
}