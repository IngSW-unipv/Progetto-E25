package it.unipv.ingsw.model.spedizione;

//per leggere dati da tastiera:
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;

public class Spedizione {

	public Spedizione() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void AvvioSpedizione() {
		
		InputStreamReader in=new InputStreamReader(System.in);
		BufferedReader keyboard=new BufferedReader(in);
		
		String nome_destinatario;
		boolean controllo=false;

		//richiesta inserimento dati dall'utente
		System.out.printf("Inserire dettagli pacco: dimensione e peso"); //come li faccio a recuperare i dati?
		
		while(controllo==true) { //permetto all'utente di avere piu possibilità di inserire i dati se sbagliati
			try {
				System.out.printf("Inserire dettagli del destinatario");
				nome_destinatario=keyboard.readLine();		
				controllo= true;
			}catch(IOException e){
				System.out.printf("Errore inserimento dati destinatario");
				controllo= false;
				//poi ci capiamo come risolvere sta cosa
			}
			
		}

		//bisogna chiedere i dettagli del mittente o fa il sistema in automatico???
		
		System.out.printf("Si vuole aggiungere una copertura assicurativa?"); //devo aggiungere tipo le possibili coperture??
		
		//devo usare il getPosizione del locker per capire quali ci sono??
		System.out.printf("Controllo disponibilità nel locker scelto"); //??
		/*locker.checkDisponibilita();
			pagamento.effettuaPagamento;  //eccezione la devo gestire io??
			codice_spedizione= qrCode.generaQRcodice();
			
			//trasferimento dati sul database
		
		*/
		
	}

}
