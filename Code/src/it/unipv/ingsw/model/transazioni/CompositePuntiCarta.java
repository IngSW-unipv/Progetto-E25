package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;
import it.unipv.ingsw.model.utenze.Utente;

public class CompositePuntiCarta extends CompositePagamentiStrategy{
	private PagamentoCarta pagaConCarta;
	private PagamentoPuntiApp pagaConPuntiApp;
	
	public CompositePuntiCarta() {
		super.addStrategy(new PagamentoPuntiApp());
		super.addStrategy(new PagamentoCarta());
	}
	
	//pagamento con punti e carta
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{ 
		double temp=amount;
		Saldo sal;
		System.out.println("PAGAMENTO PUNTI+CARTA");
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		
		//trasformo puntiApp in saldo 
		temp-=(super.convertiPuntiInSaldo(utente.getSaldo().getPuntiApp())); //utilizzo una variabile temp per vedere se ho punti sufficienti per pagare
		if(temp<0) { //Posso pagare con puntiApp, non c'è bisogno di carta!
			pagaConPuntiApp.effettuaPagamento(amount, puntiApp, utente); ;
		}
		else {
			//ho abbastanza denaro: pagamento solo con i puntiApp trasformati in saldo
			pagaConCarta.pagaCarta(temp); ////tot-punti (>0)
			sal=new Saldo(utente.getSaldo().getDenaro(),0); //ho utilizzato tutti i puntiApp
			utente.setSaldo(sal);
		}
	}
}
