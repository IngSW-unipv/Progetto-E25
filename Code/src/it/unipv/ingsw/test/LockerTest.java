package it.unipv.ingsw.test;

import org.junit.Test;

import it.unipv.ingsw.model.spedizione.Coordinate;
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
		IPuntoDeposito locker1 = new Locker(punto, 10);
		IPuntoDeposito locker2 = new Locker(punto, 15);
		Spedizione spedizione = new Spedizione(null, null, null, 0, locker1, locker2, null, new Date(0));
		
		//creo una istanza del codiceqr e lo genero
		QRcode qr = new QRcode();
		qr.generaQRcode();
		
		//aggiunge lo scompartimento di ID=1 alla mappa
		((Locker) locker1).aggiungiScompartimento(new Scompartimento(1, Size.L));
		
		//testa il metodo checkQR() con la condizione che il pacco è stato ritirato dal destinatario
		boolean result = ((Locker) locker1).checkQR(qr, spedizione, true, true); //true indica che il codice è valido ed è stato ritirato il pacco
		//verifica che lo stato della spedizione sia "Consegnato"
		assertTrue(result);
		//assertEquals è un metodo che controlla se la stringa che mi aspetto è uguale alla stringa reale 
		asserEquals("Consegnato", spedizione.getStatoSpedizione());
	}

	private void asserEquals(String string, String statoSpedizione) {
		
	}
	
	@Test
	public void testCheckQR_paccoRiconsegnatoAlMittente() {
	
	Coordinate punto1 = new Coordinate(10,15);
	IPuntoDeposito locker3 = new Locker(punto1, 10);
	IPuntoDeposito locker4 = new Locker(punto1, 15);
	Spedizione spedizione = new Spedizione(null, null, null, 0, locker3, locker4, null, new Date(System.currentTimeMillis() - 86400000L * 4)); //dataDeposito è 4 giorni fa
	QRcode codice = new QRcode();
	codice.generaQRcode();

	((Locker) locker4).aggiungiScompartimento(new Scompartimento(2, Size.M));
	
	//test di checkQR() quando il pacco non viene ritirato in tempo
	boolean result = ((Locker) locker4).checkQR(codice, spedizione, false, true); //false indica che pacco non ritirato
	
	//verifica se pacco sia riconsegnato al mittente
	assertTrue(result);
	//assertEquals è un metodo che controlla se la stringa che mi aspetto è uguale alla stringa reale
	assertEquals("Pacco riconsegnato al mittente.", spedizione.getStatoSpedizione());
	
	}
}
