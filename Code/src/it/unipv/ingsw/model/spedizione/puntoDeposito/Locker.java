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
	
	public Locker(Point2D posizione) {
		this.posizione = posizione;
		this.scompartimenti = new HashMap<>(); // inizializzando con una mappa di scompartimenti vuota
	}
	
	public void aggiungiScompartimento(Scompartimento scompartimento) {
		scompartimenti.put(scompartimento.getIDscompartimento(), scompartimento);
	}
	
	public void rimuoviScompartimento(Scompartimento scompartimento) {
		scompartimenti.remove(scompartimento.getIDscompartimento(), scompartimento);
	}

	@Override
	public Point2D getPosizione() {
		return posizione;
	}
	
	@Override
	//bisogna specificare il locker di partenza o finale???? ci ho pensato ma non so come farlo	
	public boolean checkQR(QRcode codice, Spedizione spedizione) {
//		verifica se il codice corrisponde alla spedizione
		if(spedizione.getCodice().equals(codice)) {
//			controlla che il pacco non sia stato già consegnato, si può anche togliere
			if("In Viaggio".equals(spedizione.getStatoSpedizione())) {
				return true; //il codice è valido per il ritiro o la consegna
			}
		}
		return false; //codice non valido
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
