package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class EndUser extends Utente implements Observer{
	
	
	//costruttore
	public EndUser(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,String dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}
	
	
	//metodi da implementare
	@Override
	public void update(Spedizione spedizione) {
		System.out.println("Aggiornamento spedizione" + spedizione.getCodice() + ": " + spedizione.getStatoSpedizione());
		if (isLoggedIn()) {
			System.out.println(getNome() + "ha ricevuto la notifica tramite l'app: " + "Stato spedizione aggiornato a: " + spedizione.getStatoSpedizione());
		}
		//fuori del blocco if perché in ogni caso si invia la mial
		inviaMail(spedizione);
	}


	//serve per simulare l'invio della mail
	public void inviaMail(Spedizione spedizione) {
		System.out.println("Invio mail a: " + getMail() + "con lo stato della spedizione: " + spedizione.getStatoSpedizione());
	}

}
