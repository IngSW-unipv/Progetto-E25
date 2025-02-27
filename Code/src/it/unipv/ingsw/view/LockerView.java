package it.unipv.ingsw.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;

public class LockerView {

	private Locker locker;
	
	public LockerView(Locker locker) {
		this.locker = locker;
		 JFrame frame = new JFrame("Locker");
	     JButton checkQRButton = new JButton("Check QR");
	     
	     checkQRButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String codiceQR = JOptionPane.showInputDialog("Inserisci il codice QR della spedizione:");

	                if (codiceQR != null) {
	                    // Creiamo un oggetto QRCode (supponendo che tu abbia una classe QRCode)
	                    QRcode qrCode = new QRcode();

	                    // Creiamo una spedizione fittizia (devi recuperarla da DB)
	                    Spedizione spedizione = new Spedizione(1, "IN_TRANSITO", "2025-02-15 08:00:00", null, "user1@example.com", "user2@example.com", 1, 3, 50); // Supponiamo che l'ID sia il codice QR

	                    // Definiamo i parametri booleani (puoi modificarli in base alla logica)
	                    boolean isRitiro = true;  
	                    boolean isMittenteDeposita = false;  
	                    boolean isPresaInCarico = false;  

	                    // Chiamiamo il metodo con i parametri corretti
	                    if (locker.checkQR(qrCode, spedizione, isRitiro, isMittenteDeposita, isPresaInCarico)) {
	                        JOptionPane.showMessageDialog(null, "Codice valido! Aprendo lo scompartimento...");
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Codice errato! Riprova.");
	                    }
	                }
	            }
	        });

	     
	     frame.add(checkQRButton);
	        frame.setSize(300, 200);
	        frame.setLayout(new java.awt.FlowLayout());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	}
	
	 public static void main(String[] args) {
		 Coordinate a = new Coordinate(4,5);
			Coordinate b = new Coordinate(7,-2);
	        new LockerView(new Locker(a, 1));
	    }
	
}
