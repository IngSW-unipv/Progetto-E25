package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class UtenteView extends JFrame {

	private JPanel azioniPan,risultatoPan,infoPan;
	private JButton modifica,logout,avviaSped,prendiInCarico,trasferisci, ricarica;
	private JLabel infoUtenteLab, saldo;
	private JScrollPane scrollAzioni, scrollRisultato;

	public UtenteView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null); 
        //setVisible(true);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("ShipUp/Profilo Utente");
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		modifica =new JButton("Modifica profilo");
		avviaSped =new JButton("Avvia spedizione");
		prendiInCarico =new JButton("Prendi in carico spedizione");
		logout =new JButton("Logout");
		ricarica =new JButton("Ricarica saldo");
		trasferisci =new JButton("Trasferisci saldo"); 
		
		logout.setPreferredSize(new Dimension(90, 40));
		modifica.setPreferredSize(new Dimension(90, 40));
		avviaSped.setPreferredSize(new Dimension(90, 40));
		prendiInCarico.setPreferredSize(new Dimension(110, 40));
		trasferisci.setPreferredSize(new Dimension(200, 70));
		ricarica.setPreferredSize(new Dimension(200, 70));
		
		modifica.setFocusable(true); //non schiacciabile fino ad approvazione admin
		logout.setFocusable(false);
		avviaSped.setFocusable(false);
		prendiInCarico.setFocusable(false);
		prendiInCarico.setVisible(true);
		trasferisci.setVisible(true);
		ricarica.setVisible(true);
		
		azioniPan=new JPanel();
		risultatoPan=new JPanel();
		infoPan=new JPanel();
		
		azioniPan.setLayout(new GridLayout(10, 10));
		risultatoPan.setLayout(new GridLayout(10, 10));
		
		infoUtenteLab=new JLabel();
		infoUtenteLab.setPreferredSize(new Dimension(200,80));
		infoUtenteLab.setText("Profilo utente:				");
		infoUtenteLab.setPreferredSize(new Dimension(200,80));
		infoUtenteLab.setText("Saldo: 0$");
		infoPan.add(infoUtenteLab);
		
		saldo=new JLabel();
		saldo.setPreferredSize(new Dimension(200,80));
		saldo.setText("PuntiApp: 0");
		infoPan.add(saldo);
		infoPan.add(ricarica);
		infoPan.add(trasferisci);
		
		JToolBar bar=new JToolBar();
		bar.add(modifica);
		bar.add(avviaSped);
		bar.add(prendiInCarico);
		bar.add(logout);
		
		scrollAzioni = new JScrollPane(azioniPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRisultato = new JScrollPane(risultatoPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(bar, BorderLayout.NORTH);
		this.add(scrollAzioni, BorderLayout.CENTER);
		this.add(infoPan, BorderLayout.SOUTH);
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

public static void main(String[] args) {
    // Imposta l'aspetto dell'interfaccia grafica in modo più fluido (threading Swing)
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            // Crea una nuova istanza di UtenteView
        	UtenteView utenteView = new UtenteView();
            // Puoi usare il metodo setVisible(true) per mostrare la finestra
        	utenteView.setVisible(true);
        }
    });
}
}