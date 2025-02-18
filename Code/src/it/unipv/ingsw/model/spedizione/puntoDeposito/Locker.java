package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;
import java.awt.geom.Point2D;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

public class Locker implements IPuntoDeposito{
	
	private Point2D posizione;
	private Map<Integer, Scompartimento> scompartimenti; // ogni scompartimento e' identificato da un unico Key
	
	public Locker(Point2D posizione) {
		this.posizione = posizione;
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
	}
	
	public void aggiungiScompartimento(Scompartimento scompartimento) {
		//aggiunta scompartimento nella mappa
	}
	
	public void rimuoviScompartimento(Scompartimento scompartimento) {
		//rimozione scompartimento nella mappa
	}
    

	@Override
	public Point2D getPosizione() {
		return posizione;
	}
	
	@Override
	public boolean checkQR() {
		
		// da implementare ...
		
		return true;
	}
	
	@Override
	public boolean checkDisponibilita() {
		
		// da implementare ...
		
		return true;
	}
	
}
