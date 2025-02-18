package it.unipv.ingsw.dao;

import it.unipv.ingsw.model.utenze.Utente;

public interface IUtenteDAO {
	
	public boolean inserimentoUtente(Utente u);
	public boolean aggiornamentoUtente(Utente u);
}
