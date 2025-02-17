package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;

public class Scompartimento {
	
	private Pacco paccoInScompartimento;

	Map<Integer, Scompartimento> scompartimentoMap = new HashMap<>();
	
	public void put(int i, Scompartimento scompartimento) {
		
	}
	
	public void getSize() {
		
	}
	
	public void Open() {
		
	}
	
	public Scompartimento() {
		this.paccoInScompartimento = null; // inizialmente vuoto
	}
	
	public boolean Occupato() {
		if(paccoInScompartimento != null) { //controlla se lo scompartimento non e' vuoto
			return true; // lo scompartimento occupato da un pacco non ritirato 
		} else {
			return false; //lo scompartimento e' vuoto
		}
	}
	
	public Pacco getPacco() {
		return paccoInScompartimento;
	}
	
	public void setPacco(Pacco paccoInScompartimento) {
		this.paccoInScompartimento = paccoInScompartimento;
	}
	
	public void clearScompartimento() {
		this.paccoInScompartimento = null; //dopo il ritiro del pacco, lo scompartimento torna vuoto
	}
	
}
