package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
	private int IDscompartimento;
	private int IDlocker;
	
	public Locker(Coordinate posizione, int Idlocker) {
		this.posizione = posizione;
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
		this.IDscompartimento = IDscompartimento;
		this.IDlocker = IDlocker;
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
	
	@Override
	//metodo che funziona sia per il carrier che per il destinatario
	public boolean checkQR(QRcode codice, Spedizione spedizione, boolean isRitiro) {
//		verifica se il codice corrisponde al codice della spedizione
		if(spedizione.getCodice().equals(codice)) {
			Integer IDscompartimento = getIDscompartimento(); //ottiene l'ID dello Scompartimento
			Scompartimento scompartimento = getScompartimento(IDscompartimento); //ottiene lo scompartimento proprio
			scompartimento.Open();
			
			try {
				//controlla se sono passati più di 3gg dal deposito
				Date dataDeposito = spedizione.getDataDeposito();
				long diffInMillies = Math.abs(new Date().getTime() - dataDeposito.getTime());
				long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				
				if(diffInDays > 3 && !isRitiro) {
					//pacco reconsegnato al mittente
					spedizione.setStatoSpedizione("Pacco riconsengato al mittente.");
					System.out.println("Il pacco non è stato ritirato in tempo. Stato aggiornato a 'Riconsegnato al mittente'.");
					spedizione.notifyObservers();
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
	public boolean checkDisponibilita(IShippable daSpedire) {
				int id_salvare=0;
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
				if(scompartimenti.get(id_salvare).isOccupato()==true) {
					//System.out.printf("Scompartimento prenotato \n");
					return true;
				}else {
					return false;
				}
	}

	
}
