package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;
import it.unipv.ingsw.model.utenze.Utente;

public class PagamentoPuntiApp implements IPagamento{ //aggiunto implements IPagamento per far funzionare composite, dovrei far capire che lo sta già implementando senza fare implements
		
		//pagamento con puntiApp
		public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{ 
			System.out.println("QUI PUNTIAPP");
			int temp;
			Saldo sal;
			Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
			if(m.getSaldo().getPuntiApp()<puntiApp) {
				throw new PaymentException();
			}
			else {
				temp=m.getSaldo().getPuntiApp();
				temp=temp-puntiApp;
				
				sal=new Saldo(m.getSaldo().getDenaro(),temp); 
				m.setSaldo(sal);
			}
		}
}
