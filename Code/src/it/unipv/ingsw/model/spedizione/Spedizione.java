package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

//per utilizzare package shippable
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.*;
import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

import it.unipv.ingsw.model.transazioni.*;

public class Spedizione {
	
	private Mittente mittente;
	private Carrier carrier; //non sono sicuro
	private Destinatario destinatario; 
	private IShippable shippable;
	private int assicurazione; //non sono sicuro
	private IPuntoDeposito destinazione;
//	private IPuntoDeposito partenza;
	private Itinerario itinerario;
	private Blob codice;
	private String statoSpedizione;
	List <Observer> observers = new ArrayList<>();
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito destinazione) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		this.destinazione = destinazione;
//		this.itinerario.setFine(destinazione.getPosizione());  //segna un problema in esecuzione
		this.codice=codice; // codice della spedizione
		this.statoSpedizione=statoSpedizione;
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

	//aggiungi observer
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	//remove observer
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void setStatoSpedizione(String statoSpedizione) {
		this.statoSpedizione = statoSpedizione;
		//notifica gli Observers ad ogni aggiornamento
		notifyObservers();
	}
	
	//notify observers
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this); //faccio riferimento alla spedizione
		}
	}

	public Itinerario getItinerario() {
		return itinerario;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public void avvioSpedizione(Utente utente, IPuntoDeposito punto_deposito_partenza, ASuperUser destinatario) { 
		
		if(shippable==null) System.out.println("registra il pacco");
		
		punto_deposito_partenza.checkDisponibilita(shippable); 
		
		//far partire il pagamento pagamento.effettuaPagamento();
				
		//genera QR
		
		System.out.printf("Finito avvioSpedizione\n");
	}
	
	
	
	public void presaInCarico(Utente utente, Itinerario tratta) {
		
		Size size = shippable.getSize();
		double weight = shippable.getWeight();
		
		//faccio verificare dall'itinerario della spedizione se essa contiene l'itinerario della tratta del carrier
		
		this.carrier = (Carrier) utente;
		
	}

}
