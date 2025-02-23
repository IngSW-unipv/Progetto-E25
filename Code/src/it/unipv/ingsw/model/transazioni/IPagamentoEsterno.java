package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.exceptions.TransferException;

public interface IPagamentoEsterno {

	public void pagaCarta(double amount) throws PaymentException;
	
	public boolean effettuaBonifico(double bonifico) throws TransferException; 

	public boolean trasferisciSaldo(double saldo) throws TransferException;
}
