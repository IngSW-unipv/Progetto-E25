package it.unipv.ingsw.dao;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.model.utenze.ASuperUser;

public interface ISuperUserDAO {
	
	public ASuperUser getUtenteByEmail(String email);
	public ASuperUser getAdminById (String id);
	public boolean insertUtente (Utente u);
	public String selectPassword(ASuperUser u);
}
