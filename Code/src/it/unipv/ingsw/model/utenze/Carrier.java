package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Carrier extends Utente{
	private Map<Integer, Carrier> carrierMap;
	//costruttore
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,LocalDate dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		carrierMap = new HashMap<>();
	}

	public void put(int i, Carrier carrier) {
		
	}
	
}
