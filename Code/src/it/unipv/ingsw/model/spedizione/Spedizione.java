package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;

//per utilizzare package shippable
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.*;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

public class Spedizione {
	
	private Mittente mittente;
	private Carrier carrier; //non sono sicuro
	private Destinatario destinatario; 
	private IShippable shippable;
	private int assicurazione; //non sono sicuro
	private IPuntoDeposito destinazione;
	
	
	public Spedizione(Mittente mittente, Destinatario destinatario, IShippable shippable, int assicurazione, IPuntoDeposito destinazione) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.shippable = shippable;
		this.assicurazione = assicurazione;
		this.destinazione = destinazione;
	}
	
	public void setPacco(IShippable shippable) {
		this.shippable = shippable;
	}


	public void avvioSpedizione(Utente utente, Locker lockerIniziale, ASuperUser destinatario) {
		
		if(shippable==null) System.out.println("registra il pacco");
		
		//far partire il pagamento ...
	}
	
	
	public void presaInCarico(Utente utente) {
		
		Size size = shippable.getSize();
		double weight = shippable.getWeight();
		
		//calcolo 
		
		this.carrier = (Carrier) utente;
		
	}

}
