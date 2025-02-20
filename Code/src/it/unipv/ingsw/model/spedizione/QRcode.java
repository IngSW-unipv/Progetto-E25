package it.unipv.ingsw.model.spedizione;

import java.sql.Blob;

public class QRcode {
	
	private Blob id_qr;
	private boolean stato_qr=false;
	
	public QRcode() {

	}
	
	public void generaQRcode() { 
		//generazione qr
		stato_qr=true;
	}
	
	public Blob getQRcode() {
		return id_qr;
	}
	
	public void disabilitaQRcode() {
		stato_qr=false;
	}

}
