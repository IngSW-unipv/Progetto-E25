package it.unipv.ingsw.dao;

import java.util.ArrayList;

import it.unipv.ingsw.model.utenze.Utente;

public interface IUtenteDAO {
	
	public boolean inserimentoUtente(Utente u);
	public boolean aggiornamentoUtente(Utente u);
	ArrayList<Utente> selectAll();
}
