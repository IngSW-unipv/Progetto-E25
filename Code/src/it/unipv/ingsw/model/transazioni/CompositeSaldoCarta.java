package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;
import it.unipv.ingsw.model.utenze.Utente;

public class CompositeSaldoCarta extends CompositePagamentiStrategy {
	private PagamentoSaldo pagaConSaldo;
	private PagamentoCarta pagaConCarta;
	
	public CompositeSaldoCarta() {
		super.addStrategy(new PagamentoSaldo()); //pagamento saldo deve implementare IPagamento
		super.addStrategy(new PagamentoCarta()); //pagamento carta deve implementare IPagamentoEsterno che a sua volta implementa IPagamento
	}
	
	//pagamento con saldo e carta
	public void effettuaPagamento(double amount,int puntiApp,Utente utente) throws PaymentException{ 
		double temp=amount;
		Saldo sal;
		System.out.println("PAGAMENTO SALDO+CARTA");
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		temp-=utente.getSaldo().getDenaro(); //utilizzo una variabile temp per vedere se ho saldo sufficiente per pagare
		if(temp<=0) { //Posso pagare con saldo, non c'è bisogno di carta!
			pagaConSaldo.effettuaPagamento(amount, puntiApp, utente); 
		}
		else {
			//non ho abbastanza denaro sul saldo: pagamento con carta sottraendo il saldo--> composite
			pagaConCarta.pagaCarta(amount-utente.getSaldo().getDenaro()); //tot-saldo (o temp che è >0)
			sal=new Saldo(0,utente.getSaldo().getPuntiApp()); //ho utilizzato tutto il saldo
			utente.setSaldo(sal);
		}
	}		
}
