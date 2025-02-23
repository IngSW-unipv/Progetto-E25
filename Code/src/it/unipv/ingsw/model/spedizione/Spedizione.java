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
	
	private MatchingService matchingService;
	private List <Itinerario> itinerarioMancante; //i sottoitinerari che mancano fino alla fine della spedizione 
	private Itinerario itinerarioCorrente; //itinerario che fornisco al carrier (FORSE NON VA QUI)
	
	private Blob codice;
	private String codice; //???
	private String statoSpedizione;
	List <Observer> observers = new ArrayList<>();
	List <Locker> lockers = new ArrayList<>(); //lista dei locker associati alla spedizione
	
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito a, IPuntoDeposito b, MatchingService m) { 
	private Date dataInizioSpedizione;
	
	QRcode codice_mittente=new QRcode();//codice mittente
	
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		this.destinazione = b;
	//	this.itinerario.setFine(destinazione.getPosizione());
		
		//matchingService = new MatchingService();
		
		matchingService = m;
		
		itinerarioMancante = matchingService.itinerarioDivider(new Itinerario(a.getPosizione(),b.getPosizione())); //da fare qui
	
		
	}
	
	public QRcode getCodice() {
		return codice_mittente;
	}
	
	
	public void setPacco(IShippable shippable) {
		this.shippable = shippable;
	}
	
	public String getStatoSpedizione() {
		return statoSpedizione;
	}
	
	//aggiunge locker alla spedizione
	public void aggiungiLocker(Locker locker) {
		lockers.add(locker);
	}
	
	//metodo per ottenere l'ultimo deposito
	public Date getUltimoDeposito() {
		if (lockers.isEmpty()) {
			return null; //nessun locker
		}
		
		//ottiene la data di deposito più recente 
		Date ultimoDeposito = null;
		for (Locker locker : lockers) {
			Date dataDeposito = locker.getDataDeposito();
			if (dataDeposito != null) {
				if (ultimoDeposito == null || dataDeposito.after(ultimoDeposito))  {
					ultimoDeposito = dataDeposito;
				}
			}
		}
		return ultimoDeposito;
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
	
	public List<Itinerario> getItinerarioMancante(){
		return itinerarioMancante;
	}
	
	//forse non vanno qui:
	public Itinerario getItinerarioCorrente() {
		return itinerarioCorrente;
	}
	
	public void setItinerarioCorrente(Itinerario itinerarioCorrente) {
		this.itinerarioCorrente = itinerarioCorrente;
	}
	
	public MatchingService getMatchingService() {
		return matchingService;
	}

	public void setMatchingService(MatchingService matchingService) {
		this.matchingService = matchingService;
	}

	
	
	
	//notify observers
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this); //faccio riferimento alla spedizione
		}
	}

//	public Itinerario getItinerario() {
//		return itinerario;
//	}
//
//	public void setItinerario(Itinerario itinerario) {
//		this.itinerario = itinerario;
//	}

	public void avvioSpedizione(Utente utente, IPuntoDeposito punto_deposito_partenza, ASuperUser destinatario) { 
		
		if(shippable==null && destinatario==null) System.out.println("i campi obbligatori non sono stati completati");
		
		codice_mittente.generaQRcode();
		boolean controllo_disponibilita=punto_deposito_partenza.checkDisponibilita(shippable, codice_mittente.getQRcode());
		
		if(controllo_disponibilita==false) {
			System.out.printf("Nel locker scelto non c'è disponibilità\n");
		}else {
			// if(pagamento.effettuaPagamento()=true)
			// forse modifico lo stato qr dopo il pagamento
			
			statoSpedizione="In attesa di consegna pacco in locker";
			Date data_inizio=new Date();
			dataInizioSpedizione=data_inizio; //setto la data di inizio spedizione
		//	this.mittente=(Mittente)utente;  //l'utente viene consideranto mittente 
			System.out.printf("Finito avvioSpedizione\n");
		}

	}
	
	public void confermaAvvioSpedizione(IPuntoDeposito punto_deposito_partenza, QRcode codice) {
		boolean controllo_QRcode=punto_deposito_partenza.checkQRsecondo(codice.getQRcode());
		
		if(controllo_QRcode==false) {
			System.out.printf("La spedizione non è avviata\n");
		}else {
			System.out.printf("La spedizione è considerata iniziata\n");
		}
		
	}

	
	public void presaInCarico(Utente utente, Itinerario tratta) {
		
		Size size = shippable.getSize();
		double weight = shippable.getWeight();
		
		//faccio verificare dall'itinerario della spedizione se essa contiene l'itinerario della tratta del carrier
		
		this.carrier = (Carrier) utente;
		
	}

}
