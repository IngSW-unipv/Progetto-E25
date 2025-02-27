package it.unipv.ingsw.controller;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import it.unipv.ingsw.recensioni.*;
import it.unipv.ingsw.view.*;
import it.unipv.ingsw.dao.*;

public class RecensioniController {
	
//	private Recensioni model;
	private String recensione;
	private int costo_spedizione;
	private int soddisfazione_generale;
	private int tempo_spedizione;
	private int semplicita;
	private int compenso;
	private int affidabilita;
	private int rapidita_consegna;
	private RecensioniView recensioniView;
	private RecensioniDAO recensioniDAO;

	public RecensioniController() {
		
	}

	
	public void invioRecensione() {
		ActionListener invioRecensioneListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			
			public void manageAction() {
				String recensione_scritta=recensioniView.getRecensione().getText();
				int punteggio_comodita_locker=Integer.valueOf(recensioniView.getPunteggioComoditaLocker().getText()); //da modificare
				int punteggio_costo_spedizione= Integer.valueOf(recensioniView.getPunteggioCostoSpedizione().getText());
				int punteggio_soddisfazione_generale=Integer.valueOf(recensioniView.getPunteggioSoddisfazioneGenerale().getText());
				int punteggio_tempo_spedizione=Integer.valueOf(recensioniView.getPunteggioTempoSpedione().getText());
				int punteggio_semplicita=Integer.valueOf(recensioniView.getPunteggioSemplicita().getText());
				int punteggio_compenso=Integer.valueOf(recensioniView.getPunteggioCompenso().getText());
				int punteggio_affidabilita=Integer.valueOf(recensioniView.getPunetggioAffidabilita().getText());
				int punteggio_rapidita_consegna=Integer.valueOf(recensioniView.getPunetggioRapiditaConsegna().getText());
				
				
				try {
					Recensioni r=new Recensioni(recensione_scritta,punteggio_comodita_locker, punteggio_costo_spedizione,punteggio_soddisfazione_generale, punteggio_tempo_spedizione,punteggio_semplicita, punteggio_compenso, punteggio_affidabilita,punteggio_rapidita_consegna );
					recensioniDAO.addRecensione(r);
					
					recensioniView.setVisible(false);
				}catch(Exception e) {
					System.out.printf("Perche non funzioni");
					
				}
			}
		};
		
		recensioniView.getButton().addActionListener(invioRecensioneListener);
		
	
	}//fine metodo
	
	
}
