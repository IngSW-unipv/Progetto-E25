package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;

public class Mittente extends EndUser{

	public Mittente(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,LocalDate dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}

}
