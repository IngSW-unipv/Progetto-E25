package it.unipv.ingsw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Scompartimento;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.shippable.Size;

public class TestRitiraPacco {
	
	@Test
	public void testRitiraPacco() {
		
		MatchingService m = new MatchingService();
		GestoreSpedizioni gs = new GestoreSpedizioni(m);
		Coordinate a = new Coordinate(4,5);	
		IPuntoDeposito l1 = new Locker(a,1);
		QRcode codice = new QRcode();
		codice.generaQRcode();
		Spedizione spedizione = new Spedizione(12345, null, l1, null);
		Scompartimento sc = new Scompartimento(2, Size.S);
		
		((Locker) l1).getScompartimenti().put(2, sc);
		
		((Locker) l1).getMappaQRcode().put(codice.getQRcode(), 2);
		
		//stampa per verificare il contenuto della mappa
		System.out.println("Contenuto della mappa mappaQRcode: " + ((Locker) l1).getMappaQRcode());
		
		//chiama il metodo ritiraPacco
		gs.ritiraPacco(codice, spedizione, true, (Locker) l1);
		
		//stampa per verifcare lo stato prima e dopo
		System.out.println("Stato spedizione dopo il ritiro: " + spedizione.getStatoSpedizione());
		
		//verifica che lo stato sped. sia stato aggiornato correttamente
		assertEquals("Consegnato", spedizione.getStatoSpedizione());
	}
	
}
