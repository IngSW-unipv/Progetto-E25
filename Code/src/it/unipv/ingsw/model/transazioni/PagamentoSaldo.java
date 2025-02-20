package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class PagamentoSaldo{

	public void effettuaPagamentoConSaldo(double amount,int puntiApp) throws PaymentException{
		//pagamento con saldo utilizzando classe Saldo e Mittente
		double temp;
		Saldo sal;
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		if(m.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			temp=m.getSaldo().getDenaro();
			temp=temp-amount;
			sal=new Saldo(temp,0);
			m.setSaldo(sal);
			//m.setSaldo(m.getSaldo().getDenaro()-amount);
		}
	}
}
