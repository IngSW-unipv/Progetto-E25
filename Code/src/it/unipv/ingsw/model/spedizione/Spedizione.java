package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

//per controllare data di deposito
import java.util.Date;
import javax.xml.crypto.Data;

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
	private Date dataDeposito;
	private Date dataInizioSpedizione;
	
	QRcode codiceQR= new QRcode();
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito destinazione) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		this.destinazione = destinazione;
	//	this.itinerario.setFine(destinazione.getPosizione());

//		this.itinerario.setFine(destinazione.getPosizione());  //segna un problema in esecuzione
	}
		
	public Blob getCodice() {
		return codice;
	}
	
	public Date getDataDeposito() {
		return dataDeposito;
	}
	
	public void setDataDeposito(Date data) {
		this.dataDeposito = data;
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
		
		if(shippable==null && destinatario==null) System.out.println("i campi obbligatori non sono stati completati");
		
		punto_deposito_partenza.checkDisponibilita(shippable); 
		
		codiceQR.generaQRcode();
			
		/* if(pagamento.effettuaPagamento()=true)
		 * 		codiceQR.generaQRcode();
		 */
		statoSpedizione="In attesa di consegna pacco in locker";
		Date data_inizio=new Date();
		dataInizioSpedizione=data_inizio; //setto la data di inizio spedizione
		this.mittente=(Mittente)utente;  //l'utente viene consideranto mittente 
		System.out.printf("Finito avvioSpedizione\n");
		
	}

	
	public void presaInCarico(Utente utente, Itinerario tratta) {
		
		Size size = shippable.getSize();
		double weight = shippable.getWeight();
		
		//faccio verificare dall'itinerario della spedizione se essa contiene l'itinerario della tratta del carrier
		
		this.carrier = (Carrier) utente;
		
	}

}
