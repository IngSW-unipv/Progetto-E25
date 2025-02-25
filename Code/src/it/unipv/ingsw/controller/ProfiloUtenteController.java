package it.unipv.ingsw.controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.ingsw.dao.UtenteDAO;
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
	Carrier carrier;
	private UtenteView view; 
	private ModificaProfiloView modificaProfiloView;
	private AvviaSpedizioneView avviaSpedizioneView;
	private ItinerarioCarrierView itinerarioCarrierView;
	private UtenteDAO utenteDAO;
	
	public ProfiloUtenteController(Utente model, UtenteView view) {
		this.model=model;
		this.view=view;
		utenteDAO=new UtenteDAO();
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
	            String password = String.valueOf(modificaProfiloView.getPasswordField().getPassword());
	            String nome = modificaProfiloView.getNomeField().getText();
	            String cognome = modificaProfiloView.getCognomeField().getText();
	            String numeroTelefono = modificaProfiloView.getNumeroTelefonoField().getText();
	            String dataNascita = modificaProfiloView.getDataNascitaField().getText();
	            String indirizzoCivico = modificaProfiloView.getIndirizzoCivicoField().getText();
	            String fotoDocumento = modificaProfiloView.getFotoDocumentoField().getText();
				try {
					Utente utente;
					utente= utenteDAO.aggiornamentoUtente(model, password,nome,cognome,numeroTelefono,dataNascita,indirizzoCivico,fotoDocumento);
					modificaProfiloView.setVisible(false);
					view.setVisible(false);
					new ProfiloUtenteController(utente, new UtenteView());	//magari schermata d'attesa convalida profilo?
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
//				System.out.println("-4");
				itinerarioCarrierView = new ItinerarioCarrierView();
//				System.out.println("-3");
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
				
				try {

					carrier = new Carrier(model.getMail(), model.getPassword(), model.getNome(), model.getCognome(), model.getNumeroTelefono(), model.getIndirizzoCivico(), model.getDataNascita(), model.getFotoDocumento());
					
					itinerarioCarrierView.setVisible(false);

					view.setVisible(false);

					//new ItinerarioCarrierController(new ItinerarioCarrierView(), carrier);

					new ProfiloUtenteController(model, view);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(itinerarioCarrierView, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					
				}
			
			}
		};
		itinerarioCarrierView.getSendButton().addActionListener(okPrendiInCaricoListener); //bottone di conferma di presa in carico di spedizione
	}
}