package it.unipv.ingsw.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.utenze.EndUser;
import it.unipv.ingsw.model.utenze.Utente;

public class TracciamentoView {
	
	private JFrame frame;
	private JTextField codiceSpedizioneField;
	private JTextArea statoSpedizioneArea;
	private JButton tracciaButton;
	private GestoreSpedizioni gestoreSpedizioni;
    private EndUser currentUser;
	
	Coordinate a = new Coordinate(4,5);
	Coordinate b = new Coordinate(7,-2);
	IPuntoDeposito l1 = new Locker(a,1);
	IPuntoDeposito l2 = new Locker(b,2);
	private Spedizione spedizione = new Spedizione(null, null, null, 0, l1, l2, null, new Date(0));
	
	public TracciamentoView(GestoreSpedizioni gestoreSpedizioni, EndUser currentUser) {
		this.gestoreSpedizioni = gestoreSpedizioni;
        this.currentUser = currentUser;
		
		
		//creazione finestra
		frame = new JFrame("Tracciamento Spedizione");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		
		//componenti
		codiceSpedizioneField = new JTextField(15);
		statoSpedizioneArea = new JTextArea(5, 20);
		statoSpedizioneArea.setEditable(false);
		tracciaButton = new JButton("Traccia");
		
		//Layout
		frame.setLayout(new FlowLayout());
		
		//aggiunta dei componenti alla finestra
		frame.add(new JLabel("Inserisci codice spedizione"));
		frame.add(codiceSpedizioneField);
		frame.add(tracciaButton);
		frame.add(new JScrollPane(statoSpedizioneArea));
		
		//azioni per il pulsante traccia
		tracciaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//recupera il codice inserito
				String codiceInserito = codiceSpedizioneField.getText().trim();
				
				//recupera la sped. dalla lista nel GestoreSpedizioni
				Spedizione spedizione = gestoreSpedizioni.getSpedizioneByCodiceQR(codiceInserito);
				
				//verifica se la spedizione è stata trovata
				if (spedizione != null) {
					//se la sped. esiste, si mostra il suo stato
					String stato = spedizione.getStatoSpedizione();
					statoSpedizioneArea.setText("Stato spedizione: " + stato);
					
					//aggiungo l'utente EndUser come osservatore della spedizione
					spedizione.addObserver(currentUser);
				} else {
					//spedizione non trovata
					statoSpedizioneArea.setText("Codice non valido.");
				}
				
			}
			
		});
		
		frame.setVisible(true);
		
	}
	
	public static void apriTracciamento(GestoreSpedizioni gestoreSpedizioni, EndUser currentUser) {
		new TracciamentoView(gestoreSpedizioni, currentUser);
	}
	
//	public static void main(String[] args) {
//		
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				GestoreSpedizioni gestoreSpedizioni = new GestoreSpedizioni(new MatchingService());
//				EndUser currentUser = new EndUser();
//				new TracciamentoView(gestoreSpedizioni, currentUser);
//			}
//			
//		});
//		
//	}
	
}
