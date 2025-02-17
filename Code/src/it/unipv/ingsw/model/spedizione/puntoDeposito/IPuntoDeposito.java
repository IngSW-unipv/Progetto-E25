package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;

public interface IPuntoDeposito {

	public void getPosizione();
	
	public void checkQR();
	
	public boolean checkDisponibilita();
	
	
}
