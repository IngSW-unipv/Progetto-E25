package it.unipv.ingsw.dao;
import it.unipv.ingsw.recensioni.*;
import it.unipv.ingsw.model.spedizione.*;

public interface IRecensioniDAO {
	
	public void addRecensione(Recensioni r, Spedizione id);

}
