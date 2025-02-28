package it.unipv.ingsw.exceptions;

public class NessunaSpedizioneCompatibileException extends Exception {
	
	public NessunaSpedizioneCompatibileException() {
		super("Nessuna spedizione compatibile con l'itinerario inserito");
	}

}
