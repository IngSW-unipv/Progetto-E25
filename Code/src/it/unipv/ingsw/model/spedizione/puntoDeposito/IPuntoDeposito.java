package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public interface IPuntoDeposito {

	public Point2D getPosizione();
	
	public boolean checkQR();
	
	public boolean checkDisponibilita();
	
	
}
