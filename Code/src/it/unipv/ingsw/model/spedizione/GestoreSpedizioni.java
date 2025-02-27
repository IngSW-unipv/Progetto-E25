package it.unipv.ingsw.model.spedizione;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import it.unipv.ingsw.dao.IPuntoDepositoDAO;
import it.unipv.ingsw.dao.ISpedizioneDAO;
import it.unipv.ingsw.dao.LockerDAO;
import it.unipv.ingsw.dao.SpedizioneDAO;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.*;

public class GestoreSpedizioni {

	private MatchingService matchingService;
	private ISpedizioneDAO spedizioneDAO;
	private IPuntoDepositoDAO puntoDepositoDAO;
	private IPuntoDeposito puntoDeposito;
	private QRcode qrcode;
	private static final double TASSOCOMPENSOSOLDI = 0.8;
	private static final double TASSOCOMPENSOPUNTI = 0.2;
	
	
	private List<Spedizione> spedizioniCompatibili; // variabile di istanza
	
	public GestoreSpedizioni(MatchingService matchingService) {
		this.matchingService = matchingService;
		spedizioneDAO = new SpedizioneDAO();
		puntoDepositoDAO = new LockerDAO();
	}
	
	//metodo avvio spedizione
	public Spedizione avvioSpedizione(Utente utente, IPuntoDeposito punto_deposito_partenza, IPuntoDeposito punto_deposito_destinazione, ASuperUser destinatario, Spedizione spedizione, IShippable spedibile) {
		
		Mittente mittente_spedizione= new Mittente(utente.getMail(), null, utente.getNome(), utente.getCognome(), utente.getNumeroTelefono(), utente.getIndirizzoCivico(), utente.getDataNascita(), utente.getFotoDocumento());
		Destinatario destinatario_spedizione= new Destinatario(destinatario.getMail());
		spedizione.setMittente(mittente_spedizione);
		spedizione.setPartenza(punto_deposito_partenza);
		spedizione.setDestinazione(punto_deposito_destinazione);
		spedizione.setDestinatario(destinatario_spedizione);
		spedizione.setPacco(spedibile);
		
		if(spedizione.getPacco()==null && destinatario==null) System.out.println("i campi obbligatori non sono stati completati");
		
		spedizione.getCodice();
		boolean controllo_disponibilita=punto_deposito_partenza.checkDisponibilita(spedizione.getPacco(), spedizione.getCodice().getQRcode());
		
		if(controllo_disponibilita==false) {
			System.out.printf("Nel locker scelto non c'è disponibilità\n");
		}else {
			// if(pagamento.effettuaPagamento()=true)
			// forse modifico lo stato qr dopo il pagamento
			
			spedizione.setStatoSpedizione("In attesa di consegna pacco in locker");
			Date data_inizio=new Date();
			spedizione.setDataInizio(data_inizio);
		//	this.mittente=(Mittente)utente;  //l'utente viene consideranto mittente 
			System.out.printf("Finito avvioSpedizione\n");
		}
		
		return spedizione;

	}
	
	//divisione itinerario di una spedizione 
	public void dividiItinerario(Spedizione s) {
			
		s.setItinerarioMancante(matchingService.itinerarioDivider(new Itinerario(s.getPartenza().getPosizione(), s.getDestinazione().getPosizione())));
	}
	
	//prima fase di presa in carico di una spedizione (ricerca delle spedizioni compatibili)
	public List<Spedizione> presaInCaricoSpedizione(Carrier carrier) {
		
		//ricerca delle spedizioni compatibili
		List<Spedizione> spedizioniCompatibili = matchingService.trovaSpedizioniCompatibili(carrier.getItinerario());
		
		Iterator<Spedizione> iterator = spedizioniCompatibili.iterator();
		while (iterator.hasNext()) {
		    Spedizione s = iterator.next();
		    //genero i QRcode
			qrcode = new QRcode();
			qrcode.generaQRcode();
			carrier.addQRcode(qrcode);
			
			//prenoto i puntiDeposito di arrivo
			puntoDeposito = puntoDepositoDAO.selectPuntoDeposito(s.getItinerarioCorrente().getFine());
		    
		    if (!puntoDeposito.checkDisponibilita(s.getPacco(), qrcode.getQRcode())) {
		    	//se non c'è disponibilità non ne tengo conto
		    	System.out.println("non c'è disponibilita!!");
		    	
//		        iterator.remove(); <----- momentaneamente rimosso per errore nel metodo di verifica disponibilità
		    }
		}
		return spedizioniCompatibili;
	}
	
	//accetto la presa in carico delle spedizioni compatibili
	public void accettaPresaInCarico(Carrier carrier, List<Spedizione> spedizioniCompatibili) {
		//assegno al carrier tutte le spedizioni compatibili con il suo itinerario
		carrier.assegnaSpedizioni(spedizioniCompatibili);
		
		for(Spedizione s : spedizioniCompatibili) {
			s.setStatoSpedizione("IN_TRANSITO");
			//aggiorno lo stato della spedizione col DAO
			spedizioneDAO.aggiornaStatoSpedizione(s, s.getStatoSpedizione());
			
			//assegno un compenso
			double distanza = s.getItinerarioCorrente().getInizio().distanza(s.getItinerarioCorrente().getFine());
			
			double compensoSoldi = distanza*TASSOCOMPENSOSOLDI;
			int compensoPuntiApp = (int) (distanza*TASSOCOMPENSOPUNTI);
			
			carrier.getCompenso().setDenaro(carrier.getCompenso().getDenaro() + compensoSoldi);
			carrier.getCompenso().setPuntiApp(carrier.getCompenso().getPuntiApp() + compensoPuntiApp);
			
			//setto s.itinerarioTOT con un nuovo punto di partenza (forse dovrebbe esser fatto quando consegna il pacco)
			s.setItinerarioTot(new Itinerario(s.getItinerarioCorrente().getFine(), s.getItinerarioTot().getFine()));
			
			//invia il codice QR via notifica e mail
			String codiceQR = s.getCodice().getQRcode();
			String messaggio = "Hai preso in carico la spedizione: " + s.getIDSpedizione() + ". Ecco il tuo codice QR: " + codiceQR;
			carrier.inviaNotificaCarrier(messaggio);
	        carrier.inviaEmailCarrier("Codice QR Spedizione", messaggio);
		}
	}
	
    // Metodo per cercare una spedizione dato un codice QR
    public Spedizione getSpedizioneByCodiceQR(String codice) {
		// Cerca la spedizione corrispondente al codice QR
        for (Spedizione spedizione : spedizioniCompatibili) {
            if (spedizione.getCodice().equals(codice)) {
                return spedizione;  // Ritorna la spedizione trovata
            }
        }
        return null;  // Ritorna null se non trova una spedizione corrispondente
    }
	
	
	
	//gli passo Locker come parametro anziché istanziarne uno ogni volta
	public void ritiraPacco(QRcode codice, Spedizione spedizione, boolean isRitiro, Locker locker) {
		
		//verifica il QR con locker, passando false per 'isMittenteDeposita'
		boolean codiceValido = locker.checkQR(codice, spedizione, true, false, false);
		
		
		//metodo che chiama il metodo checkQR nella classe Locker
		
	}
	
	//gli passo Locker come parametro anziché istanziarne uno ogni volta
	public void depositaPacco(QRcode codice, Spedizione spedizione, boolean isMittenteDeposita, Locker locker) {

		
		//verifica il QR con locker, passando true per 'isMittenteDeposita' e false a 'isRitiro'
		
		boolean codiceValido = locker.checkQR(codice, spedizione, false, isMittenteDeposita, false);
		
		if (!codiceValido) {
			System.out.println("Errore: Il codice QR non è valido.");
		}
	}
	
	public void verificaTempoDeposito(Spedizione spedizione, Date dataDeposito, boolean isRitiro) {	
		try {
			//verifica quanti gg sono passati dal deposito
			//Date dataDeposito = this.getDataDeposito();
			if (dataDeposito != null) {
				long diffInMillies = Math.abs(new Date().getTime() - dataDeposito.getTime());
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
				//controlla se sono passati più di 3gg dal deposito
				if(diffInDays > 3 && !isRitiro) {
				//pacco reconsegnato al mittente
				spedizione.setStatoSpedizione("Pacco riconsegnato al mittente.");
				System.out.println("Il pacco non è stato ritirato in tempo. Stato aggiornato a 'Riconsegnato al mittente'.");
				spedizione.notifyObservers();
				}
			}
		} catch (Exception e) {
			//per esempio dataDeposito è 0. il calcolo è gestito da una libreria di java
			System.out.println("Errore nel calcolo del tempo di deposito: " + e.getMessage());
			System.out.println("Il pacco non è stato depositato entro 3gg. \nStato aggiornato a 'Pacco smarrito'."); // il corriere non ha depositato il pacco entro 3gg <= dataDeposito = 0
			spedizione.setStatoSpedizione("Pacco Smarrito.");
		}
	}
	
//	public void aggiornaStatoSpedizione(Spedizione spedizione, boolean isRitiro) {
//		//pacco ritirato dal destinatario
//		if (isRitiro) {
//			spedizione.setStatoSpedizione("Consegnato");
//			System.out.println("Il pacco è stato ritirato.\nStato aggiornato a 'Consegna'.");
//		} else {
//			//pacco depositato dal carrier
//			spedizione.setStatoSpedizione("In attesa");
//			System.out.println("Il pacco è stato depositato nel locker ed è in attesa per il ritiro. \nStato aggiornato a 'In attesa'.");
//		}
//	}
	
	
	
	public static void main(String[] args) {
		
		Coordinate a = new Coordinate(4,5);
		Coordinate b = new Coordinate(7,-2);
		Coordinate c = new Coordinate(3,3);
		Coordinate d = new Coordinate(7,2);
		Coordinate e = new Coordinate(14,1);
		Coordinate f = new Coordinate(5,-1);
		Coordinate g = new Coordinate(8,-2);
		Coordinate h = new Coordinate(0,0);
		
		IPuntoDeposito l1 = new Locker(a,1);
		IPuntoDeposito l2 = new Locker(b,2);
		IPuntoDeposito l3 = new Locker(c,3);
		IPuntoDeposito l4 = new Locker(d,4);
		IPuntoDeposito l5 = new Locker(e,5);
		IPuntoDeposito l6 = new Locker(f,6);
		IPuntoDeposito l7 = new Locker(g,7);
		IPuntoDeposito l8 = new Locker(h,8);
		
		List<IPuntoDeposito> puntiDeposito = new ArrayList<>();
		
		puntiDeposito.add(l1);
		puntiDeposito.add(l2);
		puntiDeposito.add(l3);
		puntiDeposito.add(l4);
		puntiDeposito.add(l5);
		puntiDeposito.add(l6);
		puntiDeposito.add(l7);
		puntiDeposito.add(l8);
		
		 
		System.out.println("locker totali:");
		for(IPuntoDeposito pd : puntiDeposito) {
			System.out.println("("+(((Locker) pd).getPosizione().getLongitudine())+","+(((Locker) pd).getPosizione().getLatitudine())+")");
		}
		
		
		MatchingService m = new MatchingService();
		GestoreSpedizioni gs = new GestoreSpedizioni(m);
		
		//Itinerario it = new Itinerario(a,b); //spedizione
		
		
		m.setPuntiDeposito(puntiDeposito);
		
		
		
		System.out.println("*\n*\n*\n*");
		
		Coordinate i = new Coordinate(2,5);
		Coordinate j = new Coordinate(13,2);
		
		Itinerario ic = new Itinerario(i,j);
		Carrier car = new Carrier(ic);
		
	
		gs.presaInCaricoSpedizione(car);
		
		System.out.println("numero spedizioni assegnate:::"+car.getSpedizioniAssegnate().size());
		System.out.println("---id spedizione assegnata:: "+car.getSpedizioniAssegnate().get(0).getIDSpedizione());
		System.out.println("---id spedizione assegnata:: "+car.getSpedizioniAssegnate().get(1).getIDSpedizione());


		
		
	}

	public static double getTassocompensosoldi() {
		return TASSOCOMPENSOSOLDI;
	}

	public static double getTassocompensopunti() {
		return TASSOCOMPENSOPUNTI;
	}
	

}
