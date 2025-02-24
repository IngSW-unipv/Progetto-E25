package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.*;

public class LoginUtenteView extends JFrame {

	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton confirmButton;
	
	public LoginUtenteView() {

		setTitle("Login");
        setSize(400, 300);
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(loginPanel);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        loginPanel.add(fieldsPanel, BorderLayout.CENTER);

        fieldsPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        fieldsPanel.add(emailField);

        fieldsPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        fieldsPanel.add(passwordField);

        confirmButton = new JButton("Conferma");
        
        loginPanel.add(confirmButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
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

	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
	}
	


}