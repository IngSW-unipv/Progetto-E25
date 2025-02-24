package it.unipv.ingsw.view;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RegistrazioneView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel registrationPanel;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JTextField dataNascitaField;
	private JTextField numeroTelefonoField;
	private JTextField indirizzoCivicoField;
	private JLabel fotoDocumentoField;
	private JPasswordField passwordField;
	private JButton confirmButton;
	
	public RegistrazioneView() {

		setTitle("Registrazione");
		setSize(400, 400);
		

		registrationPanel = new JPanel(new GridLayout(9, 2, 10, 10));
		add(registrationPanel);
		
		registrationPanel.add(new JLabel("  Nome:"));
		nameField = new JTextField();
		registrationPanel.add(nameField);
	
		registrationPanel.add(new JLabel("  Cognome:"));
		surnameField = new JTextField();
		registrationPanel.add(surnameField);
	

		registrationPanel.add(new JLabel("  Mail:"));
		emailField = new JTextField();
		registrationPanel.add(emailField);
		
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
		
		registrationPanel.add(new JLabel("  Foto di un documento(PNG):"));
		fotoDocumentoField = new JLabel();
		registrationPanel.add(fotoDocumentoField);

		confirmButton = new JButton("Conferma");

		registrationPanel.add(confirmButton);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setVisible(true);
		
	}


	public JTextField getNameField() {
		return nameField;
	}


	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}


	public JTextField getSurnameField() {
		return surnameField;
	}


	public void setSurnameField(JTextField surnameField) {
		this.surnameField = surnameField;
	}


	public JTextField getEmailField() {
		return emailField;
	}


	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
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


	public JLabel getFotoDocumentoField() {
		return fotoDocumentoField;
	}


	public void setFotoDocumentoField(JLabel fotoDocumentoField) {
		this.fotoDocumentoField = fotoDocumentoField;
	}


	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
	}



}
