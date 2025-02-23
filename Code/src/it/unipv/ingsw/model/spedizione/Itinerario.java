package it.unipv.ingsw.model.spedizione;


public class Itinerario {
	
	private Coordinate inizio;
	private Coordinate fine;
	private static final double TOLLERANZA = 3;
	
	public Itinerario(Coordinate inizio, Coordinate fine) {
		this.inizio = inizio;
		this.fine = fine;
	}

	public Coordinate getInizio() {
		return inizio;
	}

	public void setInizio(Coordinate inizio) {
		this.inizio = inizio;
	}

	public Coordinate getFine() {
		return fine;
	}

	public void setFine(Coordinate fine) {
		this.fine = fine;
	}
	
	public double lunghezza() {
		
		return inizio.distanza(fine);
	}
	
	//verifico se questo itinerario contiene un altro itinerario (considerando la tolleranza)
	public boolean contiene(Itinerario altro) {
		
//		System.out.println(altro.getInizio().distanza(inizio, fine));	
//		System.out.println(altro.getFine().distanza(inizio, fine));	
		
		if(altro.getInizio().distanza(inizio, fine)<TOLLERANZA) {
			
			//caso 1: l'itinerario del pacco è completamente contenuto nell'itinerario del carrier
			if(altro.getFine().distanza(inizio, fine)<TOLLERANZA) {
//				System.out.println("itinerario del pacco completamente contenuto nell'itinerario del carrier");
				
			}
			else {
				//caso2: l'itinerario del pacco è parzialmente contenuto nell'itinerario del carrier
//				System.out.println("itinerario del pacco parzialmente contenuto nell'itinerario del carrier");
			}
			
			return true;
		}
		
		
		return false;
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		Coordinate a = new Coordinate(1,1);
		Coordinate b = new Coordinate(5,1);
		Coordinate c = new Coordinate(2,2);
		Coordinate d = new Coordinate(7,2);
		Coordinate e = new Coordinate(2,1);
		Coordinate f = new Coordinate(4,1);
		
		Itinerario i1 = new Itinerario(a,b); //itinerario carrier
		Itinerario i2 = new Itinerario(c,d); //itinerario pacco
		
		i1.contiene(i2);
		
	}
	
}
