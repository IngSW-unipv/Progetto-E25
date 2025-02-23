package it.unipv.ingsw.model.spedizione;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.utenze.Destinatario;
import it.unipv.ingsw.model.utenze.Mittente;

public class MatchingService {
	
	private List<IPuntoDeposito> puntiDeposito;
	private static final double TOLLERANZA = 5;
	
	
	public MatchingService() {
		puntiDeposito = new ArrayList<>();
		//dovrei prendere tutti i locker con il dao
		
		//dovrei prendere tutte le spedizioni disponibili con il dao
		
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
			
			//se è il primo locker 
			if(pto.getPosizione().equals(itinerarioTOT.getInizio())) {
				inizio=itinerarioTOT.getInizio();
				continue;
			}
				
			
			sottoItinerari.add(new Itinerario(inizio, pto.getPosizione()));
			inizio = pto.getPosizione();
			
		}
		
		System.out.println("sottoItinerari:");
		for(Itinerario sub : sottoItinerari) {
			System.out.println("inizio: ("+ sub.getInizio().getLongitudine()+","+sub.getInizio().getLatitudine()+") fine: ("+ sub.getFine().getLongitudine()+","+sub.getFine().getLatitudine()+")");
		}
		
		return sottoItinerari;
	}
	
	
	public List<Spedizione> trovaSpedizioniCompatibili(Itinerario itinerarioCarrier, List<Spedizione> spedizioniDisponibili) { //spedizioni disponibili non è un parametro ma un attributo di matchingService
	    List<Spedizione> spedizioniCompatibili = new ArrayList<>();
	    
	    Coordinate inizio;
	    Coordinate fine;
	    //per ogni spedizione in spedizioni totali in attesa
	    for (Spedizione spedizione : spedizioniDisponibili) {
	    	//il punto di inizio dell'itinerario corrente è il punto di inizio del primo sottoitinerario
			inizio = spedizione.getItinerarioMancante().get(0).getInizio();
			fine=null;
	    	//per ogni sottoitinerario in itinerarioMancante
	    	for(Itinerario sottoItinerario : spedizione.getItinerarioMancante()) {
	    		//controllo se il sottoitinerario è contenuto in quello del carrier 
	    		if (itinerarioCarrier.contiene(sottoItinerario)) {   				
	    			//punto di inizio gestito sopra,
	    			//il punto di fine dell'itinerario corrente è il punto di fine dell'ultimo sottoitinerario (all'utlima iterazione raggiungo l'ultimo contenuto)
	    			fine = sottoItinerario.getInizio(); //era fine!!!
		        }	
	    	}
	    	
	    	spedizione.setItinerarioCorrente(new Itinerario(inizio,fine));
//	    	System.out.println("setItinerarioCorrente:\n - inizio: "+inizio.getClass());
	    	spedizioniCompatibili.add(spedizione);
	        
	    }
	    
	    return spedizioniCompatibili;
	}
	
	public static void main(String[] args) {
/*		
		Coordinate a = new Coordinate(1,1);
		Coordinate b = new Coordinate(12,1);
		Coordinate c = new Coordinate(2,3);
		Coordinate d = new Coordinate(4,7);
		Coordinate e = new Coordinate(6,3);
		Coordinate f = new Coordinate(9,2);
		Coordinate g = new Coordinate(11,8);
		Coordinate h = new Coordinate(13,2);
		
		IPuntoDeposito l1 = new Locker(a,1);
		IPuntoDeposito l2 = new Locker(b,2);
		IPuntoDeposito l3 = new Locker(c,3);
		IPuntoDeposito l4 = new Locker(d,4);
		IPuntoDeposito l5 = new Locker(e,5);
		IPuntoDeposito l6 = new Locker(f,6);
		IPuntoDeposito l7 = new Locker(g,7);
		IPuntoDeposito l8 = new Locker(h,8);
		
		List<IPuntoDeposito> puntiDeposito = new ArrayList<>();
		
		puntiDeposito.add(l1);
		puntiDeposito.add(l2);
		puntiDeposito.add(l3);
		puntiDeposito.add(l4);
		puntiDeposito.add(l5);
		puntiDeposito.add(l6);
		puntiDeposito.add(l7);
		puntiDeposito.add(l8);
		
		 
		System.out.println("locker totali:");
		for(IPuntoDeposito pd : puntiDeposito) {
			System.out.println("("+(((Locker) pd).getPosizione().getLongitudine())+","+(((Locker) pd).getPosizione().getLatitudine())+")");
		}
		
		
		Itinerario i = new Itinerario(a,b);
	*/
		
		
		Coordinate a = new Coordinate(4,5);
		Coordinate b = new Coordinate(7,-2);
		Coordinate c = new Coordinate(3,3);
		Coordinate d = new Coordinate(7,2);
		Coordinate e = new Coordinate(14,1);
		Coordinate f = new Coordinate(5,-1);
		Coordinate g = new Coordinate(8,-2);
		Coordinate h = new Coordinate(0,0);
		
		IPuntoDeposito l1 = new Locker(a,1);
		IPuntoDeposito l2 = new Locker(b,2);
		IPuntoDeposito l3 = new Locker(c,3);
		IPuntoDeposito l4 = new Locker(d,4);
		IPuntoDeposito l5 = new Locker(e,5);
		IPuntoDeposito l6 = new Locker(f,6);
		IPuntoDeposito l7 = new Locker(g,7);
		IPuntoDeposito l8 = new Locker(h,8);
		
		List<IPuntoDeposito> puntiDeposito = new ArrayList<>();
		
		puntiDeposito.add(l1);
		puntiDeposito.add(l2);
		puntiDeposito.add(l3);
		puntiDeposito.add(l4);
		puntiDeposito.add(l5);
		puntiDeposito.add(l6);
		puntiDeposito.add(l7);
		puntiDeposito.add(l8);
		
		 
		System.out.println("locker totali:");
		for(IPuntoDeposito pd : puntiDeposito) {
			System.out.println("("+(((Locker) pd).getPosizione().getLongitudine())+","+(((Locker) pd).getPosizione().getLatitudine())+")");
		}
		
		
		Itinerario it = new Itinerario(a,b); //spedizione
		
		
		MatchingService m = new MatchingService();
		
		m.setPuntiDeposito(puntiDeposito);
		m.itinerarioDivider(it);
		
		System.out.println("*\n*\n*\n*");
		
		Coordinate i = new Coordinate(2,5);
		Coordinate j = new Coordinate(13,2);
		
		Itinerario ic = new Itinerario(i,j);
		
		Spedizione s1 = new Spedizione(null, null, null, 0, l1, l2, m);
		//Spedizione s2 = new Spedizione(null, null, null, 0, l4, l7, m);
		
		s1.setMatchingService(m);
		//s2.setMatchingService(m);
		
		
		List <Spedizione> ls = new ArrayList<>();
		ls.add(s1);
		//ls.add(s2);
		

		List<Spedizione> sc = m.trovaSpedizioniCompatibili(ic, ls);
		
		System.out.println("spedizioni compatibili trovate:");
		for(Spedizione sped : sc) {
			
			System.out.println("inizio: ("+sped.getItinerarioCorrente().getInizio().getLongitudine()+","+sped.getItinerarioCorrente().getInizio().getLatitudine()+")"+
											" fine: ("+sped.getItinerarioCorrente().getFine().getLongitudine()+","+sped.getItinerarioCorrente().getFine().getLatitudine()+")");
			
			
		}
	}
	

}
