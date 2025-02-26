package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;

public class Destinatario extends EndUser {

	public Destinatario(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}

	public Destinatario(String mailDest) {
		// TODO Auto-generated constructor stub
	}

	public void update() {
		
	}
	
}
