package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;

//per utilizzare package shippable
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.*;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

public class Spedizione {
	
	private Mittente mittente;
	private Carrier carrier; //non sono sicuro
	private Destinatario destinatario; 
	private IShippable shippable;
	private int assicurazione; //non sono sicuro
//	private IPuntoDeposito destinazione;
//	private IPuntoDeposito partenza;
	private Itinerario itinerario;
	private Blob codice;
	private String statoSpedizione;
	
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito destinazione) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		//this.destinazione = destinazione;
		this.itinerario.setFine(destinazione.getPosizione());
	}
	
	public Blob getCodice() {
		return codice;
	}
	
	public void setPacco(IShippable shippable) {
		this.shippable = shippable;
	}
	
	public String getStatoSpedizione() {
		return statoSpedizione;
	}

	public void setStatoSpedizione(String statoSpedizione) {
		this.statoSpedizione = statoSpedizione;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public void avvioSpedizione(Utente utente, Locker lockerIniziale, ASuperUser destinatario) {
		
		if(shippable==null) System.out.println("registra il pacco");
		
		int id_locker_libero=lockerIniziale.checkDisponibilita(shippable);
		System.out.print(id_locker_libero);
		
				
				//far partire il pagamento ...
				
				//genera QR
		
		System.out.printf("Finito avvioSpedizione");
	}
	
	
	
	public void presaInCarico(Utente utente, Itinerario tratta) {
		
		Size size = shippable.getSize();
		double weight = shippable.getWeight();
		
		//faccio verificare dall'itinerario della spedizione se essa contiene l'itinerario della tratta del carrier
		
		this.carrier = (Carrier) utente;
		
	}

}
