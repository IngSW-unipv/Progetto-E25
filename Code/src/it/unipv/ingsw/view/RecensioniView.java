package it.unipv.ingsw.view;

import java.awt.BorderLayout;

import javax.swing.*;

public class RecensioniView extends JFrame {
	
	public RecensioniView() {
		setTitle("Recensioni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		
		JPanel textAreaPanel = new JPanel(new BorderLayout());
		 JTextArea textArea = new JTextArea("Lascia una recensione sul nostro servizio");
		  textArea.setEditable(false);  // Impedisce che l'utente modifichi il testo
		  textArea.setWrapStyleWord(true);  
		  textArea.setLineWrap(true);
		  add(textAreaPanel, BorderLayout.NORTH); 
	}

		
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	RecensioniView r=new RecensioniView();
	        	r.setVisible(true);
	        }
	    });
		
	}
}
