package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;
import it.unipv.ingsw.model.utenze.Utente;

public class PagamentoSaldo implements IPagamento{ //aggiunto implements IPagamento per far funzionare composite

	//pagamento con saldo
	public void effettuaPagamento(double amount,int puntiApp,Utente u) throws PaymentException{
		System.out.println("PAGAMENTO SALDO");
		double temp;
		Saldo sal;
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		if(u.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			temp=u.getSaldo().getDenaro();
			temp=temp-amount;
			sal=new Saldo(temp,u.getSaldo().getPuntiApp());
			u.setSaldo(sal);
		}
	}
}

