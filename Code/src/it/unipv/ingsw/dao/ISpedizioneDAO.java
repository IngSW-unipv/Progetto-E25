package it.unipv.ingsw.dao;

import java.util.List;

import it.unipv.ingsw.model.spedizione.Spedizione;


public interface ISpedizioneDAO {

	List<Spedizione> selectAllInAttesa();
	
	void addSpedizione(Spedizione spedizione);
	
}
