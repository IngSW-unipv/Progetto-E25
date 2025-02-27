package it.unipv.ingsw.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.*;

public class RecensioniView extends JFrame {
	
//	private JPanel panel;
	private JTextArea recensione;
	private JTextArea costo_spedizione;
	private JTextArea soddisfazione_generale;
	private JTextArea tempo_spedizione;
	private JTextArea semplicita;
	private JTextArea compenso;
	private JTextArea affidabilita;
	private JTextArea rapidita_consegna;
	
	public RecensioniView() {
		
		setTitle("Recensioni");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		
		
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		
		JLabel label=new JLabel("Lascia una recensione sul nostro servizio");
		panel.add(label);
		
		recensione= new JTextArea();
		recensione.setBackground(Color.LIGHT_GRAY);
		panel.add(recensione);
		
		JLabel label2=new JLabel("Punteggio soddisfazione generale");
		panel.add(label2);
		soddisfazione_generale= new JTextArea();
		soddisfazione_generale.setBackground(Color.LIGHT_GRAY);
		panel.add(soddisfazione_generale);
		
		JLabel label3=new JLabel("Punteggio costo spedizione");
		panel.add(label3);
		costo_spedizione= new JTextArea();
		costo_spedizione.setBackground(Color.LIGHT_GRAY);
		panel.add(costo_spedizione);
		
		JLabel label4=new JLabel("Punteggio tempo spedizione");
		panel.add(label4);
		tempo_spedizione= new JTextArea();
		tempo_spedizione.setBackground(Color.LIGHT_GRAY);
		panel.add(tempo_spedizione);
		
		JLabel label5=new JLabel("Punteggio semplicita");
		panel.add(label5);
		semplicita= new JTextArea();
		semplicita.setBackground(Color.LIGHT_GRAY);
		panel.add(semplicita);
		
		JLabel label6=new JLabel("Punteggio compenso");
		panel.add(label6);
		compenso= new JTextArea();
		compenso.setBackground(Color.LIGHT_GRAY);
		panel.add(compenso);

		JLabel label7=new JLabel("Punteggio affidabilita");
		panel.add(label7);
		affidabilita= new JTextArea();
		affidabilita.setBackground(Color.LIGHT_GRAY);
		panel.add(affidabilita);
		
		JLabel label8=new JLabel("Punteggio rapidita consegna");
		panel.add(label8);
		rapidita_consegna= new JTextArea();
		rapidita_consegna.setBackground(Color.LIGHT_GRAY);
		panel.add(rapidita_consegna);
	
		
		JButton button=new JButton("Invia recensione");
		panel.add(button);
		
		add(panel);
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		  
	}
	
	public JTextArea getRecensione() {
		return recensione;
	}
	
	public JTextArea getPunteggioCostoSpedizione() {
		return costo_spedizione;
	}
	public JTextArea getPunteggioSoddisfazioneGenerale() {
		return soddisfazione_generale;
	}
	public JTextArea getPunteggioTempoSpedione() {
		return tempo_spedizione;
	}
	public JTextArea getPunteggioSemplicita() {
		return semplicita; 
	}
	public JTextArea getPunteggioCompenso() {
		return compenso;
	}
	public JTextArea getPunetggioAffidabilita() {
		return affidabilita;
	}
	
	public JTextArea getPunetggioRapiditaConsegna() {
		return rapidita_consegna;
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
