package it.unipv.ingsw.model.spedizione.puntoDeposito;

import java.util.HashMap;
import java.util.Map;

public interface IPuntoDeposito {

	public void checkQR();
	
	Map<Integer, Locker> lockerMap = new HashMap<>();
	
	
}
