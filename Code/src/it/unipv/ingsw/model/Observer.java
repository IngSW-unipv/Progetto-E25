package it.unipv.ingsw.model;

import it.unipv.ingsw.model.spedizione.Spedizione;

public interface Observer {
	
	public void update(Spedizione spedizione);
	
}
