package it.unipv.ingsw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Test;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Scompartimento;
import it.unipv.ingsw.model.spedizione.shippable.Size;

public class SpedizioneTest {
	
	@Test
	public void testAggiornamentoStatoSpedizione() {
		Coordinate punto= new Coordinate(15,20);
		IPuntoDeposito locker1 = new Locker(punto, 10);
		IPuntoDeposito locker2 = new Locker(punto, 15);
		Spedizione spedizione = new Spedizione(null, null, null, 0, locker1, locker2, null, new Date(0));
		GestoreSpedizioni gestore = new GestoreSpedizioni(null);
		
		//istanza il qr e lo genera
		QRcode codice = new QRcode();
		codice.generaQRcode();
		
		//aggiunge lo scompartimento di ID=1 alla mappa
		((Locker) locker1).aggiungiScompartimento(new Scompartimento(1, Size.M));
		
		//testa il metodo checkQR() con la condizione che il pacco è stato ritirato dal destinatario
		boolean result = ((Locker) locker1).checkQR(codice, spedizione, true, false); //true indica che il codice è valido ed è stato ritirato il pacco
		//verifica che lo stato della spedizione sia "Consegnato"
		assertTrue(result);
		//assertEquals è un metodo che controlla se la stringa che mi aspetto è uguale alla stringa reale 
		asserEquals("Consegnato", spedizione.getStatoSpedizione());
		
		//verifico che inizialmente lo stato è in attesa
		gestore.aggiornaStatoSpedizione(spedizione, true); // pacco depositato
		assertEquals("In attesa", spedizione.getStatoSpedizione()); //dovrebbe essere preso in carico
		
		//aggiorna lo stato a "Consegnato"
		gestore.aggiornaStatoSpedizione(spedizione, false);
		assertEquals("Consegnato", spedizione.getStatoSpedizione());
		
	}

	private void asserEquals(String string, String statoSpedizione) {
		
	}
	

}
