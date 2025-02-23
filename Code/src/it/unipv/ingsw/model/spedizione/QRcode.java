package it.unipv.ingsw.model.spedizione;

//import java.sql.Blob;

public class QRcode {
	
	private String codice; 
	private boolean statoqr=false;
	
	public QRcode() {

	}
	
	public void generaQRcode() { 
		String theAlphaNumericS;
	    StringBuilder builder;
	    final int j=7;
	    
	    theAlphaNumericS="ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"012345678";
	    builder= new StringBuilder(7);
	    
	    for(int i=0;i<j;i++) {
	    	int myindex=(int)(theAlphaNumericS.length()*Math.random());
	    	builder.append(theAlphaNumericS.charAt(myindex));
	    }
	    
	    codice=builder.toString();
		//System.out.printf("Codice Qr generato: "+codice+ "\n");
		statoqr=true;  
	}
	
	public String getQRcode() {
		return codice;
	}
	
	public void disabilitaQRcode() {
		statoqr=false;
	}
	

}
