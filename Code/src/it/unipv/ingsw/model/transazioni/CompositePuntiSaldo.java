package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;
import it.unipv.ingsw.model.utenze.Utente;

public class CompositePuntiSaldo extends CompositePagamentiStrategy{
	
	public CompositePuntiSaldo() {
		super.addStrategy(new PagamentoPuntiApp());
		super.addStrategy(new PagamentoSaldo());
	}
	
	//pagamento con puntiApp e saldo
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{
		double temp;
		Saldo sal;
		System.out.println("QUI PUNTI-SALDO");
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		//trasformo puntiApp in saldo
		amount-=(super.convertiPuntiInSaldo(utente.getSaldo().getPuntiApp()));
		if(utente.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			temp=utente.getSaldo().getDenaro();
			temp=temp-puntiApp;
			sal=new Saldo(temp,0);
			utente.setSaldo(sal);
		}
	}
}
