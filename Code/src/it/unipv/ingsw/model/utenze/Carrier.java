package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.spedizione.Itinerario;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class Carrier extends Utente{
	private Itinerario itinerario; //itinerario che il carrier inserisce
	private List<Spedizione> spedizioniAssegnate;
	private List<QRcode> qrcodes;
	private Saldo compenso;
	
	//costruttore
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento,Itinerario itinerario) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		this.itinerario = itinerario;
		qrcodes = new ArrayList<>();
		compenso = new Saldo(0,0);
	}
	
	public Carrier(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		qrcodes = new ArrayList<>();
		compenso = new Saldo(0,0);
	}
	
	//per debug
	public Carrier(Itinerario itinerario) {
		this.itinerario = itinerario;
		qrcodes = new ArrayList<>();
		compenso = new Saldo(0,0);
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
	
	public void addQRcode(QRcode qr) {
		qrcodes.add(qr);
	}

	public Saldo getCompenso() {
		return compenso;
	}

	public void setCompenso(Saldo compenso) {
		this.compenso = compenso;
	}

	@Override
    public void inviaNotificaCarrier(String messaggio) {
        // Implementazione della notifica tramite l'app
        System.out.println("Notifica per " + getNome() + ": " + messaggio);
    }

    @Override
    public void inviaEmailCarrier(String soggetto, String corpo) {
        // Implementazione dell'invio email
        System.out.println("Email inviata a " + getMail());
        System.out.println("Oggetto: " + soggetto);
        System.out.println("Corpo: " + corpo);
    }
	
	
	
}
