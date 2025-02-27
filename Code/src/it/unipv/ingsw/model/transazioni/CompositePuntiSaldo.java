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
		double temp,sconto,prezzoFinale,prezzoScontato;
		Saldo sal;
		System.out.println("PAGAMENTO PUNTI+SALDO");
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		//trasformo puntiApp in saldo
		sconto=super.convertiPuntiInSaldo(utente.getSaldo().getPuntiApp());
		prezzoScontato = amount-sconto;
		//mostrare a video prezzoScontato
		
		if(utente.getSaldo().getDenaro()<prezzoScontato) 
			throw new PaymentException(); //anche popup
		else {
			prezzoFinale = amount + sconto;
			temp=utente.getSaldo().getDenaro()-prezzoFinale;
			sal=new Saldo(temp,0);
			utente.setSaldo(sal);
		}
	}
}
