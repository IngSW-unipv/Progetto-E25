package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;

public class Locker {
	
	private String lockerId;
	private Map<String, Scompartimento> Scompartimenti; // ogni scompartimento e' identificato da un unico Key
	
	public Locker(String lockerId) {
		this.lockerId = lockerId;
		this.Scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
	}

	public void aggiungiScompartimento(String scompartimentoId, Scompartimento scompartimento) {
		Scompartimenti.put(scompartimentoId, scompartimento);
	}
	
	
}
