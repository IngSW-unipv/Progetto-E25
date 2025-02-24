package it.unipv.ingsw.model;

import it.unipv.ingsw.model.spedizione.Spedizione;

public interface Observer<T> {
	
	public void update(T dato);
	
}
