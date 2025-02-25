package it.unipv.ingsw.controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.transazioni.IPagamento;
import it.unipv.ingsw.model.transazioni.Pagamento;
import it.unipv.ingsw.model.transazioni.PagamentoCarta;
import it.unipv.ingsw.model.transazioni.PagamentoSaldo;
import it.unipv.ingsw.model.transazioni.PagamentoStrategyFactory;
import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.AvviaSpedizioneView;
import it.unipv.ingsw.view.ItinerarioCarrierView;
import it.unipv.ingsw.view.LoginAdminView;
import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.ModificaProfiloView;
import it.unipv.ingsw.view.PrendiInCaricoSpedizioneView;
import it.unipv.ingsw.view.UtenteView;


public class ProfiloUtenteController {

	private Utente model;
	private UtenteView view; 
	private ModificaProfiloView modificaProfiloView;
	private AvviaSpedizioneView avviaSpedizioneView;
	private ItinerarioCarrierView itinerarioCarrierView;
	
	public ProfiloUtenteController(Utente model, UtenteView view) {
		this.model=model;
		this.view=view;
		modificaProfiloInit();
		avvioSpedizioneInit();
		prendiInCaricoSpedizioneInit();
	}
	
	private void modificaProfiloInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				modificaProfiloView = new ModificaProfiloView();
				okModificaProfiloButton();
			}
		};
		view.getModificaProfilo().addActionListener(listener); 
	} 
	
	private void okModificaProfiloButton() {
		ActionListener okModificaListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				Utente utente = new Utente(); //OK
				try {
					utente.modificaProfilo(modificaProfiloView.getMailField().getText(), String.valueOf(modificaProfiloView.getPasswordField().getPassword()), modificaProfiloView.getNomeField().getText(),modificaProfiloView.getCognomeField().getText(), modificaProfiloView.getNumeroTelefonoField().getText(), modificaProfiloView.getDataNascitaField().getText(), modificaProfiloView.getIndirizzoCivicoField().getText(), modificaProfiloView.getFotoDocumentoField().getText());
					modificaProfiloView.setVisible(false);
					view.setVisible(false);
					//new ProfiloUtenteController(new Utente(), new UtenteView());	//magari schermata d'attesa convalida profilo?
				} catch (Exception e) {
					JOptionPane.showMessageDialog(modificaProfiloView, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		};
		modificaProfiloView.getConfirmButton().addActionListener(okModificaListener); //bottone di conferma moodifiche 
	}
	
	private void avvioSpedizioneInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				avviaSpedizioneView = new AvviaSpedizioneView();
				okAvviaSpedizioneButton();
			}
		};
		view.getAvviaSpedizione().addActionListener(listener); 
	} 
	
	private void okAvviaSpedizioneButton() {
		ActionListener okAvviaSpedizioneListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				Spedizione sp = new Spedizione(); //OK
				try {
					// AVVIA SPEDIZIONE
					sp.avvioSpedizione(model, null, model);
					model.setVisible(false);
					view.setVisible(false);
					//new ProfiloUtenteController(new Utente(), new UtenteView());	//ritorno a schermata del profilo
				} catch (Exception e) {
					JOptionPane.showMessageDialog(model, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		};
		avviaSpedizioneView.getConfirmButton().addActionListener(okAvviaSpedizioneListener); //bottone di conferma di avvio spedizione 
	}
	
	private void prendiInCaricoSpedizioneInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				itinerarioCarrierView = new ItinerarioCarrierView();
				okPrendiInCaricoSpedizioneButton();
			}
		};
		view.getPrendiInCaricoSpedizioneButton().addActionListener(listener); 
	} 
	
	private void okPrendiInCaricoSpedizioneButton() {
		ActionListener okPrendiInCaricoListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				MatchingService m = new MatchingService();
				GestoreSpedizioni gs = new GestoreSpedizioni(m);
				try {
					// AVVIA SPEDIZIONE
					Carrier carrier = (Carrier)model;
					gs.presaInCaricoSpedizione(carrier, null);
					itinerarioCarrierView.setVisible(false);
					view.setVisible(false);
					new ItinerarioCarrierController(new ItinerarioCarrierView(), carrier);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(itinerarioCarrierView, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		};
		itinerarioCarrierView.getSendButton().addActionListener(okPrendiInCaricoListener); //bottone di conferma di presa in carico di spedizione
	}
}