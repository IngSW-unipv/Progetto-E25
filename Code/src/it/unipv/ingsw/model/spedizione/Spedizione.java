package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
	private Date dataDeposito;
	
	private MatchingService matchingService;
	private List <Itinerario> itinerarioMancante; //i sottoitinerari che mancano fino alla fine della spedizione 
	private Itinerario itinerarioCorrente; //itinerario che fornisco al carrier (FORSE NON VA QUI)
	
	private Blob codice;
//	private String codice; //???
	private String statoSpedizione;
	List <Observer> observers = new ArrayList<>();
	List <Locker> lockers = new ArrayList<>(); //lista dei locker associati alla spedizione
	
	private Date dataInizioSpedizione;
	QRcode codice_mittente;
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito a, IPuntoDeposito b, MatchingService m) { 
	
		codice_mittente=new QRcode();//codice mittente
	
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		this.destinazione = b;
		this.dataDeposito = null; //inizialmente nessuna data di deposito
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
	
	public void registraDeposito(Date data) {
		this.dataDeposito = data;
	}
	
	public Date getDataDeposito() {
		return this.dataDeposito;
	}
	
	public String getStatoSpedizione() {
		return statoSpedizione;
	}
	
	public void aggiornaStatoSpedizione(boolean isRitiro) {
		//pacco ritirato dal destinatario
		if (isRitiro) {
			this.statoSpedizione = "Consegnato";
			System.out.println("Il pacco è stato ritirato.\nStato aggiornato a 'Consegna'.");
		} else {
			//pacco depositato dal carrier
			this.statoSpedizione = "In attesa";
			System.out.println("Il pacco è stato depositato nel locker ed è in attesa per il ritiro. \nStato aggiornato a 'In attesa'.");
		}
	}
	public void verificaTempoDeposito(Date dataDeposito, boolean isRitiro) {	
		try {
			//verifica quanti gg sono passati dal deposito
			//Date dataDeposito = this.getDataDeposito();
			if (dataDeposito != null) {
				long diffInMillies = Math.abs(new Date().getTime() - dataDeposito.getTime());
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
				//controlla se sono passati più di 3gg dal deposito
				if(diffInDays > 3 && !isRitiro) {
				//pacco reconsegnato al mittente
				this.setStatoSpedizione("Pacco riconsengato al mittente.");
				System.out.println("Il pacco non è stato ritirato in tempo. Stato aggiornato a 'Riconsegnato al mittente'.");
				this.notifyObservers();
				}
			}
		} catch (Exception e) {
			//per esempio dataDeposito è 0. il calcolo è gestito da una libreria di java
			System.out.println("Errore nel calcolo del tempo di deposito: " + e.getMessage());
			System.out.println("Il pacco non è stato depositato entro 3gg. \nStato aggiornato a 'Pacco smarrito'."); // il corriere non ha depositato il pacco entro 3gg <= dataDeposito = 0
			this.setStatoSpedizione("Pacco Smarrito");
		}
	}
	
	public void aggiornaStatoSpedizione(boolean isRitiro) {
		//pacco ritirato dal destinatario
		if (isRitiro) {
			this.statoSpedizione = "Consegnato";
			System.out.println("Il pacco è stato ritirato.\nStato aggiornato a 'Consegna'.");
		} else {
			//pacco depositato dal carrier
			this.statoSpedizione = "In attesa";
			System.out.println("Il pacco è stato depositato nel locker ed è in attesa per il ritiro. \nStato aggiornato a 'In attesa'.");
		}
	}
	public void verificaTempoDeposito(Date dataDeposito, boolean isRitiro) {	
		try {
			//verifica quanti gg sono passati dal deposito
			Date dataDeposito = this.getDataDeposito();
			if (dataDeposito != null) {
				long diffInMillies = Math.abs(new Date().getTime() - dataDeposito.getTime());
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
				//controlla se sono passati più di 3gg dal deposito
				if(diffInDays > 3 && !isRitiro) {
				//pacco reconsegnato al mittente
				this.setStatoSpedizione("Pacco riconsengato al mittente.");
				System.out.println("Il pacco non è stato ritirato in tempo. Stato aggiornato a 'Riconsegnato al mittente'.");
				this.notifyObservers();
				}
			}
		} catch (Exception e) {
			//per esempio dataDeposito è 0. il calcolo è gestito da una libreria di java
			System.out.println("Errore nel calcolo del tempo di deposito: " + e.getMessage());
			System.out.println("Il pacco non è stato depositato entro 3gg. \nStato aggiornato a 'Pacco smarrito'."); // il corriere non ha depositato il pacco entro 3gg <= dataDeposito = 0
			this.setStatoSpedizione("Pacco Smarrito");
		}
	}
	
	//metodo per ottenere l'ultimo deposito
	public Date getUltimoDeposito() {
		if (lockers.isEmpty()) {
			return null; //nessun locker
		}
		
		//ottiene la data di deposito più recente 
		Date ultimoDeposito = null;
		for (Locker locker : lockers) {
			Date dataDeposito = getDataDeposito();
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
