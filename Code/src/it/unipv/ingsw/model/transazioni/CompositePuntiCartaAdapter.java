package it.unipv.ingsw.model.transazioni;

import it.unipv.ingsw.exceptions.PaymentException;

public class CompositePuntiCartaAdapter implements IPagamento {
		private CompositePuntiCarta pagamentoPuntiCarta;

		public CompositePuntiCartaAdapter(CompositePuntiCarta ppa) {
			this.pagamentoPuntiCarta = ppa;
		}
		
		@Override
		public void effettuaPagamento(double amount,int puntiApp) throws PaymentException{
			pagamentoPuntiCarta.effettuaPagamento(amount, puntiApp);
		}

}
