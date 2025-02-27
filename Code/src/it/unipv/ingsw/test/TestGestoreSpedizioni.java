package it.unipv.ingsw.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.Itinerario;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.utenze.Carrier;

public class TestGestoreSpedizioni {
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAccettaPresaInCarico() {
		 Itinerario itinerarioCarrier = new Itinerario(new Coordinate(1, 1), new Coordinate(5, 1));
		 Carrier carrier = new Carrier("test@mail.com", "password", "Mario", "Rossi", "1234567890",
                 "Via Roma 1", "01/01/1990", "documento.jpg", itinerarioCarrier);
		 
		 QRcode qrcode = new QRcode();
		 qrcode.generaQRcode();
		 
		 //creazione di spedizioni compatibili
		 List<Spedizione> spedizioni = new ArrayList<>();
		 Itinerario itinerarioSpedizione1 = new Itinerario(new Coordinate(2, 1), new Coordinate(4, 1));
	     Itinerario itinerarioSpedizioneTot1 = new Itinerario(new Coordinate(2, 1), new Coordinate(6, 1));
	     Spedizione spedizione1 = new Spedizione("SPED001", itinerarioSpedizione1, itinerarioSpedizioneTot1, new QRcode());
	     spedizioni.add(spedizione1);
	     
	  // Creazione dell'istanza di GestoreSpedizioni
	     MatchingService m = new MatchingService();
	     GestoreSpedizioni gs = new GestoreSpedizioni(m);
	
	     Itinerario itinerarioSpedizione2 = new Itinerario(new Coordinate(1, 1), new Coordinate(5, 1));
	     Itinerario itinerarioSpedizioneTot2 = new Itinerario(new Coordinate(1, 1), new Coordinate(7, 1));
	     Spedizione spedizione2 = new Spedizione("SPED002", itinerarioSpedizione2, itinerarioSpedizioneTot2, new QRcode());
	     spedizioni.add(spedizione2);
	
	        gs.accettaPresaInCarico(carrier, spedizioni);
	        
	     // **VERIFICHE**
	        // 1. Il carrier ha ricevuto le spedizioni
	        assertEquals(2, carrier.getSpedizioniAssegnate().size());
	        
	        // 2. Le spedizioni hanno lo stato aggiornato
	        for (Spedizione s : spedizioni) {
	            assertEquals("IN_TRANSITO", s.getStatoSpedizione());
	        }
	        
//	        // 3. Il compenso del carrier è stato aggiornato
//	        double distanza1 = spedizione1.getItinerarioCorrente().getInizio().distanza(gs.getItinerarioCorrente().getFine());
//	        double distanza2 = spedizione2.getItinerarioCorrente().getInizio().distanza(gs.getItinerarioCorrente().getFine());
//	        double compensoAtteso = (distanza1 + distanza2) * gs.getTassocompensosoldi();
//	        int puntiAttesi = (int) ((distanza1 + distanza2) * gs.getTassocompensopunti());
//
//	        assertEquals(compensoAtteso, carrier.getCompenso().getDenaro());
//	        assertEquals(puntiAttesi, carrier.getCompenso().getPuntiApp());
//	        
//	     // 4. Verificare che l'itinerario della spedizione sia stato aggiornato
//	        for (Spedizione s : spedizioni) {
//	            assertEquals(gs.getItinerarioCorrente().getFine(), s.getItinerarioTot().getInizio());
	        
//	        }
	}
	
}
