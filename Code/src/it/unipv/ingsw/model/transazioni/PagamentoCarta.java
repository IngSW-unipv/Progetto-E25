package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.exceptions.TransferException;

public class PagamentoCarta implements IPagamentoEsterno{ 

	public void pagaCarta(double amount) throws PaymentException{
		 //LOGICA ESTERNA
	}

	public boolean effettuaBonifico(double bonifico) throws TransferException {
		//LOGICA ESTERNA PER IL BONIFICO
		return true;
	}
	
	public boolean trasferisciSaldo(double saldo) throws TransferException {
		//LOGICA ESTERNA PER IL TRASFERIMENTO DEL SALDO
		return true;
	}
}	