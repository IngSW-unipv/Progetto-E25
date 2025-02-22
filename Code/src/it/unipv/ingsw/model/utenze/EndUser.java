package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class EndUser extends Utente implements Observer{
	
	
	private String userType; //serve per l'invio della mail esclusiva al destinatario 

	//costruttore
	public EndUser(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,String dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}
	
	//nuovo costruttore che accetta userType
	public EndUser(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,String dataNascita, Blob fotoDocumento, String userType) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		this.userType = userType;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void update(Spedizione spedizione) {
		System.out.println("Aggiornamento spedizione" + spedizione.getCodice() + ": " + spedizione.getStatoSpedizione());
		if (isLoggedIn()) {
			System.out.println(getNome() + "ha ricevuto la notifica tramite l'app: " + "Stato spedizione aggiornato a: " + spedizione.getStatoSpedizione());
		}
		//fuori del blocco if perché in ogni caso si invia la mial
		inviaMail(spedizione);
		
		if(spedizione.getStatoSpedizione().equals("In attesa")) {
			//invia la mail solo se l'utente è un destinatario
			if ("Destinatario".equals(getUserType())) {
				inviaMailDest(spedizione);
			}
			//vuol dire che il destinatario ha scansionato il QR ed ha ritirato il pacco
			if (spedizione.getStatoSpedizione().equals("Consegnato")) {
				System.out.println("Il pacco è stato ritirato da: " + getMail() + ".\nStato aggiornato a 'Consegnato'.");
				notificaMittente();
			}
		}
	}
	
	public void notificaMittente() {
		//logica per notificare il mittente della consegna del pacco
		System.out.println("Il pacco è stato ritirato dal destinatario");
	}
	
	 //metodo per l'invio della mail con il QR al destinatario da scansionare
    public void inviaMailDest(Spedizione spedizione) {
    	System.out.println("Mail mandata al destinatario" + getMail() + "contenente il codiceQR.");
    }
}
