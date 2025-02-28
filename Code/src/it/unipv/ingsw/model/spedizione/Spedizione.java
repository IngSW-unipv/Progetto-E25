package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
//import java.io.BufferedReader; 
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.TimeUnit;
//per controllare data di deposito
import java.util.Date;
//import javax.xml.crypto.Data;

//per utilizzare package shippable
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.*;
import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

//import it.unipv.ingsw.model.transazioni.*;

public class Spedizione {
	
	private int IDSpedizione;
	private Mittente mittente;
	private Carrier carrier; //non sono sicuro
	private Destinatario destinatario; 
	private IShippable shippable;
	private int assicurazione; //non sono sicuro
	private IPuntoDeposito partenza;
	private IPuntoDeposito destinazione;
	private Date dataInizio;
	private Date dataFine;
	private Date dataDeposito;
//	private Date dataInizioSpedizione;
	private QRcode qrCodice; //questo è il codice Qr
	private String codice;
	private Itinerario itinerarioTot;
	private MatchingService matchingService;
	private List <Itinerario> itinerarioMancante; //i sottoitinerari che mancano fino alla fine della spedizione 
	private Itinerario itinerarioCorrente; //itinerario a cui deve far riferimento il carrier
	

	private String statoSpedizione;
	List <Observer<Spedizione>> observers = new ArrayList<>();
	List <Locker> lockers = new ArrayList<>(); //lista dei locker associati alla spedizione
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito a, IPuntoDeposito b, MatchingService m, Date dataDeposito) { 
	
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		this.partenza = a;
		this.destinazione = b;
		this.dataDeposito = null; //inizialmente nessuna data di deposito
		this.qrCodice = new QRcode();
		this.codice=qrCodice.generaQRcode();
	//	this.itinerario.setFine(destinazione.getPosizione());
		
		//matchingService = new MatchingService();
		
		matchingService = m;
		
	//	itinerarioMancante = matchingService.itinerarioDivider(new Itinerario(a.getPosizione(),b.getPosizione())); //da fare qui	
	}
	
	public Spedizione(int id, IShippable shippable, IPuntoDeposito a, IPuntoDeposito b) {
		this.IDSpedizione = id;
		this.shippable = shippable; 
		this.partenza = a;
		this.destinazione = b;
		itinerarioTot = new Itinerario(a.getPosizione(),b.getPosizione());
		
	}
	
	//costruttore default
	public Spedizione() {
		
	}

	public Spedizione(int iDSpedizione) {
		IDSpedizione = iDSpedizione;
	}

	public Spedizione(String string, Itinerario itinerarioSpedizione1, Itinerario itinerarioSpedizioneTot1,
			QRcode qrcode) {
		// serve per il test
	}

	public Spedizione(int i, String string, String string2, Object object, String string3, String string4, int j, int k,
			int l) {
	}

	public Itinerario getItinerarioTot() {
		return itinerarioTot;
	}

	public void setItinerarioTot(Itinerario itinerarioTot) {
		this.itinerarioTot = itinerarioTot;
	}

	public int getIDSpedizione() {
		return IDSpedizione;
	}
	
	public void setIDSpedizione(int IDSpedizione) {
		this.IDSpedizione=IDSpedizione;
	}
	
	public Mittente getMittente() {
		return mittente;
	}
	
	public void setMittente(Mittente mittente) {
		this.mittente=mittente;
	}
	
	public Destinatario getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(Destinatario destinatario) {
		this.destinatario=destinatario;
	}
	
	
	public QRcode getCodice() {
		return qrCodice;
	}
	
	
	
	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setPacco(IShippable shippable) {
		this.shippable = shippable;
	}
	
	public IShippable getPacco() {
		return shippable;
	}
	
	public void setAssicurazione(int assicurazione) {
		this.assicurazione = assicurazione;
	}
	
	public int getAssicurazione() {
		return assicurazione;
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
	
	public void setDataInizio(Date data) {
		this.dataInizio=data;
	}
	
	public Date getDataInizio() {
		return dataInizio;
	}
	
	public void setDataFine(Date data) {
		this.dataFine=data;
	}
	
	public Date getDataFine() {
		return dataFine;
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
	public void addObserver(Observer<Spedizione> observer) {
		observers.add(observer);
	}
	
	//remove observer
	public void removeObserver(Observer<Spedizione> observer) {
		observers.remove(observer);
	}
	
	public void setStatoSpedizione(String statoSpedizione) {
		this.statoSpedizione = statoSpedizione;
		//notifica gli Observers ad ogni aggiornamento
		notifyObservers();
//		System.out.println("Stato spedizione aggiornato a: " + statoSpedizione);
	}
	
	//notify observers
	public void notifyObservers() {
		for (Observer<Spedizione> observer : observers) {
			observer.update(this); //facco riferimento alla spedizione
		}
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

	
	public IPuntoDeposito getPartenza() {
		return partenza;
	}

	public void setPartenza(IPuntoDeposito partenza) {
		this.partenza = partenza;
	}

	public IPuntoDeposito getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(IPuntoDeposito destinazione) {
		this.destinazione = destinazione;
	}

	public void setItinerarioMancante(List<Itinerario> itinerarioMancante) {
		this.itinerarioMancante = itinerarioMancante;
	}


//	public Itinerario getItinerario() {
//		return itinerario;
//	}
//
//	public void setItinerario(Itinerario itinerario) {
//		this.itinerario = itinerario;
//	}

	/*
	public void avvioSpedizione(Utente utente, IPuntoDeposito punto_deposito_partenza, ASuperUser destinatario) { 
		
		if(shippable==null && destinatario==null) System.out.println("i campi obbligatori non sono stati completati");
		
		codice.generaQRcode();
		boolean controllo_disponibilita=punto_deposito_partenza.checkDisponibilita(shippable, codice.getQRcode());
		
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
		boolean controllo_QRcode=punto_deposito_partenza.checkQR(codice.getQRcode);
		boolean controllo_QRcode=punto_deposito_partenza.checkQR(codice.getQRcode());
		
		if(controllo_QRcode==false) {
			System.out.printf("La spedizione non è avviata\n");
		}else {
			System.out.printf("La spedizione è considerata iniziata\n");
			statoSpedizione="Consegnato nel locker di partenza";
			Date data_deposito=new Date();
			dataDeposito=data_deposito;
			System.out.printf("Data deposito: "+ dataDeposito+"\n");
		}
		
	}
 
	}*/
	
	

}
