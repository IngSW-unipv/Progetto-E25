package it.unipv.ingsw.model.transazioni;

import java.util.LinkedList;
import java.util.List;
import it.unipv.ingsw.exceptions.PaymentException;

public abstract class CompositePagamentiStrategy implements IPagamento{

	private List<IPagamento> pagamentoStrategies;

	protected CompositePagamentiStrategy() {
		pagamentoStrategies=new LinkedList<IPagamento>();
	}
	
	protected void addStrategy(IPagamento s) {
		pagamentoStrategies.add(s);
	}
	
	protected void addStrategy(IPagamentoEsterno s) { //non sono sicuro
		pagamentoStrategies.add((IPagamento) s);
	}
	
	protected List<IPagamento> getStrategies(){
		return pagamentoStrategies;
	}
	
	public static double convertiPuntiInSaldo(int punti) {
	    // Ogni 100 punti corrispondono a 1 euro
	    double conversioneSoldi = punti / 100.0;
	    double amount=0;
	    // Aggiungi l'equivalente in euro alla variabile amount
	    amount += conversioneSoldi;
	    
	    return amount;
	}
	
	@Override
	public abstract void effettuaPagamento(double amount,int puntiApp) throws PaymentException;
	
}	
