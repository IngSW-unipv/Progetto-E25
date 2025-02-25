package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import it.unipv.ingsw.model.spedizione.Itinerario;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class Carrier extends Utente{
	private Itinerario itinerario; //itinerario che il carrier inserisce
	private List<Spedizione> spedizioniAssegnate;
	
	//costruttore
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento, Itinerario itinerario) {
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento,Itinerario itinerario) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		this.itinerario = itinerario;
	}
	
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}
	
	//per debug
	public Carrier(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
	
	
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}
	
	public void assegnaSpedizioni(List<Spedizione> spedizioni) {
		spedizioniAssegnate = spedizioni;
	}

	public List<Spedizione> getSpedizioniAssegnate() {
		return spedizioniAssegnate;
	}

	
	
	
}
