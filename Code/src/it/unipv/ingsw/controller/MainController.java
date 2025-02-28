package it.unipv.ingsw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;

import it.unipv.ingsw.dao.AdminDAO;
import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.exceptions.*;
import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.RegistrazioneView;
import it.unipv.ingsw.view.UtenteView;
import it.unipv.ingsw.view.LoginUtenteView;
import it.unipv.ingsw.view.AdminView;
import it.unipv.ingsw.view.LoginAdminView;


public class MainController {
	MainView mainView;
	LoginUtenteView loginUtenteView;
	RegistrazioneView regView;
	LoginAdminView loginAdminView;
	UtenteDAO utenteDAO;
	AdminDAO adminDAO;
	Utente utente;
	
	public MainController(MainView mainView) {
		this.mainView=mainView;
		utenteDAO=new UtenteDAO();
		adminDAO=new AdminDAO();
		utenteInit();
		adminInit();
		
	}
	
	private void utenteInit() {
		ActionListener utenteListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				int r = mainView.logOrReg();
				if (r==1) {		
					loginUtenteView = new LoginUtenteView();
					okLoginUtenteButton();
				} else if(r==0) {
					regView = new RegistrazioneView();
					okRegistrationUtenteButton();
				}
			}
		};
		mainView.getUtenteButton().addActionListener(utenteListener);
	} 
	
	private void adminInit() {
		ActionListener adminListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				
				loginAdminView = new LoginAdminView();
				okAdminButtonInit();
			}
		};
		mainView.getAdminButton().addActionListener(adminListener);
	} 
	
	private void okLoginUtenteButton() {
		ActionListener okListener=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}
			private void manageAction() {
				String email = loginUtenteView.getEmailField().getText();
	            String password = String.valueOf(loginUtenteView.getPasswordField().getPassword());
				try {
					utente = utenteDAO.getUtenteByEmailPassword(email, password);
					System.out.println(utente.toString());
					loginUtenteView.setVisible(false);
					mainView.setVisible(false);
					utenteDAO.getSaldoEPuntiAppByUtente(utente); //aggiorna saldo e restituisce saldo aggiornato
					new ProfiloUtenteController(utente, new UtenteView(utente));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(loginUtenteView, "Campi errati", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		};
		loginUtenteView.getConfirmButton().addActionListener(okListener);
	}
	
	private void okRegistrationUtenteButton() {
        ActionListener okListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    manageAction();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(regView, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
	
            private void manageAction() throws EmptyFieldException, AccountAlreadyExistsException, SQLException, IOException, WrongFieldException {
            	regView.setAlwaysOnTop(false);
            	Utente utente = new Utente();        
                utente.registrazione(regView.getMailField().getText(),String.valueOf(regView.getPasswordField().getPassword()),regView.getNomeField().getText(),regView.getCognomeField().getText(),
                		regView.getNumeroTelefonoField().getText(),regView.getDataNascitaField().getText(),regView.getIndirizzoCivicoField().getText(),regView.getFotoDocumentoField().getText());
                regView.setVisible(false);
                loginUtenteView = new LoginUtenteView();
                okLoginUtenteButton();
                loginUtenteView.setVisible(true);
            }
        };
        regView.getConfirmButton().addActionListener(okListener);
    }
	
	private void okAdminButtonInit() {
	    ActionListener okListener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	                manageAction();
	            } catch (Exception e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(loginAdminView, e1.getMessage(), "Errore Admin", JOptionPane.ERROR_MESSAGE);
	            }
	        } 
	        private void manageAction() throws WrongAdminException {
	        	int matricola = Integer.parseInt(loginAdminView.getTextId().getText());
	        	String email = loginAdminView.getEmailField().getText();
	            String password = String.valueOf(loginAdminView.getPasswordField().getPassword()); 
				try {
					Admin admin;
					admin = adminDAO.getAdmin(matricola,email,password);
					loginAdminView.setVisible(false);
					mainView.setVisible(false);
					new ProfiloAdminController(admin, new AdminView());
	
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(loginAdminView, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
	                return; 
	            }
	        }
	    };
	    loginAdminView.getConfirmButton().addActionListener(okListener);
	}
	
}

	