package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;

import it.unipv.ingsw.model.Observer;

public class EndUser extends Utente implements Observer{

	//costruttore
	public EndUser(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,LocalDate dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}
	
	//metodi da implementare
	public void update() {}
}
