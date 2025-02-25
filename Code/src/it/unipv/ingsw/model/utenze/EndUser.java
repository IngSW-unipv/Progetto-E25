package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class EndUser extends Utente implements Observer<Spedizione>{
	
	private String userType; //serve per l'invio della mail al destinatario ed al carrier

	//costruttore
	public EndUser(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
	}
	
	//nuovo costruttore che accetta userType
	public EndUser(String mail, String password, String nome, String cognome,String numeroTelefono, String indirizzoCivico,String dataNascita, String fotoDocumento, String userType) {
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
		}
		
		//vuol dire che il destinatario ha scansionato il QR ed ha ritirato il pacco
		if (spedizione.getStatoSpedizione().equals("Consegnato")) {
			System.out.println("Il pacco è stato ritirato da: " + getMail() + ".\nStato aggiornato a 'Consegnato'.");
			notificaMittente(spedizione);
		}
		
		if (spedizione.getStatoSpedizione().equals("Smarrito")) {
			if("Carrier".equals(getUserType())) {
				inviaMailCarrier(spedizione);
			}
		}
	}
	
	public void notificaMittente(Spedizione spedizione) {
		//logica per notificare il mittente della consegna del pacco
		System.out.println("Il pacco è stato ritirato dal destinatario");
	}
	
	 //metodo per l'invio della mail con il QR al destinatario da scansionare
    public void inviaMailDest(Spedizione spedizione) {
    	System.out.println("Mail mandata al destinatario" + getMail() + "contenente il codiceQR.");
    }
    
    //in caso di smarrimento del pacco
    public void inviaMailCarrier(Spedizione spedizione) {
    	System.out.println("Mail mandata al Carrier" + getMail() + "chiedendogli di verificare il ritardo.");
    }
    
    // metodo che simula l'invio della mail, mandata sia al destinatario sia al mittente
    public void inviaMail(Spedizione spedizione) {
		System.out.println("Invio mail a: " + getMail() + "con lo stato della spedizione: " + spedizione.getStatoSpedizione());
	}

	@Override
	public void update(Object dato) {
		// TODO Auto-generated method stub
		
	} 
    
}
