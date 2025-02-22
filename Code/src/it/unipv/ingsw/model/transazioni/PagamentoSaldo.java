package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class PagamentoSaldo implements IPagamento{ //aggiunto implements IPagamento per far funzionare composite

	//pagamento con saldo
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		double temp;
		Saldo sal;
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		if(m.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			temp=m.getSaldo().getDenaro();
			temp=temp-amount;
			sal=new Saldo(temp,m.getSaldo().getPuntiApp());
			m.setSaldo(sal);
		}
	}
}
