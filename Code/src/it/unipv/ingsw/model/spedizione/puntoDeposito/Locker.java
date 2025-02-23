package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

import java.sql.Blob;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;

public class Locker implements IPuntoDeposito{
	
	private Coordinate posizione;
	private Map<Integer, Scompartimento> scompartimenti; // ogni scompartimento e' identificato da un unico Key
	private Map<String, Integer> mappaQRcode= new HashMap<>(); //mappa dei Qr che il locker si aspetta di ricevere
	private int IDscompartimento;
	private int IDlocker;
	private Date dataDeposito;
	private Spedizione spedizione;
	
	public Locker(Coordinate posizione, int Idlocker, Spedizione spedizione) {
		this.posizione = posizione;
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
		this.IDscompartimento = IDscompartimento;
		this.IDlocker = IDlocker;
		this.dataDeposito = null; //inizialmente nessuna data di deposito
		this.spedizione = spedizione;
	}
	
	public void aggiungiScompartimento(int IDscompartimento, Scompartimento scompartimento) {
		scompartimenti.put(IDscompartimento, scompartimento);
	}
	
	public boolean rimuoviScompartimento(int IDscompartimento) {
		if(scompartimenti.containsKey(IDscompartimento)) {
			scompartimenti.remove(IDscompartimento); //rimuove lo scomp dalla mappa togliendo il suo ID
			System.out.println("Scompartimento con ID " +IDscompartimento+ "rimosso.");
			return true;
		} else {
			System.out.println("Scompartimento con ID " +IDscompartimento+ "non esiste.");
			return false;
		}
	}

	@Override
	public Coordinate getPosizione() {
		return posizione;
	}
	
	//getter per IDlocker
	public int getIDlocker() {
		return IDlocker;
	}
	
//	ottiene uno scompartimento dalla HashMap usando il proprio ID
	public Scompartimento getScompartimento(Integer IDscompartimento) {
		return scompartimenti.get(IDscompartimento);
	}
	
	public int getIDscompartimento() {
		//logica da implementare
		return IDscompartimento;
	}
	
	public void registraDeposito(Date data) {
		this.dataDeposito = data;
	}
	
	public Date getDataDeposito() {
		return this.dataDeposito;
	}
	

	//metodo che funziona sia per il carrier che per il destinatario
	public boolean checkQR(QRcode codice, Spedizione spedizione, boolean isRitiro) {
//		verifica se il codice corrisponde al codice della spedizione
		if(spedizione.getCodice().equals(codice)) {
			Integer IDscompartimento = getIDscompartimento(); //ottiene l'ID dello Scompartimento
			Scompartimento scompartimento = getScompartimento(IDscompartimento); //ottiene lo scompartimento proprio
			scompartimento.Open();
			this.registraDeposito(new Date()); // se il codice è valido registra la data di deposito
			
			try {
				//verifica quanti gg sono passati dal deposito
				Date dataDeposito = this.getDataDeposito();
				if (dataDeposito != null) {
					long diffInMillies = Math.abs(new Date().getTime() - dataDeposito.getTime());
					long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
					
					//controlla se sono passati più di 3gg dal deposito
					if(diffInDays > 3 && !isRitiro) {
					//pacco reconsegnato al mittente
					spedizione.setStatoSpedizione("Pacco riconsengato al mittente.");
					System.out.println("Il pacco non è stato ritirato in tempo. Stato aggiornato a 'Riconsegnato al mittente'.");
					spedizione.notifyObservers();
					}
				}
				return false;
			} catch (Exception e) {
				//per esempio dataDeposito è 0. il calcolo è gestito da una libreria di java
				System.out.println("Errore nel calcolo del tempo di deposito: " + e.getMessage());
				System.out.println("Il pacco non è stato depositato entro 3gg. \nStato aggiornato a 'Pacco smarrito'."); // il corriere non ha depositato il pacco entro 3gg <= dataDeposito = 0
				spedizione.setStatoSpedizione("Pacco Smarrito");
			}
			
			//Aggiornare lo stato della spedizione
			//pacco ritirato dal destinatario
			if(isRitiro) {
				spedizione.setStatoSpedizione("Consegnato");
				System.out.println("Il pacco è stato ritirato.\nStato aggiornato a 'Consegna'.");
			} else {
				//pacco depositato dal carrier
				spedizione.setStatoSpedizione("In attesa.");
				System.out.println("Il pacco è stato depositato nel locker ed è in attesa per il ritiro. \nStato aggiornato a 'In attesa'.");
			}
			return true; //codice valido
		} else {
			System.out.println("Codice Non Valido");
		}
		return false; 
	}
	
	@Override
	public boolean checkQRsecondo(String codice) {
		int id_salvare=0;
		for(Map.Entry<String, Integer> c: mappaQRcode.entrySet()){ //visualizziamo le coppie chiave valore della mappa
			//System.out.printf("Aiuto funziona"+ c.getKey()+"\n");
			if(c.getKey()==codice) {
				id_salvare=c.getValue();
			}
		}
		
		for(Integer c: scompartimenti.keySet()) {
			if(id_salvare==scompartimenti.get(c).getIDscompartimento()) {
				scompartimenti.get(c).Open();
			}
				
		}
		System.out.printf("codice QR valido\n");
		return false;
	}
	
	
	@Override
	public boolean checkDisponibilita(IShippable daSpedire, String codice) {
				Integer id_salvare=0;
				boolean controllo=false;
				for(Integer c: scompartimenti.keySet()) {
					if(scompartimenti.get(c).isOccupato()==false && scompartimenti.get(c).getSize()== daSpedire.getSize()) {
					//	System.out.printf("Scompartimento libero "+ c+"\n");
						id_salvare=c;
						controllo=true;
					}
				}
				
				if(controllo==false) return false; //controllo se c'è disponibilità
				
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
