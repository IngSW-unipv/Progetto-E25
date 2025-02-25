package it.unipv.ingsw.view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RegistrazioneView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel registrationPanel;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField mailField;
	private JTextField dataNascitaField;
	private JTextField numeroTelefonoField;
	private JTextField indirizzoCivicoField;
	private JTextField fotoDocumentoField;
	private JPasswordField passwordField;
	
	private JButton confirmButton;
	
	public RegistrazioneView() {

		setTitle("Registrazione");
		setSize(400, 400);
		

		registrationPanel = new JPanel(new GridLayout(9, 2, 10, 10));
		add(registrationPanel);
		
		registrationPanel.add(new JLabel("  Nome:"));
		nomeField = new JTextField();
		registrationPanel.add(nomeField);
	
		registrationPanel.add(new JLabel("  Cognome:"));
		cognomeField = new JTextField();
		registrationPanel.add(cognomeField);
	

		registrationPanel.add(new JLabel("  Mail:"));
		mailField = new JTextField();
		registrationPanel.add(mailField);
		
		registrationPanel.add(new JLabel("  Password:"));
		passwordField = new JPasswordField();
		registrationPanel.add(passwordField);
		
		registrationPanel.add(new JLabel("  Data di nascita:"));
		dataNascitaField = new JTextField();
		registrationPanel.add(dataNascitaField);
		
		registrationPanel.add(new JLabel("  Numero di telefono:"));
		numeroTelefonoField = new JTextField();
		registrationPanel.add(numeroTelefonoField);
		
		registrationPanel.add(new JLabel("  Indirizzo civico:"));
		indirizzoCivicoField = new JTextField();
		registrationPanel.add(indirizzoCivicoField);
		
		registrationPanel.add(new JLabel("  Percorso documento (PNG):"));
		fotoDocumentoField = new JTextField();
		registrationPanel.add(fotoDocumentoField);
       

		confirmButton = new JButton("Conferma");
		confirmButton.setBackground(Color.GREEN);
		registrationPanel.add(confirmButton);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);
		
	}


	public JTextField getNomeField() {
		return nomeField;
	}


	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}


	public JTextField getCognomeField() {
		return cognomeField;
	}


	public void setCognomeField(JTextField cognomeField) {
		this.cognomeField = cognomeField;
	}


	public JTextField getMailField() {
		return mailField;
	}


	public void setMailField(JTextField mailField) {
		this.mailField = mailField;
	}


	public JPasswordField getPasswordField() {
		return passwordField;
	}


	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}


	public JButton getConfirmButton() {
		return confirmButton;
	}
	
	
	public JTextField getNumeroTelefonoField() {
		return numeroTelefonoField;
	}


	public void setNumeroTelefonoField(JTextField numeroTelefonoField) {
		this.numeroTelefonoField = numeroTelefonoField;
	}


	public JTextField getIndirizzoCivicoField() {
		return indirizzoCivicoField;
	}
	

	public void setIndirizzoCivicoField(JTextField indirizzoCivicoField) {
		this.indirizzoCivicoField = indirizzoCivicoField;
	}
	
	
	public JTextField getDataNascitaField() {
		return dataNascitaField;
	}


	public void setDataNascitaField(JTextField dataNascitaField) {
		this.dataNascitaField = dataNascitaField;
	}


	public JTextField getFotoDocumentoField() {
		return fotoDocumentoField;
	}


	public void setFotoDocumentoField(JTextField fotoDocumentoField) {
		this.fotoDocumentoField = fotoDocumentoField;
	}


	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
	}



}
