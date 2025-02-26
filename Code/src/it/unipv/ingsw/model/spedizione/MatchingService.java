package it.unipv.ingsw.model.spedizione;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.unipv.ingsw.dao.LockerDAO;
import it.unipv.ingsw.dao.SpedizioneDAO;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.utenze.Destinatario;
import it.unipv.ingsw.model.utenze.Mittente;

public class MatchingService {
	
	private List<IPuntoDeposito> puntiDeposito;
	private List<Spedizione> spedizioniDisponibili;
	private LockerDAO lockerDAO;
	private SpedizioneDAO spedizioneDAO;
	private static final double TOLLERANZA = 3;
	
	
	public MatchingService() {
		//prendo tutti i locker con il dao
		lockerDAO = new LockerDAO();
		puntiDeposito = lockerDAO.selectAll();
		
		//dovrei prendere tutte le spedizioni disponibili con il dao
		spedizioneDAO = new SpedizioneDAO();
		spedizioniDisponibili = spedizioneDAO.selectAllInAttesa();
	}
	
	public void setPuntiDeposito(List<IPuntoDeposito> puntiDeposito) {
		this.puntiDeposito = puntiDeposito;
	}
	
	
	//metodo per suddividere l'itinerario totale in sottoItinerari (chiamato dal costruttore di spedizione)
	public List<Itinerario> itinerarioDivider(Itinerario itinerarioTOT) { //non dovrebbe prendere in ingresso la lista di puntideposito (solo per debug)
		
		List<Itinerario> sottoItinerari = new ArrayList<>();
		
		/*
		 *  trovo i puntiDeposito dentro l'itinerario
		 */
		List<IPuntoDeposito> puntiDepositoValidi = new ArrayList<>();
		
		Coordinate posizionePuntoDeposito; 
		double distanzaPuntoDeposito;
		
		for(IPuntoDeposito punto : puntiDeposito) {
			
			posizionePuntoDeposito = punto.getPosizione();	
			distanzaPuntoDeposito = posizionePuntoDeposito.distanza(itinerarioTOT.getInizio(), itinerarioTOT.getFine());
			
			//controllo se è nel segmento e se rientra nella tolleranza
			if(distanzaPuntoDeposito >= 0 && distanzaPuntoDeposito < TOLLERANZA)
				puntiDepositoValidi.add(punto);
		}
		
		System.out.println("locker validi:");
		for(IPuntoDeposito pd : puntiDepositoValidi) {
			System.out.println("("+(((Locker) pd).getPosizione().getLongitudine())+","+(((Locker) pd).getPosizione().getLatitudine())+")");
		}
		
		/*
		 * ordino i puntiDeposito dentro l'itinerario
		 */
		puntiDepositoValidi.sort(Comparator.comparingDouble(p -> p.getPosizione().distanza(itinerarioTOT.getInizio())));
		
		System.out.println("loker validi ordinati:");
		for(IPuntoDeposito pd : puntiDepositoValidi) {
			System.out.println("("+(((Locker) pd).getPosizione().getLongitudine())+","+(((Locker) pd).getPosizione().getLatitudine())+")");
		}
		
		/*
		 * compongo la lista dei sottoitinerari
		 */
		
		Coordinate inizio=null;
		for(IPuntoDeposito pto : puntiDepositoValidi) {

			if((pto.getPosizione().getLongitudine()==(itinerarioTOT.getInizio().getLongitudine()))&&(pto.getPosizione().getLatitudine()==(itinerarioTOT.getInizio().getLatitudine()))) {
				//System.out.println("dentro");
				inizio=itinerarioTOT.getInizio();
				continue;
			}
				
			sottoItinerari.add(new Itinerario(inizio, pto.getPosizione()));
			inizio = pto.getPosizione();
		}
		
		System.out.println("itinerario della spedizione diviso:");
		for(Itinerario sub : sottoItinerari) {
			System.out.println("inizio: ("+ sub.getInizio().getLongitudine()+","+sub.getInizio().getLatitudine()+") fine: ("+ sub.getFine().getLongitudine()+","+sub.getFine().getLatitudine()+")");
		}
		
		return sottoItinerari;
	}
	
	
	public List<Spedizione> trovaSpedizioniCompatibili(Itinerario itinerarioCarrier) { //spedizioni disponibili non è un parametro ma un attributo di matchingService
	    List<Spedizione> spedizioniCompatibili = new ArrayList<>();
	    
	    Coordinate inizio;
	    Coordinate fine;
	    //per ogni spedizione in spedizioni totali in attesa
	    for (Spedizione spedizione : spedizioniDisponibili) {
//	    	System.out.println("itinerario tot: "+spedizione.getItinerarioTot().getInizio().getLongitudine());
	    	spedizione.setItinerarioMancante(itinerarioDivider(spedizione.getItinerarioTot()));
	    	
	    	//il punto di inizio dell'itinerario corrente è il punto di inizio del primo sottoitinerario
			inizio = spedizione.getItinerarioMancante().get(0).getInizio();
			fine=null;
	    	//per ogni sottoitinerario in itinerarioMancante
	    	for(Itinerario sottoItinerario : spedizione.getItinerarioMancante()) {
	    		//controllo se il sottoitinerario è contenuto in quello del carrier 
	    		if (itinerarioCarrier.contiene(sottoItinerario)) {
	    			System.out.println("il sottoitinerario: ("+sottoItinerario.getInizio().getLongitudine()+","+sottoItinerario.getInizio().getLatitudine()+") -to- ("+sottoItinerario.getFine().getLongitudine()+","+sottoItinerario.getFine().getLatitudine()+")");
	    			//punto di inizio gestito sopra,
	    			//il punto di fine dell'itinerario corrente è il punto di fine dell'ultimo sottoitinerario (all'utlima iterazione raggiungo l'ultimo contenuto)
	    			fine = sottoItinerario.getFine();
		        }	
	    	}
	    	
	    	spedizione.setItinerarioCorrente(new Itinerario(inizio,fine));
	    	spedizioniCompatibili.add(spedizione);
	        
	    }
	    
	    return spedizioniCompatibili;
	}
	
}
