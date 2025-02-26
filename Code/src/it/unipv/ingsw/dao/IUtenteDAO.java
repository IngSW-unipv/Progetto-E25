package it.unipv.ingsw.dao;

import java.util.ArrayList;

import it.unipv.ingsw.model.utenze.Utente;

public interface IUtenteDAO {
	
	public boolean inserimentoUtente(Utente u);
	public Utente getUtenteByEmailPassword(String mail,String password);
	public Utente aggiornamentoUtente(Utente u,String password,String nome,String cognome,String numeroTelefono,String dataNascita,String indirizzoCivico,String fotoDocumento);
	ArrayList<Utente> selectAll();
}
