package it.unipv.ingsw.controller;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import it.unipv.ingsw.dao.LockerDAO;
import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.Itinerario;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.shippable.Size;
import it.unipv.ingsw.model.transazioni.IPagamento;
import it.unipv.ingsw.model.transazioni.Pagamento;
import it.unipv.ingsw.model.transazioni.PagamentoCarta;
import it.unipv.ingsw.model.transazioni.PagamentoSaldo;
import it.unipv.ingsw.model.transazioni.PagamentoStrategyFactory;
import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.model.utenze.Destinatario;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.AvviaSpedizioneView;
import it.unipv.ingsw.view.CarrierView;
import it.unipv.ingsw.view.ItinerarioCarrierView;
import it.unipv.ingsw.view.LoginAdminView;
import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.ModificaProfiloView;
import it.unipv.ingsw.view.PagamentoView;
import it.unipv.ingsw.view.PrendiInCaricoSpedizioneView;
import it.unipv.ingsw.view.UtenteView;
import it.unipv.ingsw.dao.*;

public class ProfiloUtenteController {

	private Utente model;
	private Carrier carrier;
	private UtenteView view; 
	private ModificaProfiloView modificaProfiloView;
	private AvviaSpedizioneView avviaSpedizioneView;
	private PagamentoView pagamentoView;
	private ItinerarioCarrierView itinerarioCarrierView;
	private UtenteDAO utenteDAO;
    private Itinerario it;
	private LockerDAO lockerDAO;
	private SpedizioneDAO spedizioneDAO;
	
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
					if (utente!= null) {
						utente.notifyObservers();
					}
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
				String mailDest = avviaSpedizioneView.getMailDestField().getText();
	            double lockerInizioX = Double.parseDouble(avviaSpedizioneView.getLockerInizioXField().getText());
	            double lockerInizioY = Double.parseDouble(avviaSpedizioneView.getLockerInizioYField().getText());
	            double lockerDestinazioneX = Double.parseDouble(avviaSpedizioneView.getLockerDestinazioneXField().getText());
	            double lockerDestinazioneY = Double.parseDouble(avviaSpedizioneView.getLockerDestinazioneYField().getText());
	            JComboBox dimPacco = avviaSpedizioneView.getDimPaccoField(); //menù a tendina
	            double pesoPacco = Double.parseDouble(avviaSpedizioneView.getPesoPaccoField().getText());
	            String copertura = avviaSpedizioneView.getCoperturaField().getText();
				
	            MatchingService ms=new MatchingService();
				GestoreSpedizioni gs = new GestoreSpedizioni(ms);
				Destinatario d=new Destinatario(mailDest); 
				Coordinate ci=new Coordinate(lockerInizioX,lockerInizioY);
				Coordinate cf=new Coordinate(lockerDestinazioneX,lockerDestinazioneY);
				Size selectedDim = (Size) ((JComboBox) dimPacco).getSelectedItem();		
			        switch (selectedDim) {
			            case S:
			                // Logica per dimensione Small (S)
			                break;
			            case M:
			                // Logica per dimensione Medium (M)
			                break;
			            case L:
			                // Logica per dimensione Large (L)
			                break;
			            case XL:
			                // Logica per dimensione Extra Large (XL)
			                break;
			        }
				IShippable p=new Pacco(selectedDim,pesoPacco);
				IPuntoDeposito ipi,ipf;
				ipi=lockerDAO.selectPuntoDeposito(ci);
				ipf=lockerDAO.selectPuntoDeposito(cf);
				Spedizione s=new Spedizione(); //fittizia, sbagliato
				try {
					// AVVIA SPEDIZIONE
					s=gs.avvioSpedizione(model, ipi, ipf, d, s, p); //avvioSpedizione da modificare
					avviaSpedizioneView.setVisible(false);
					view.setVisible(false);
					new PagamentoController(model,s, new PagamentoView(lockerInizioX));	//costo spedizione effettivo, metodo Zein
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(model, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
				spedizioneDAO.addSpedizione(s); //non so se si fa cosi?
			
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
	    // Aggiungo il listener al bottone "Send" della stessa view
	    itinerarioCarrierView.getSendButton().addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	                //creo il carrier usando i dati di model
	                carrier = new Carrier(model.getMail(), model.getPassword(), model.getNome(), model.getCognome(), 
	                                        model.getNumeroTelefono(), model.getIndirizzoCivico(), model.getDataNascita(), 
	                                        model.getFotoDocumento());
	                
	                //leggo i dati dall'itinerarioCarrierView (quella in cui l'utente ha inserito i dati)
	                double startX = Double.parseDouble(itinerarioCarrierView.getStartXField().getText());
	                double startY = Double.parseDouble(itinerarioCarrierView.getStartYField().getText());
	                double endX = Double.parseDouble(itinerarioCarrierView.getEndXField().getText());
	                double endY = Double.parseDouble(itinerarioCarrierView.getEndYField().getText());
	                
	                
	                System.out.println("Punto di partenza: (" + startX + ", " + startY + ")");
	                System.out.println("Punto di arrivo: (" + endX + ", " + endY + ")");
	                
	                it = new Itinerario(new Coordinate(startX,startY), new Coordinate(endX,endY));
	                
	                carrier.setItinerario(it);
	                
	                //System.out.println("latitudine inizio: "+carrier.getItinerario().getInizio().getLatitudine());
	                
	                itinerarioCarrierView.setVisible(false);
	                //view.setVisible(false);
	                
//	                new ItinerarioCarrierController(itinerarioCarrierView, carrier);
//	                new ProfiloUtenteController(model, view);
	                
	                new CarrierController(new CarrierView(), carrier);
	                
	                
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(itinerarioCarrierView, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	}


}