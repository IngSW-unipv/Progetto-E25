package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;

public class Locker implements IPuntoDeposito{
	
	private Coordinate posizione;
	private Map<Integer, Scompartimento> scompartimenti; // ogni scompartimento e' identificato da un unico Key
	private Map<String, Integer> mappaQRcode= new HashMap<>(); //mappa dei Qr che il locker si aspetta di ricevere
//	private Map<String, Spedizione> mappaQR= new HashMap<>(); //ha come chiave codice QR e valore l'oggetto spedizione
	private int IDscompartimento;
	private int IDlocker;
	private LocalDateTime dataDeposito;
	
	public Locker(Coordinate posizione, int Idlocker) {
		this.posizione = posizione;
//		dataDeposito = null; //inizialmente nessuna data di deposito
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
//		this.mappaQR = new HashMap<>();
	}
	public Locker(Coordinate posizione) {
		this.posizione = posizione;
		scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
		dataDeposito = null; //inizialmente nessuna data di deposito
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
	}
	public void aggiungiScompartimento(Scompartimento scompartimento) {
		scompartimenti.put(scompartimento.getIDscompartimento(), scompartimento);
	}
	
	public boolean rimuoviScompartimento(Scompartimento scompartimento) {
		if(scompartimenti.containsKey(scompartimento.getIDscompartimento())) {
			scompartimenti.remove(scompartimento.getIDscompartimento()); //rimuove lo scomp dalla mappa togliendo il suo ID
			System.out.println("Scompartimento rimosso.");
			return true;
		} else {
			System.out.println("Scompartimento non esiste.");
			return false;
		}
	}

	@Override
	public Coordinate getPosizione() {
		return posizione;
	}
	
	@Override
	public int getID() {
		return IDlocker;
	}
	
	
	//getter per IDlocker
	public int getIDlocker() {
		return IDlocker;
	}
	
//	ottiene uno scompartimento dalla HashMap usando il proprio ID
	public Scompartimento getScompartimento(int scompartimento) {
		return scompartimenti.get(scompartimento);
	}
	
	public int getIDscompartimento() {
		return IDscompartimento;
	}
	
	public void registraDeposito(LocalDateTime localDateTime) {
		this.dataDeposito = localDateTime;
		System.out.println("Deposito Registrato: " + localDateTime);
	}
	
	private LocalDateTime getDataDeposito() {
		return dataDeposito;
	}
	
	
	//metodo che funziona sia per il carrier che per il destinatario
	public boolean checkQR(QRcode codice, Spedizione spedizione, boolean isRitiro, boolean isMittenteDeposita, boolean isPresaInCarico) {
		
		MatchingService m = new MatchingService();
		GestoreSpedizioni gs = new GestoreSpedizioni(m);
		
		//recupera il codice dalla classe QRcode
		String codiceQR = codice.getQRcode();
		
		//verifica se il codice esiste nella mappa dei QR che il locker aspetta
		if(!mappaQRcode.containsKey(codiceQR)) {
			System.out.println("Errore: Il codice QR non è valido");
		}
		System.out.println("Codice QR valido: " + codiceQR);
			
		if (isMittenteDeposita) {
			spedizione.setStatoSpedizione("In attesa.");
			System.out.println("Il pacco è stato deppositato dal mittente.");
		}
				
		if (isRitiro) {
			spedizione.setStatoSpedizione("Consegnato.");
			System.out.println("Il pacco è stato ritirato dal destinatario. Stato aggiornato a 'Consegnato'.");
		}
			
		if (isPresaInCarico) {
			 spedizione.setStatoSpedizione("IN_TRANSITO");
		        System.out.println("Il carrier ha preso in carico la spedizione. Stato aggiornato a 'IN_TRANSITO'.");
		}
				
			//se il qr valido, procede ed apre lo sc associato
			int s = mappaQRcode.get(codiceQR);
			Scompartimento sc = scompartimenti.get(s);
			sc.Open();
			System.out.println("Scompartimento aperto!");
				
			this.registraDeposito(LocalDateTime.now());
				
			mappaQRcode.remove(codiceQR); //elimina dalla mappa il codiceQR scansionato
			System.out.println("CodiceQR rimosso dalla mappa dei codici attesi");
					
			return true;
			
	}

//	@Override
//	public boolean checkQRsecondo(String codice) {
//		int id_salvare=0;
//		boolean controllo=false;
//		for(Map.Entry<String, Integer> c: mappaQRcode.entrySet()){ //visualizziamo le coppie chiave valore della mappa
//			//System.out.printf("Aiuto funziona"+ c.getKey()+"\n");
//			if(c.getKey()==codice) {
//				id_salvare=c.getValue();
//			}
//		}
//		
//		for(Integer c: scompartimenti.keySet()) {
//			if(id_salvare==scompartimenti.get(c).getIDscompartimento()) {
//				controllo=true;
//				//System.out.println("Ho trovato lo scompartimento");
//				scompartimenti.get(c).Open();
//			}
//		}
//		//System.out.printf("VAlore controllo: "+ controllo+"\n");
//		return controllo;	
//	}
	
	
	@Override
	public boolean checkDisponibilita(IShippable daSpedire, String codice) {
				Integer id_salvare=0;
				boolean controllo=false;
				
				
				for(Integer c: scompartimenti.keySet()) {
					if(scompartimenti.get(c).isOccupato()==false && scompartimenti.get(c).getSize()== daSpedire.getSize()) {
						System.out.printf("Scompartimento libero "+ c+"\n");
						id_salvare=c;
						controllo=true;
					}
				}
				
				
				if(controllo==false) {
					return false; 
				}else {
					scompartimenti.get(id_salvare).setOccupato(true);
					mappaQRcode.put(codice,id_salvare);	//carico nella mapppa QR lo scompartimento relativo
					if(scompartimenti.get(id_salvare).isOccupato()==true) {
						//System.out.printf("Scompartimento prenotato \n");
						return true;
					}else {
						return false;
					}
				}
				
	}

	public Map<String, Integer> getMappaQRcode() {
		return mappaQRcode;
	}

	public Map<Integer, Scompartimento> getScompartimenti() {
		return scompartimenti;
	}


}
