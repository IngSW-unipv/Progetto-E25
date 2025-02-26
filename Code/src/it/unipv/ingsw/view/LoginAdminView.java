package it.unipv.ingsw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginAdminView extends JFrame {
	private JPasswordField passwordField;
	private JTextField textId;
	private JTextField emailField;
	private JButton confirm;

	public LoginAdminView() {
		setTitle("Login Admin");
		setVisible(true);
        setSize(400, 150);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(2, 1));
        add(panel);
        
        
        panel.add(new JLabel("ID:"));
        textId = new JTextField(10);
        panel.add(textId);
        
        panel.add(new JLabel("Email:"));
        emailField = new JTextField(10);
        panel.add(emailField);
        
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(10);
        panel.add(passwordField);
        
        
        confirm=new JButton("Conferma");
        panel.add(confirm);
        
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getTextId() {
		return textId;
	}

	public void setTextId(JTextField textId) {
		this.textId = textId;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	public JButton getConfirmButton() {
		return confirm;
	}

	public void setConfirmButton(JButton confirm) {
		this.confirm = confirm;
	}
}