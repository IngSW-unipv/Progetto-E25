package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class CompositePagamenti implements IPagamento {

	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException {
		//SISTEMARE
		throw new PaymentException();
	}
	
	public static double convertiPuntiInSaldo(double amount,int punti) {
	    // Ogni 100 punti corrispondono a 1 euro
	    double conversioneSoldi = punti / 100.0;
	    
	    // Aggiungi l'equivalente in euro alla variabile amount
	    amount += conversioneSoldi;
	    
	    return amount;
	}
}	
