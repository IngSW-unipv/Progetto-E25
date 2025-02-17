package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;

public class Locker {
	
	private Pacco pacco;
	private String lockerId;
	private Map<String, Scompartimento> scompartimenti; // ogni scompartimento e' identificato da un unico Key
	
	public Locker(String lockerId, int numeroScompartimenti) {
		this.lockerId = lockerId;
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
		for (int i=0; i<numeroScompartimenti; i++) {
			scompartimenti.put(lockerId + "-S" + (i+1), new Scompartimento(lockerId + "-S" + (i+1)));
		}
	}

	
	public String getId() {
		return lockerId;
	}
	
	public boolean paccoPresente() {
		return pacco != null;
	}
	
	public void depositaPacco(Pacco pacco) {
		this.pacco = pacco;
	}
	
}
