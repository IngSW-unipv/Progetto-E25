package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class PagamentoPuntiApp { //ADAPTER DI PTAPP E SALDO
	
		public void effettuaPagamentoConPuntiApp(double amount,int puntiApp) throws PaymentException{
			//pagamento con puntiApp utilizzando classe Saldo e Utente
			int temp;
			Saldo sal;
			Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
			if(m.getSaldo().getPuntiApp()<puntiApp) {
				throw new PaymentException();
			}
			else {
				temp=m.getSaldo().getPuntiApp();
				temp=temp-puntiApp;
				sal=new Saldo(0,temp);
				m.setSaldo(sal);
				//m.setSaldo(m.getSaldo().getDenaro()-amount);
			}
		}
}
