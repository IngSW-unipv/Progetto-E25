package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;

public class Carrier extends Utente{
	//costruttore
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,LocalDate dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);

	
}
