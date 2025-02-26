package it.unipv.ingsw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.*;

public class RecensioniView extends JFrame {
	
	private String recensione;
	
	public RecensioniView() {
		
		setTitle("Recensioni");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
	
		JLabel label=new JLabel("Lascia una recensione sul nostro servizio");
		add(label, BorderLayout.PAGE_START);
		
		JTextArea casella_testo= new JTextArea();
		casella_testo.setBackground(Color.LIGHT_GRAY);
		add(casella_testo,BorderLayout.CENTER );
		recensione=casella_testo.getText();
		
		JButton button=new JButton("Invia recensione");
		add(button,BorderLayout.PAGE_END);
		
		 
		setVisible(true);
		  
	}
	
	public String getRecensione() {
		return recensione;
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
