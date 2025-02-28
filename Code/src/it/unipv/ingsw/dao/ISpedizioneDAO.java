package it.unipv.ingsw.dao;

import java.util.List;

import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;


public interface ISpedizioneDAO {

	public List<Spedizione> selectAllInAttesa();
	
	public void addSpedizione(Spedizione spedizione);
	
	public Spedizione aggiornaStatoSpedizione(Spedizione spedizione, String stato);
	
	public Spedizione aggiornaPuntoDepositoIniziale(Spedizione spedizione, IPuntoDeposito puntoDeposito);
	
	
	
}
