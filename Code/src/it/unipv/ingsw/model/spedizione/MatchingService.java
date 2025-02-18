package it.unipv.ingsw.model.spedizione;

import java.util.ArrayList;
import java.util.List;

public class MatchingService {

	public List<Spedizione> trovaSpedizioniCompatibili(Itinerario itinerarioCarrier, List<Spedizione> spedizioniDisponibili) {
	    List<Spedizione> spedizioniCompatibili = new ArrayList<>();
	    
	    for (Spedizione spedizione : spedizioniDisponibili) {
	        if (itinerarioCarrier.contiene(spedizione.getItinerario())) {
	            spedizioniCompatibili.add(spedizione);
	        }
	    }
	    
	    return spedizioniCompatibili;
	}


}
