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
import it.unipv.ingsw.view.CancellaAccountView;
import it.unipv.ingsw.view.CarrierView;
import it.unipv.ingsw.view.ItinerarioCarrierView;
import it.unipv.ingsw.view.LogOutView;
import it.unipv.ingsw.view.LoginAdminView;
import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.ModificaProfiloView;
import it.unipv.ingsw.view.PagamentoEsternoView;
import it.unipv.ingsw.view.PagamentoView;
import it.unipv.ingsw.view.PrendiInCaricoSpedizioneView;
import it.unipv.ingsw.view.RicaricaSaldoView;
import it.unipv.ingsw.view.UtenteView;
import it.unipv.ingsw.dao.*;

public class ProfiloUtenteController {

	private Utente model;
	private Carrier carrier;
	private UtenteView view; 
	private MainView mainView;
	private ModificaProfiloView modificaProfiloView;
	private AvviaSpedizioneView avviaSpedizioneView;
	private LogOutView logOutView;
	private CancellaAccountView cancellaAccountView;
	private PagamentoEsternoView pagamentoEsternoView;
	private RicaricaSaldoView ricaricaSaldoView;
	private PagamentoView pagamentoView;
	private ItinerarioCarrierView itinerarioCarrierView;
	private UtenteDAO utenteDAO;
    private Itinerario it;
	private LockerDAO lockerDAO;
	private SpedizioneDAO spedizioneDAO;
	private MatchingService ms;
	private GestoreSpedizioni gs;
	
	public ProfiloUtenteController(Utente model, UtenteView view) {
		this.model=model;
		this.view=view;
		utenteDAO=new UtenteDAO();
		lockerDAO= new LockerDAO();
		modificaProfiloInit();
		avvioSpedizioneInit();
		prendiInCaricoSpedizioneInit();
		logOutInit();
		cancellaAccountInit();
		ricaricaSaldoInit();
		storicoSpedizioniInit();
	}
		
	//costruttore per logout
	public ProfiloUtenteController(Utente model, MainView mainView) {
		ms = new MatchingService();
		gs = new GestoreSpedizioni(ms);
		this.model=model;
		this.mainView=mainView;
		utenteDAO=new UtenteDAO();
		lockerDAO= new LockerDAO();
	}
	
	//costruttore per ricaricaSaldo
	public ProfiloUtenteController(Utente model, PagamentoEsternoView pagamentoEsternoView) {
		ms = new MatchingService();
		gs = new GestoreSpedizioni(ms);
		this.model=model;
		this.pagamentoEsternoView=pagamentoEsternoView;
		utenteDAO=new UtenteDAO();
		lockerDAO= new LockerDAO();
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
					new ProfiloUtenteController(utente, new UtenteView(utente));	//magari schermata d'attesa convalida profilo?
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
	            int selectedIndex;
	            selectedIndex = avviaSpedizioneView.getDimPaccoField().getSelectedIndex();
	            double pesoPacco = Double.parseDouble(avviaSpedizioneView.getPesoPaccoField().getText());
	            String copertura = avviaSpedizioneView.getCoperturaField().getText();
				Destinatario d=new Destinatario(mailDest); //da passare
				Coordinate ci=new Coordinate(lockerInizioX,lockerInizioY); //da passare
				Coordinate cf=new Coordinate(lockerDestinazioneX,lockerDestinazioneY); //da passare
				Size taglia= null;
				  switch (selectedIndex) {
			            case 0:
			            	taglia = Size.S;
			                // Logica per dimensione Small (S)
			                break;
			            case 1:
			            	taglia = Size.M;
			                // Logica per dimensione Medium (M)
			                break;
			            case 2:
			            	taglia = Size.L;
			                // Logica per dimensione Large (L)
			                break;
			            case 3:
			            	taglia =Size.XL;
			                // Logica per dimensione Extra Large (XL)
			                break;
			        }
			        IShippable p=new Pacco(taglia,pesoPacco); //da passare
			        IPuntoDeposito ipi,ipf;
			        System.out.println(ci.getLongitudine());
			        System.out.println(ci.getLatitudine());
			        System.out.println(cf.getLongitudine());
			        System.out.println(cf.getLatitudine()); //OK
			        ipi=lockerDAO.selectPuntoDeposito(ci);
			        ipf=lockerDAO.selectPuntoDeposito(cf);
			        ms = new MatchingService();
					gs = new GestoreSpedizioni(ms);
					
					
				try {
					// AVVIA SPEDIZIONE
					Spedizione s1;
					s1=gs.avvioSpedizione(model, ipi, ipf, d, p); //avvioSpedizione da modificare
					System.out.println(s1.getDestinatario());
					avviaSpedizioneView.setVisible(false);
					view.setVisible(false);
					int costoPuntiApp=10;
					double costoSoldi=2.5;
					new PagamentoController(model, new PagamentoView(costoSoldi,costoPuntiApp,model),s1,costoSoldi, costoPuntiApp);	//costo spedizione effettivo, metodo Zein
					
				} catch (Exception e) {
					//JOptionPane.showMessageDialog(model, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
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
	             
	                
	                itinerarioCarrierView.setVisible(false);
	                //view.setVisible(false);
	                
//		                new ItinerarioCarrierController(itinerarioCarrierView, carrier);
//		                new ProfiloUtenteController(model, view);
	                
	                new CarrierController(new CarrierView(), carrier);
	                
	                
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(itinerarioCarrierView, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	}

	private void logOutInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				logOutView = new LogOutView();
				okLogOutButton();
			}
		};
		view.getLogout().addActionListener(listener); 
	} 
	
	private void okLogOutButton() {
		ActionListener okLogOutListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				new MainController(new MainView());	//ritorna a view iniziale	
			}
		};
		logOutView.getConfirmButton().addActionListener(okLogOutListener); //bottone di logout 
	}
	
	private void cancellaAccountInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				cancellaAccountView = new CancellaAccountView();
				okCancellaAccountButton();
			}
		};
		view.getCancellaAccount().addActionListener(listener); 
	} 
	
	private void okCancellaAccountButton() {
		ActionListener okCancellaAccountListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				boolean esitoCancellazione;
				esitoCancellazione=utenteDAO.deleteUtente(model);
				model=null;
				if(esitoCancellazione)
					new MainController(new MainView());	//ritorna a view iniziale
			}
		};
		cancellaAccountView.getConfirmButton().addActionListener(okCancellaAccountListener); //bottone di cancellazioneAccount 
	}
	
	private void ricaricaSaldoInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				ricaricaSaldoView = new RicaricaSaldoView();
				okRicaricaSaldoButton();
			}
		};
		view.getRicarica().addActionListener(listener); 
	} 
	
	private void okRicaricaSaldoButton() {
		ActionListener okRicaricaListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
					double importoInserito=0.0;
					importoInserito= ricaricaSaldoView.getAmount();
					new ProfiloUtenteController(model, new PagamentoEsternoView(importoInserito));
			}
		};
		ricaricaSaldoView.getConfirmButton().addActionListener(okRicaricaListener); //bottone di ricarica saldo
	}
	
	private void storicoSpedizioniInit() {
		ActionListener listener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				ricaricaSaldoView = new RicaricaSaldoView();
				okSpedizioniButton();
			}
		};
		view.getRicarica().addActionListener(listener); 
	} 
	
	private void okSpedizioniButton() {
		ActionListener okRicaricaListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
					double importoInserito=0.0;
					importoInserito= ricaricaSaldoView.getAmount();
					new ProfiloUtenteController(model, new PagamentoEsternoView(importoInserito));
			}
		};
		ricaricaSaldoView.getConfirmButton().addActionListener(okRicaricaListener); //bottone di ricarica saldo
	}
}