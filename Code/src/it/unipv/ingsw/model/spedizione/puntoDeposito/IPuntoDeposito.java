package it.unipv.ingsw.model.spedizione.puntoDeposito;


import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.shippable.*;

public interface IPuntoDeposito {
	
	public int getID();

	public Coordinate getPosizione();
	
	public boolean checkQR(QRcode codice, Spedizione spedizione, boolean isRitiro, boolean isMittenteDeposita);
	
	//public boolean checkQRsecondo(String codice);
	
	public boolean checkDisponibilita(IShippable daSpedire, String codice); 
	
	
}
