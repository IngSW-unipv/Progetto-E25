package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class CompositePuntiSaldo extends CompositePagamentiStrategy{
	
	public CompositePuntiSaldo() {
		super.addStrategy(new PagamentoPuntiApp());
		super.addStrategy(new PagamentoSaldo());
	}
	
	//pagamento con puntiApp e saldo
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		double temp;
		Saldo sal;
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		//trasformo puntiApp in saldo
		amount-=(super.convertiPuntiInSaldo(m.getSaldo().getPuntiApp()));
		if(m.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			temp=m.getSaldo().getDenaro();
			temp=temp-puntiApp;
			sal=new Saldo(temp,0);
			m.setSaldo(sal);
		}
	}
}
