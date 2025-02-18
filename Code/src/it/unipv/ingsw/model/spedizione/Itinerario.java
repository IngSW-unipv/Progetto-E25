package it.unipv.ingsw.model.spedizione;

import java.awt.geom.Point2D;

public class Itinerario {
	
	private Point2D inizio;
	private Point2D fine;
	
	public Itinerario(Point2D inizio, Point2D fine) {
		this.inizio = inizio;
		this.fine = fine;
	}

	public Point2D getInizio() {
		return inizio;
	}

	public void setInizio(Point2D inizio) {
		this.inizio = inizio;
	}

	public Point2D getFine() {
		return fine;
	}

	public void setFine(Point2D fine) {
		this.fine = fine;
	}
	
	
	public boolean contiene(Itinerario altro) {
		
		//verifico se questo itinerario contiene l'itinerario passato in altro 
		
		return true;
	}
	
}
