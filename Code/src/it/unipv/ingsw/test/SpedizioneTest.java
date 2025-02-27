package it.unipv.ingsw.test;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertTrue;

//import java.sql.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Scompartimento;
//import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.spedizione.shippable.Size;

public class SpedizioneTest {
	
//	private Locker locker;
	private QRcode codice;
//	private Spedizione spedizione;
	
	@BeforeEach
	public void setUp() {
		Coordinate a = new Coordinate(4,5);
//		Coordinate b = new Coordinate(7,-2);
		IPuntoDeposito l1 = new Locker(a, 1);
//		IPuntoDeposito l2 = new Locker(b,2);
		
		l1 = new Locker(a, 1);
		codice = new QRcode();
		codice.generaQRcode();
		
		//creo la spedizione
//		Spedizione spedizione = new Spedizione(12345, null, l1, l2);
		
		Scompartimento sc = new Scompartimento(2, Size.M);
		((Locker) l1).getScompartimenti().put(2, sc);
		
		//inserisco la spedizione nella mappaQRcode
		((Locker) l1).getMappaQRcode().put(codice.getQRcode(), 2);
	}
	
	
	@Test
	public void testCheckQRValido() {
		
		MatchingService m = new MatchingService();
		GestoreSpedizioni gs = new GestoreSpedizioni(m);
		
		Coordinate a = new Coordinate(4,5);
		Coordinate b = new Coordinate(7,-2);
		IPuntoDeposito l1 = new Locker(a, 1);
		IPuntoDeposito l2 = new Locker(b,2);
		
		//creo la spedizione
		Spedizione spedizione = new Spedizione(12345, null, l1, l2);
		
		//istanzo il QR e lo genero
		QRcode codice = new QRcode();
		codice.generaQRcode();
		
		Scompartimento sc = new Scompartimento(2, Size.M);
		((Locker) l1).getScompartimenti().put(2, sc);
		((Locker) l1).getMappaQRcode().put(codice.getQRcode(), 2);
		
//		boolean isRitiro = true;
//		boolean isMittenteDeposita = false;
		
		gs.depositaPacco(codice, spedizione, true, (Locker) l1);

		//boolean result = l1.checkQR(codice, spedizione, isRitiro, isMittenteDeposita);
		
		//assertTrue(result, "Codice QR valido.");
		
		assertEquals("In attesa.", spedizione.getStatoSpedizione());
	}
	
}
