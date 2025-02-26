 package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagamentoEsternoView extends JFrame {

    // Componenti della GUI
    private JTextField cardNumberField;
    private JTextField cvvField;
    private JTextField totalField;  // Campo per il totale da pagare
    private JButton confirmButton;
    
    public PagamentoEsternoView() { 
        setTitle("Pagamento carta di credito esterno");
        setLayout(new BorderLayout(10, 10));    

        // Pannello per i campi di input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));  // 4 righe, 2 colonne per aggiungere il totale
        inputPanel.setBackground(new Color(245, 245, 245)); 

        // Etichetta e campo per il numero della carta
        JLabel cardNumberLabel = new JLabel("  Numero Carta di Credito:");
        cardNumberLabel.setFont(new Font("Arial", Font.BOLD, 14));     
        cardNumberField = new JTextField(16);
        cardNumberField.setFont(new Font("Arial", Font.BOLD, 14));
        cardNumberField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));      
        
        // Etichetta e campo per il CVV
        JLabel cvvLabel = new JLabel("  CVV:");
        cvvLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cvvField = new JTextField(3);
        cvvField.setFont(new Font("Arial", Font.BOLD, 14));
        cvvField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));  

        // Etichetta e campo per il totale
        JLabel totalLabel = new JLabel("  Totale da pagare:");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalField = new JTextField("100.00");  // Imposta il totale iniziale (esempio: 100)
        totalField.setFont(new Font("Arial", Font.BOLD, 14));
        totalField.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));  
        totalField.setEditable(false);  // Impedisce la modifica del campo

        // Aggiungi i componenti al pannello
        inputPanel.add(cardNumberLabel);
        inputPanel.add(cardNumberField);
        inputPanel.add(cvvLabel);
        inputPanel.add(cvvField);
        inputPanel.add(totalLabel);
        inputPanel.add(totalField);

        // Bottone per confermare il pagamento
        confirmButton = new JButton("Conferma Pagamento");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(new Color(0, 123, 255));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Aggiungi il listener per il bottone
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePayment();
            }
        });

        // Aggiungi il pannello e il bottone alla finestra
        add(inputPanel, BorderLayout.CENTER);
        add(confirmButton, BorderLayout.SOUTH);

        // Imposta la dimensione della finestra e la chiusura
        setSize(400, 300);  // Aumentato per fare spazio al nuovo campo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
    }

    // Metodo per gestire l'azione di pagamento
    private void handlePayment() {
        String cardNumber = cardNumberField.getText();
        String cvv = cvvField.getText();
        
        // Validazione dei dati
        if (cardNumber.isEmpty() || cvv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Per favore, inserisci tutti i dati.", "Errore", JOptionPane.ERROR_MESSAGE);
        } else if (cardNumber.length() != 16 || !cardNumber.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Il numero della carta di credito deve avere 16 cifre numeriche.", "Errore", JOptionPane.ERROR_MESSAGE);
        } else if (cvv.length() != 3 || !cvv.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Il CVV deve avere 3 cifre numeriche.", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Pagamento Confermato!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PagamentoEsternoView().setVisible(true);
            }
        });
    }
}
