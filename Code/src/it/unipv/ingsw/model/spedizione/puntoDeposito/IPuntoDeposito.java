package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.awt.geom.Point2D;
import it.unipv.ingsw.model.spedizione.shippable.*;

public interface IPuntoDeposito {

	public Point2D getPosizione();
	
	public boolean checkQR();
	
	public int checkDisponibilita(IShippable daSpedire); //cambiato in intero per recuperare id scompartimento 
	
	
}
