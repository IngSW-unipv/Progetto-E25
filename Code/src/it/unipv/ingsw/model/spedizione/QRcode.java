package it.unipv.ingsw.model.spedizione;

import java.sql.Blob;

public class QRcode {
	
	private String codice; 
	private boolean statoqr=false;
	
	public QRcode() {

	}
	
	public void generaQRcode() { 
		//generazione qr
		statoqr=true;
	}
	
	public String getQRcode() {
		return codice;
	}
	
	public void disabilitaQRcode() {
		statoqr=false;
	}

}
