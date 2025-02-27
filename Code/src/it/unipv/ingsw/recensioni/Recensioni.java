package it.unipv.ingsw.recensioni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Recensioni {

	private String recensione_scritta;
	private int comodita_locker;
	private int costo_spedizione;
	private int soddisfazione_generale;
	private int tempo_spedizione;
	private int semplicita;
	private int compenso;
	private int affidabilita;
	private int rapidita_consegna;
	
	public Recensioni(String testo,int punteggio_comodita_locker, int punteggio_costo, int punteggio_soddisfazione, int punteggio_tempo_sp, int punteggio_semplicita, int punteggio_compenso, int punteggio_affidabilita, int punteggio_rapidita_consegna) {
		this.recensione_scritta=testo;
		this.comodita_locker=punteggio_comodita_locker;
		this.costo_spedizione=punteggio_costo;
		this.soddisfazione_generale=punteggio_soddisfazione;
		this.tempo_spedizione=punteggio_tempo_sp;
		this.semplicita=punteggio_semplicita;
		this.compenso=punteggio_compenso;
		this.affidabilita=punteggio_affidabilita;
		this.rapidita_consegna=punteggio_rapidita_consegna;
	}
	
	public void scrivi() {
		System.out.printf("Vuoi lasciare una recensione scritta?");
		
		InputStreamReader in= new InputStreamReader(System.in);
		BufferedReader keyboard=new BufferedReader(in);
		String name;
		try {
			name=keyboard.readLine();
			recensione_scritta=name;
		}catch(IOException e){
			System.out.printf("Errore durante inserimento recensione");	
		}
	}
	
	public void vautaComoditaLocker(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			comodita_locker=punteggio;
		}
		
	}
	
	public void vautaCostoSpedizione(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			costo_spedizione=punteggio;
		}
		
	}
	
	public void vautaSoddisfazioneGenerale(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			soddisfazione_generale=punteggio;
		}
		
	}
	public void vautaTempoSpedizione(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			tempo_spedizione=punteggio;
		}
	
	}
	
	public void valutaSemplicita(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			semplicita=punteggio;
		}
	}
	
	public void valutaCompenso(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			compenso=punteggio;
		}
	}
	
	public void valutaAffidabilita(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			affidabilita=punteggio;
		}
	}
	
	public void valutaRapiditaConsegna(int punteggio) {
		if(punteggio<0 && punteggio>5) {
			System.out.printf("Errore inserimento");
		}else {
			rapidita_consegna=punteggio;
		}
	}
	
	public String getRecensione() {
		return recensione_scritta;
	}
	public int getPunteggioCostoSpedizione() {
		return costo_spedizione;
	}
	
	public int getPunteggioComoditaLocker() {
		return comodita_locker;
	}
	public int getPunteggioSoddisfazioneGenerale() {
		return soddisfazione_generale;
	}
	public int getPunteggioTempoSpedione() {
		return tempo_spedizione;
	}
	public int getPunteggioSemplicita() {
		return semplicita; 
	}
	public int getPunteggioCompenso() {
		return compenso;
	}
	public int getPunetggioAffidabilita() {
		return affidabilita;
	}
	
	public int getPunetggioRapiditaConsegna() {
		return rapidita_consegna;
	}
	
	
	public void setRecensione(String recensione) {
		this.recensione_scritta=recensione;
	}

}
