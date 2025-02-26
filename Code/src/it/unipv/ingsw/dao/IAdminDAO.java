package it.unipv.ingsw.dao;

import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;

public interface IAdminDAO {
	public Admin getAdminByMatricola(String matricola); 
}
