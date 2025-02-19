package it.unipv.ingsw.model.spedizione.puntoDeposito;

import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.shippable.Size;

public class Scompartimento {
	
	private int IDscompartimento;
	private Size dimensione;
	private boolean occupato;
	
	
	public Scompartimento(int IDscompartimento, Size dimensione) {
		this.IDscompartimento = IDscompartimento;
		this.dimensione = dimensione;
		this.occupato = false;
	}

	public Size getSize() {
		return dimensione;
	}
	
	public void Open() {
		//metodo che simula l'apertura dello scompartimento
	}

	public int getIDscompartimento() {
		return IDscompartimento;
	}

	public void setIDscompartimento(int iDscompartimento) {
		IDscompartimento = iDscompartimento;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	
}
