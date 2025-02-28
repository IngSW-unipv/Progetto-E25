package it.unipv.ingsw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class AdminView extends JFrame{
	private JPanel azioniPan,risultatoPan;
	private JButton convalida,logout,disattiva;
	private JScrollPane scrollAzioni, scrollRisultato;

	public AdminView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null); 
        //setVisible(true);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("ShipUp/Profilo Admin");
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		convalida =new JButton("Convalida profilo utenti in attesa");
		disattiva =new JButton("Disattiva account utente");
		logout =new JButton("Logout");		
		logout.setPreferredSize(new Dimension(90, 40));
		convalida.setPreferredSize(new Dimension(90, 40));
		disattiva.setPreferredSize(new Dimension(90, 40));
		convalida.setFocusable(true); //non schiacciabile fino ad approvazione admin
		logout.setFocusable(false);
		disattiva.setFocusable(false);
		azioniPan=new JPanel();
		risultatoPan=new JPanel();		
		azioniPan.setLayout(new GridLayout(10, 10));
		risultatoPan.setLayout(new GridLayout(10, 10));
		JToolBar bar=new JToolBar();
		bar.add(convalida);
		bar.add(disattiva);
		bar.add(logout);
		
		scrollAzioni = new JScrollPane(azioniPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRisultato = new JScrollPane(risultatoPan, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(bar, BorderLayout.NORTH);
		this.add(scrollAzioni, BorderLayout.CENTER);
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

	public JButton getConvalida() {
		return convalida;
	}

	
	public JButton getLogout() {
		return logout;
	}
	
	public JButton getDisattiva() {
		return disattiva;
	}


public static void main(String[] args) {
    // Imposta l'aspetto dell'interfaccia grafica in modo più fluido (threading Swing)
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            // Crea una nuova istanza di UtenteView
        	AdminView adminView = new AdminView();
            // Puoi usare il metodo setVisible(true) per mostrare la finestra
        	adminView.setVisible(true);
        }
    });
}
}
