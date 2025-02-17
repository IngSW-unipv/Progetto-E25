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

	public Spedizione() {
		// TODO Auto-generated constructor stub
	}
	
	public void AvvioSpedizione() {
		InputStreamReader in=new InputStreamReader(System.in);
		BufferedReader keyboard=new BufferedReader(in);
		
		Locker locker;
		Pacco pacco_spedire= new Pacco();
		Destinatario destinatario_pacco;
		boolean controllo=false;
		String email_destinatario=null;

//richiesta inserimento dati dall'utente
		System.out.printf("Inserire dettagli pacco"); 
		String dimensione_pacco=pacco_spedire.setSize();  //non sono sicura di usare gli emun
		int peso_pacco=pacco_spedire.setWeight();

		while(controllo==false) { //permetto all'utente di avere piu possibilità di inserire i dati se sbagliati
			try { //non sono sicura che siano questi i dati da inserire 
				System.out.printf("Inserire nome del destinatario");
				destinatario_pacco.setNome(keyboard.readLine());
				
				System.out.printf("Inserire cognome del destinatario");
				destinatario_pacco.setCognome(keyboard.readLine());	
				
				System.out.printf("Inserire email del destinatario");
				email_destinatario=keyboard.readLine();    
				
				controllo= true;
			}catch(IOException e){
				System.out.printf("Errore inserimento dati destinatario");
				controllo= false;
			}
			
		}

		
		System.out.printf("Si vuole aggiungere una copertura assicurativa?"); //devo aggiungere tipo le possibili coperture??
		
		//devo usare il getPosizione del locker per capire quali ci sono??
		System.out.printf("Controllo disponibilità nel locker scelto");
			String IDlocker_scelto=locker.checkDisponibilita(); //??
			
			/*pagamento.effettuaPagamento;  
			codice_spedizione= qrCode.generaQRcodice();
			
			//TRAFERIMENTO DATI SUL DATABASE
			//nota: dopo il salvataggio, devo pulire i campi
		*/
		
	}

}
