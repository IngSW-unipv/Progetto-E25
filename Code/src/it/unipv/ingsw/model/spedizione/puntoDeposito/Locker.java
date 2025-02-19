package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;
import java.awt.geom.Point2D;
import java.sql.Blob;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;

public class Locker implements IPuntoDeposito{
	
	private Point2D posizione;
	private Map<Integer, Scompartimento> scompartimenti; // ogni scompartimento e' identificato da un unico Key
	private int IDscompartimento;
	private int IDlocker;
	
	public Locker(Point2D posizione, int Idlocker) {
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
	public Point2D getPosizione() {
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
		return IDscompartimento;
	}
	
	@Override
	//metodo che funziona sia per il carrier che per il destinatario
	public boolean checkQR(QRcode codice, Spedizione spedizione) {
//		verifica se il codice corrisponde al codice della spedizione
		if(spedizione.getCodice().equals(codice)) {
			Integer IDscompartimento = getIDscompartimento(); //ottiene l'ID dello Scompartimento
			Scompartimento scompartimento = getScompartimento(IDscompartimento); //ottiene lo scompartimento proprio
			/*if(scompartimento != null) {
				scompartimento.Open();
				return true;
			} else {
				System.out.println("Scompartimento Non Esiste.");
			}*/ //parte non necessaria???
			
		} else {
			System.out.println("Codice Non Valido");
		}
		return false; 
	}
	
	@Override
	public int checkDisponibilita(IShippable daSpedire) {
		//no soluzione migliore
				int id_salvare=0;
				for(Integer c: scompartimenti.keySet()) {
					if(scompartimenti.get(c).isOccupato()==false && scompartimenti.get(c).getSize()== daSpedire.getSize()) {
					//	scompartimenti.get(c).setOccupato(true);
						System.out.printf("Scompartimento libero "+ c+"\n");
						id_salvare=c;
					}
				}
				
				
				return scompartimenti.get(id_salvare).getIDscompartimento();
	}

	
}
