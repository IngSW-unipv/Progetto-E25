package it.unipv.ingsw.view;

import javax.swing.*;

import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.utenze.EndUser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class UtenteView extends JFrame {

	private JPanel azioniPan,risultatoPan,infoPan;
	private JButton modifica,logout,avviaSped,prendiInCarico,trasferisci, ricarica, tracciamento, storicoSpedizione;
	private JButton  cancellaAccount;
	private JLabel infoUtenteLab, saldo;
	private JScrollPane scrollAzioni, scrollRisultato;
	private double saldoUtente = 0.0; 
    private int puntiApp = 0;   
	private GestoreSpedizioni gestoreSpedizioni;
    private EndUser currentUser;
    private JButton tracciamentoButton;

    
    private TracciamentoView tracciamentoView;

	public UtenteView(Utente utente) {
		
		 saldoUtente=utente.getSaldo().getDenaro();
	     puntiApp=utente.getSaldo().getPuntiApp();
	        
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null); 
        //setVisible(true);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("ShipUp/Profilo Utente");
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
//		tracciamentoButton = new JButton("Traccia Spedizione");
//		
//		//impostazione layout e aggiunta del bottone
//		setLayout(new FlowLayout());
//		add(tracciamentoButton);
		
		
		modifica =new JButton("Modifica profilo");
		avviaSped =new JButton("Avvia spedizione");
		prendiInCarico =new JButton("Prendi in carico spedizione");
		logout =new JButton("Logout");
		ricarica =new JButton("Ricarica saldo");
		trasferisci =new JButton("Trasferisci saldo"); 
		tracciamento = new JButton("Tracciamento"); //aggiunto il bottone tracciamento
		cancellaAccount =new JButton("Cancella account");
		storicoSpedizione =new JButton("Storico spedizioni"); 
		
		logout.setPreferredSize(new Dimension(90, 40));
		cancellaAccount.setPreferredSize(new Dimension(90, 40));
		modifica.setPreferredSize(new Dimension(90, 40));
		avviaSped.setPreferredSize(new Dimension(90, 40));
		prendiInCarico.setPreferredSize(new Dimension(110, 40));
		trasferisci.setPreferredSize(new Dimension(200, 70));
		ricarica.setPreferredSize(new Dimension(200, 70));
		storicoSpedizione.setPreferredSize(new Dimension(200, 70));
		tracciamento.setPreferredSize(new Dimension(150, 40)); //imposta le dimensioni del bottone

		
		modifica.setFocusable(true); //non schiacciabile fino ad approvazione admin
		logout.setFocusable(false);
		avviaSped.setFocusable(false);
		cancellaAccount.setFocusable(false);
		prendiInCarico.setFocusable(false);
		prendiInCarico.setVisible(true);
		trasferisci.setVisible(true);
		storicoSpedizione.setVisible(true);
		ricarica.setVisible(true);
		tracciamento.setVisible(false); //invisibile inizialmente

		
		azioniPan=new JPanel();
		risultatoPan=new JPanel();
		infoPan=new JPanel();
		
		azioniPan.setLayout(new GridLayout(10, 10));
		risultatoPan.setLayout(new GridLayout(10, 10));
		
		infoUtenteLab=new JLabel();
		infoUtenteLab.setPreferredSize(new Dimension(200,80));
		infoUtenteLab.setText("Profilo utente:				");
		infoUtenteLab.setPreferredSize(new Dimension(200,80));
		infoUtenteLab.setText("Saldo: "+saldoUtente+"€");
		infoPan.add(infoUtenteLab);
		
		saldo=new JLabel();
		saldo.setPreferredSize(new Dimension(200,80));
		saldo.setText("PuntiApp: "+puntiApp);
		infoPan.add(saldo);
		infoPan.add(ricarica);
		infoPan.add(trasferisci);
		infoPan.add(storicoSpedizione);
		
		JToolBar bar=new JToolBar();
		bar.add(modifica);
		bar.add(avviaSped);
		bar.add(prendiInCarico);
		bar.add(logout);
		bar.add(tracciamento); //aggiungo il bottone alla barra
		bar.add(cancellaAccount);
		
		scrollAzioni = new JScrollPane(azioniPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRisultato = new JScrollPane(risultatoPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(bar, BorderLayout.NORTH);
		this.add(scrollAzioni, BorderLayout.CENTER);
		this.add(infoPan, BorderLayout.SOUTH);
		
		//listener per il button tracciamento
		tracciamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//quando il bottone tracciamento viene cliccato, apre la finistra tracciamnto
				apriFinestraTracciamento();
			}
			
		});
		
		
//		// Impostazioni di visualizzazione
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);  // Per centrare la finestra
        
	}
	
	
	public JButton getTracciamentoButton() {
	        return tracciamentoButton;
	}
	
	public int displayConfirm() {
		return JOptionPane.showConfirmDialog(this, "Sei sicuro di continuare?");
	}
	
	public JPanel getAzioniPan() {
		return azioniPan;
	}
	
	public JPanel getRisultatoPanel() {
		return risultatoPan;
	}	

	public JButton getModificaProfilo() {
		return modifica;
	}

	
	public JButton getLogout() {
		return logout;
	}
	
	public JButton getAvviaSpedizione() {
		return avviaSped;
	}
	
	public JButton getTrasferisci() {
		return trasferisci;
	}
	
	public JLabel getInfoUtenteLab() {
		return infoUtenteLab;
	}

	public void setInfoUtenteLab(JLabel infoLab) {
		this.infoUtenteLab = infoLab;
	}
	
	public void setInfoLabText() {
		this.infoUtenteLab.setText("");
	}
	
	public void setSaldoLabImp(double tot) {
		this.saldo.setText("Saldo: "+ tot +" euro");
	}
	
	public JLabel getSaldoLab() {
		return this.saldo;
	}

	public JButton getPrendiInCaricoSpedizioneButton() {
		return prendiInCarico;
	}

	public void setPrendiInCaricoSpedizione(JButton prendiInCarico) {
		this.prendiInCarico = prendiInCarico;
	}

	public JButton getRicarica() {
		return ricarica;
	}
	
	public void apriFinestraTracciamento() {
		if (tracciamentoView == null) {
			tracciamentoView = new TracciamentoView(gestoreSpedizioni, currentUser);
		}
	}
	
	public void setGestoreSpedizioni(GestoreSpedizioni gestoreSpedizioni) {
		this.gestoreSpedizioni = gestoreSpedizioni;
	}
	
	public void setEndUser(EndUser currentUser) {
		this.currentUser = currentUser;
		tracciamento.setVisible(true); //mostra il bottone Tracciamento dopo il login

		
	}
	
	

	public JButton getCancellaAccount() {
		return cancellaAccount;
	}

public static void main(String[] args) {
	GestoreSpedizioni gestoreSpedizioni = new GestoreSpedizioni(new MatchingService());
    EndUser currentUser = new EndUser("user@example.com", "password", "Destinatario", null, null, null, null, null);
	
    // Imposta l'aspetto dell'interfaccia grafica in modo più fluido (threading Swing)
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            // Crea una nuova istanza di UtenteView
        	Utente u=new Utente();
        	UtenteView utenteView = new UtenteView(u);
            // Puoi usare il metodo setVisible(true) per mostrare la finestra
        	utenteView.setVisible(true);
        	utenteView.setGestoreSpedizioni(gestoreSpedizioni);
        	utenteView.setEndUser(currentUser);
        }
    });
}
}