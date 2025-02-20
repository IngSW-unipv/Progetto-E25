package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class CompositePuntiCarta extends CompositePagamenti {
	private PagamentoSaldo pagaConSaldo;
	
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		//pagamento con punti+carta
		//carta esterno
		double temp,rimanenteCarta;
		//PagamentoCarta pagaConCarta;
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		
		//trasformo puntiApp in saldo 
		m.getSaldo().setDenaro(super.convertiPuntiInSaldo(m.getSaldo().getDenaro(),m.getSaldo().getPuntiApp()));
		
		if(m.getSaldo().getDenaro()<amount) {
			rimanenteCarta=amount-m.getSaldo().getDenaro();
			//metodo pagaConCarta.effettuaPagamento(rimanenteCarta);
		}
		else {
			//ho abbastanza denaro: pagamento solo con i puntiApp trasformati in saldo
			pagaConSaldo.effettuaPagamentoConSaldo(amount, 0);
		}
	}
}
