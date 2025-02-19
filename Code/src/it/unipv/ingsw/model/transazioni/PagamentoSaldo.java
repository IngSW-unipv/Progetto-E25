package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Utente;

public class PagamentoSaldo implements IPagamento {

	public void effettuaPagamento(double amount) throws PaymentException{
		//pagamento con saldo utilizzando classe Saldo e Mittente
		Mittente m=(Mittente) Singleton.getInstance();
		if(m.getSaldo().getDenaro()<amount) {
			throw new PaymentException();
		}
		else {
			m.setSaldo(m.getSaldo().getDenaro()-amount);
		}
	}
}
