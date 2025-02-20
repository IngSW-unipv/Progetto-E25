package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class CompositePuntiSaldo extends CompositePagamenti{
	
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		//pagamento con puntiApp utilizzando classe Saldo e Utente
		double temp;
		Saldo sal;
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		//trasformo puntiApp in saldo
		m.getSaldo().setDenaro(super.convertiPuntiInSaldo(m.getSaldo().getDenaro(),m.getSaldo().getPuntiApp()));
		if(m.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			puntiApp=0;
			temp=m.getSaldo().getDenaro();
			temp=temp-puntiApp;
			sal=new Saldo(temp,0);
			m.setSaldo(sal);
			//m.setSaldo(m.getSaldo().getDenaro()-amount);
		}
	}
}
