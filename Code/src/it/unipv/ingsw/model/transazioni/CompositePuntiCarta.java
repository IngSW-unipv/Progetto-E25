package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class CompositePuntiCarta extends CompositePagamentiStrategy{
	private PagamentoCarta pagaConCarta;
	private PagamentoPuntiApp pagaConPuntiApp;
	
	public CompositePuntiCarta() {
		super.addStrategy(new PagamentoPuntiApp());
		super.addStrategy(new PagamentoCarta());
	}
	
	//pagamento con punti e carta
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{ 
		double temp=amount;
		Saldo sal;
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		
		//trasformo puntiApp in saldo 
		temp-=(super.convertiPuntiInSaldo(m.getSaldo().getPuntiApp())); //utilizzo una variabile temp per vedere se ho punti sufficienti per pagare
		if(temp<0) { //Posso pagare con puntiApp, non c'è bisogno di carta!
			pagaConPuntiApp.effettuaPagamento(amount, puntiApp); ;
		}
		else {
			//ho abbastanza denaro: pagamento solo con i puntiApp trasformati in saldo
			pagaConCarta.pagaCarta(temp); ////tot-punti (>0)
			sal=new Saldo(m.getSaldo().getDenaro(),0); //ho utilizzato tutti i puntiApp
			m.setSaldo(sal);
		}
	}
}
