package it.unipv.ingsw.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import it.unipv.ingsw.exceptions.*;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.RegistrazioneView;
import it.unipv.ingsw.view.LoginUtenteView;
import it.unipv.ingsw.view.LoginAdminView;


public class MainController {
	MainView mainView;
	LoginUtenteView loginUtenteView;
	RegistrazioneView regView;
	LoginAdminView loginAdminView;
	
	public MainController(MainView mainView) {
		this.mainView=mainView;
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
				Utente utente = new Utente(); //O SuperUser?
				try {
					utente.loginUtente(loginUtenteView.getEmailField().getText(), String.valueOf(loginUtenteView.getPasswordField().getPassword()));
					loginUtenteView.setVisible(false);
					mainView.setVisible(false);
						
				} catch (Exception e) {
					JOptionPane.showMessageDialog(loginUtenteView, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
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
	
            private void manageAction() throws EmptyFieldException, AccountAlreadyExistsException, SQLException, IOException {
            	SerialBlob fotoDocumento = null;
            	regView.setAlwaysOnTop(false);
            	Utente utente = new Utente();        
            	ImageIcon imageIcon = (ImageIcon) regView.getFotoDocumentoField().getIcon();  // Supponiamo che la JLabel abbia un ImageIcon

            	// Verifica che l'immagine esista
            	if (imageIcon != null) {
            	    Image image = imageIcon.getImage();  // Ottieni l'oggetto Image dall'ImageIcon
            	    
            	    // Converti l'immagine in un array di byte
            	    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            	        // Scrivi l'immagine come PNG (o qualsiasi altro formato) nel ByteArrayOutputStream
            	        ImageIO.write((java.awt.image.BufferedImage) image, "png", baos);  
            	        byte[] imageBytes = baos.toByteArray();
            	        
            	        // Crea un Blob dai byte dell'immagine
            	        fotoDocumento = new javax.sql.rowset.serial.SerialBlob(imageBytes);
            	    }
            	}    
                utente.registrazione(regView.getEmailField().getText(),String.valueOf(regView.getPasswordField().getPassword()),regView.getNameField().getText(),regView.getSurnameField().getText(),
                		regView.getNumeroTelefonoField().getText(),regView.getDataNascitaField().getText(),regView.getIndirizzoCivicoField().getText(),fotoDocumento);
                regView.setVisible(false);
               // JOptionPane.showMessageDialog(regView,null, "Registrazione effettuata con successo!", JOptionPane.OK_OPTION);
                loginUtenteView = new LoginUtenteView();
                okLoginUtenteButton();
                loginUtenteView.setVisible(true);
            }
        };
        regView.getConfirmButton().addActionListener(okListener);
    }
	
	
	
}
	