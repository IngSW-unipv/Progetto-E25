package it.unipv.ingsw.dao;

import java.util.List;

import it.unipv.ingsw.model.spedizione.Spedizione;


public interface ISpedizioneDAO {

	List<Spedizione> selectAllInAttesa();
	
	void addSpedizione(Spedizione spedizione);
	
	Spedizione aggiornaStatoSpedizione(Spedizione spedizione, String stato);
	
}
