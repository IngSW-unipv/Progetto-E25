package it.unipv.ingsw.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unipv.ingsw.controller.MainController;

public class MainView extends JFrame {
	    private JButton bottoneUtente;
	    private JButton bottoneAdmin;

	    public MainView() {
	    	
	        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	        JPanel panel = new JPanel();

	        JLabel l = new JLabel("Benvenuto in ShipUp");
	        bottoneUtente = new JButton("Login utente");
	        bottoneAdmin = new JButton("Login operatore admin");
	        panel.add(l);
	        panel.add(bottoneUtente);
	        panel.add(bottoneAdmin);
	        this.getContentPane().add(panel);
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); 
	        setVisible(true);
	    }
	    
	    public int logOrReg() {
	    	String[] option= { "Registrati", "Login" };
	    	int r= JOptionPane.showOptionDialog(this, "Utente", "Login o Registrazione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
	    	return r;
	    }

		public JButton getUtenteButton() {
			return bottoneUtente;
		}

		public void setUtenteButton(JButton clientButton) {
			this.bottoneUtente = clientButton;
		}

		public JButton getAdminButton() {
			return bottoneAdmin;
		}

		public void setAdminButton(JButton operatorButton) {
			this.bottoneAdmin = operatorButton;
		}
}
