package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import it.unipv.ingsw.model.Observer;

public class EndUsers extends Utente implements Observer{
	private Map<Integer, EndUsers> EndUsersMap;
	//costruttore
	public EndUsers(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,LocalDate dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		EndUsersMap = new HashMap<>();
	}
	
	//metodi da implementare
	public void update() {}
}
