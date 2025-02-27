package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RicaricaSaldoView extends JFrame {
    
    private JTextField amountField;
    private JButton confirmButton;
    private double amount;

    public RicaricaSaldoView() {
        // Impostazioni della finestra principale
        setTitle("Ricarica");
        setSize(400, 150);
        setVisible(true);
        setLocationRelativeTo(null);  // Centro della finestra sullo schermo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creazione dei componenti
        JLabel label = new JLabel("Quanto vuoi caricare?");
        amountField = new JTextField(15);  // JTextField per l'importo
        confirmButton = new JButton("Conferma");

        // Impostazioni del layout
        setLayout(new FlowLayout());  // Layout semplice orizzontale
        add(label);
        add(amountField);
        add(confirmButton);

        // Gestore dell'evento per il bottone di conferma
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera il valore inserito nel JTextField
                String amountText = amountField.getText();
                
                // Converte la stringa in un numero double
                try {
                    double amount = Double.parseDouble(amountText);
                    System.out.println("Hai inserito: " + amount);
                    
                    // Chiudi la finestra RicaricaView
                    dispose();
                    
                    // Passa alla schermata PagamentoEsternoView
                    new PagamentoEsternoView(amount).setVisible(true);
                    
                } catch (NumberFormatException ex) {
                    // Gestione dell'errore se il valore non è un numero valido
                    JOptionPane.showMessageDialog(null, "Inserisci un valore numerico valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public JButton getConfirmButton() {
		return confirmButton;
	}

    public double getAmount() {
		return amount;
	}

	public static void main(String[] args) {
        // Creazione e visualizzazione della finestra
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RicaricaSaldoView().setVisible(true);
            }
        });
    }
}

