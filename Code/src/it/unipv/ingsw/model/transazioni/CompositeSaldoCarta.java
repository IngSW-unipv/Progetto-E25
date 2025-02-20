package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Saldo;

public class CompositeSaldoCarta extends CompositePagamenti {
	private PagamentoSaldo pagaConSaldo;
	
	public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
		//pagamento con saldo+carta
		//carta esterno
		double temp,rimanenteCarta;
		//PagamentoCarta pagaConCarta;
		
		Mittente m=(Mittente) Singleton.getInstance().getUtenteLoggato();
		if(m.getSaldo().getDenaro()<amount) {
			rimanenteCarta=amount-m.getSaldo().getDenaro();
			//metodo pagaConCarta.effettuaPagamento(rimanenteCarta);
		}
		else {
			//ho abbastanza denaro: pagamento solo con saldo
			pagaConSaldo.effettuaPagamentoConSaldo(amount, 0);
		}
	}
}
