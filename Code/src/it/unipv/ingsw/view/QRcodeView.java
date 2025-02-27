package it.unipv.ingsw.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import it.unipv.ingsw.model.spedizione.QRcode;

public class QRcodeView extends JFrame {

	private JButton checkQRButton;
	private JTextField codiceQRField;
    private QRcode qrCode;
    
    public QRcodeView() {
    	//impostatazione della finestra
    	setTitle("QR Code Viewer");
    	setSize(300, 200);
    	setLayout(new FlowLayout());
    	
    	//nuova istanza di QRcode
    	qrCode = new QRcode();
    	qrCode.generaQRcode(); //genera il codice all'avvio
    	codiceQRField.setEditable(false);
    	
    	//aggiunge il button per il checkQR
    	checkQRButton = new JButton("Check QR");
        checkQRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkQR();
            }
        });
        
        //aggiunge i componenti alla finestra
        add(codiceQRField);
        add(checkQRButton);
        
     // per la visualizzazione
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Per centrare la finestra
        
    }
    
    //metodo per gestire il bottone checkQR; è diverso da checkQR() in Locker
	private void checkQR() {
		String qrCodeInput = JOptionPane.showInputDialog(this, "Inserisce il codice QR:");
		
		if (qrCodeInput != null && qrCodeInput.equals(qrCode.getQRcode())) {
			JOptionPane.showMessageDialog(this, "Codice QR corretto!");
		} else {
			JOptionPane.showMessageDialog(this, "Codice QR errato!");
		}
	}
	
}
