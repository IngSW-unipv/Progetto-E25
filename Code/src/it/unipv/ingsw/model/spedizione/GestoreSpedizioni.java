package it.unipv.ingsw.model.spedizione;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.model.utenze.Utente;

public class GestoreSpedizioni {

	private MatchingService matchingService;
	
	public GestoreSpedizioni(MatchingService matchingService) {
		this.matchingService = matchingService;
	}
	
	
	//divisione itinerario di una spedizione 
	public void dividiItinerario(Spedizione s) {
		
		s.setItinerarioMancante(matchingService.itinerarioDivider(new Itinerario(s.getPartenza().getPosizione(), s.getDestinazione().getPosizione())));
	}
	
	
	//presa in carico di una spedizione 
	public void presaInCaricoSpedizione(Carrier carrier, List<Spedizione> spedizioniDisponibili) {
		
		//ricerca delle spedizioni compatibili
		List<Spedizione> spedizioniCompatibili = matchingService.trovaSpedizioniCompatibili(carrier.getItinerario(), spedizioniDisponibili);
		
		//assegno al carrier tutte le spedizioni compatibili con il suo itinerario
		carrier.assegnaSpedizioni(spedizioniCompatibili);
		
	}
	
	
	
	public static void main(String[] args) {
		
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
		
		
		MatchingService m = new MatchingService();
		GestoreSpedizioni gs = new GestoreSpedizioni(m);
		
		Itinerario it = new Itinerario(a,b); //spedizione
		
		
		m.setPuntiDeposito(puntiDeposito);
		
		
		
		System.out.println("*\n*\n*\n*");
		
		Coordinate i = new Coordinate(2,5);
		Coordinate j = new Coordinate(13,2);
		
		Itinerario ic = new Itinerario(i,j);
		Carrier car = new Carrier(ic);
		
		
		Spedizione s1 = new Spedizione(0, null, l1, l2);
		
		gs.dividiItinerario(s1);
		
		List <Spedizione> ls = new ArrayList<>();
		ls.add(s1);
		
		gs.presaInCaricoSpedizione(car, ls);
		
		System.out.println(car.getSpedizioniAssegnate());


		
		
	}
	
	
	

}
