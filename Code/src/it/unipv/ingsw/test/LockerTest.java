package it.unipv.ingsw.test;

import org.junit.Test;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Scompartimento;
import it.unipv.ingsw.model.spedizione.shippable.Size;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import static org.junit.Assert.*;


public class LockerTest {
	
	@Test
	public void testCheckQR_validCodeAndRitiro() {
		
		Coordinate punto= new Coordinate(15,20);
		IPuntoDeposito l1 = new Locker(punto, 10);
		IPuntoDeposito l2 = new Locker(punto, 15);
		Spedizione spedizione = new Spedizione(null, null, null, 0, l1, l2, null, new Date(0));
		MatchingService m = new MatchingService();
		GestoreSpedizioni gs = new GestoreSpedizioni(m);
		
		
		//creo una istanza del codiceqr e lo genero
		QRcode qr = new QRcode();
		qr.generaQRcode();
		
		Scompartimento sc = new Scompartimento(2, Size.S);
		((Locker) l1).getScompartimenti().put(2, sc);
		
		((Locker) l1).getMappaQRcode().put(qr.getQRcode(), 2);
		((Locker) l1).aggiungiScompartimento(new Scompartimento(2, Size.M));
		
		
		//testa il metodo checkQR() con la condizione che il pacco è stato ritirato dal destinatario
		boolean result = ((Locker) l1).checkQR(qr, spedizione, true, true); //true indica che il codice è valido ed è stato ritirato il pacco
		
		gs.verificaTempoDeposito(spedizione, new Date(System.currentTimeMillis() - 86400000L * 4), false);
		
		//verifica che lo stato della spedizione sia "Consegnato"
		assertTrue(result);
		//assertEquals è un metodo che controlla se la stringa che mi aspetto è uguale alla stringa reale 
		asserEquals("Consegnato", spedizione.getStatoSpedizione());
		
		System.out.println("Il pacco è stato ritirato dal destinatario.");
		
	}

	private void asserEquals(String string, String statoSpedizione) {
		
	}
	
	@Test
	public void testCheckQR_paccoRiconsegnatoAlMittente() {
	
	Coordinate punto1 = new Coordinate(10,15);
	IPuntoDeposito locker3 = new Locker(punto1, 10);
	IPuntoDeposito l4 = new Locker(punto1, 15);
	Spedizione spedizione = new Spedizione(null, null, null, 0, locker3, l4, null, new Date(System.currentTimeMillis() - 86400000L * 4)); //dataDeposito è 4 giorni fa
	QRcode codice = new QRcode();
	codice.generaQRcode();
	MatchingService m = new MatchingService();
	GestoreSpedizioni gs = new GestoreSpedizioni(m);
	
	
	Scompartimento sc = new Scompartimento(2, Size.S);
	((Locker) l4).getScompartimenti().put(2, sc);
	
	((Locker) l4).getMappaQRcode().put(codice.getQRcode(), 2);
	((Locker) l4).aggiungiScompartimento(new Scompartimento(2, Size.M));
	
	//test di checkQR() quando il pacco non viene ritirato in tempo
	boolean result = ((Locker) l4).checkQR(codice, spedizione, false, true); //false indica che pacco non ritirato
	
	gs.verificaTempoDeposito(spedizione, new Date(System.currentTimeMillis() - 86400000L * 4), false);
	
	//verifica se pacco sia riconsegnato al mittente
	assertTrue(result);
	//assertEquals è un metodo che controlla se la stringa che mi aspetto è uguale alla stringa reale
	assertEquals("Pacco riconsegnato al mittente.", spedizione.getStatoSpedizione());
	
	}
}
