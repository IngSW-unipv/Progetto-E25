package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

public class Locker implements IPuntoDeposito{
	
	private Pacco pacco;
	private String idLocker;
	private Map<String, Scompartimento> scompartimenti; // ogni scompartimento e' identificato da un unico Key
	
	public Locker(String lockerId, int numeroScompartimenti) {
		this.idLocker = idLocker;
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
		for (int i=0; i<numeroScompartimenti; i++) {
			scompartimenti.put(idLocker + "-S" + (i+1), new Scompartimento(idLocker + "-S" + (i+1)));
		} //aggiunge scompartimenti alla mappa ognunco con Id univoco
	}
	
	public String getId() {
		return idLocker;
	}
	
	// Getter per la mappa degli scompartimenti
    public Map<String, Scompartimento> getScompartimenti() {
        return scompartimenti;
    }
    
    //metodo per il ritiro di un pacco con un id specificato
    public boolean ritiraPacco(String idScompartimento) {
    	Scompartimento scompartimento = scompartimenti.get(idScompartimento);
    	if(scompartimento.Occupato()) {
    		scompartimento.ritiraPacco(); // ritira il pacco e si libera lo scompartimento
    		return true;
    	}else {
    		return false; //lo scompartimento e' vuoto
    	}
    }
    

	public boolean paccoPresente() {
		return pacco != null;
	}
	
	public void depositaPacco(Pacco pacco) {
		this.pacco = pacco;
	}
	
	@Override
	public void getPosizione() {
		
	}
	
	@Override
	public void checkQR() {
		
	}
	
	@Override
	public String checkDisponibilita() {
		for (Map.Entry<String, Scompartimento> entry : scompartimenti.entrySet()) {
			Scompartimento idScompartimento= entry.getValue();
			if(idScompartimento.Occupato()==false) {
				System.out.printf("Uno scompartimento è libero");
				return idLocker;
			}	
		}
		return null;
	}
	
}
