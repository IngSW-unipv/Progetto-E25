package it.unipv.ingsw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PagamentoView extends JFrame {
    // Variabili per i componenti
    private JPanel formPanel;
    private JLabel cardNumberLabel;
    private JComboBox<String> cardNumberField;
    private JLabel cvvLabel;
    private JTextField cvvField;
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton payButton;
    private JLabel balanceLabel;
    private JTextField balanceField;
    private JLabel pointsLabel;
    private JTextField pointsField;
    private JLabel cardNumberInputLabel;
    private JTextField cardNumberFieldInput;

    // Dati finti per l'utente (questi dovrebbero essere sostituiti con i dati reali)
    private double saldoUtente = 100.0; // Esempio di saldo
    private int puntiApp = 200;         // Esempio di puntiApp

    public PagamentoView(double costo) {
        setTitle("Pagamento");
        setSize(1000, 600);
        setLayout(new BorderLayout());

        formPanel = new JPanel(new GridLayout(7, 2, 10, 10));  
        add(formPanel, BorderLayout.CENTER);

        // Etichetta per la selezione del metodo di pagamento
        JLabel methodLabel = new JLabel("Seleziona il metodo di pagamento:");
        formPanel.add(methodLabel);

        // ComboBox per la selezione del metodo di pagamento
        String[] sizeOptions = { 
            "Saldo", "PuntiApp", "Carta", "Saldo+PuntiApp", "Carta+PuntiApp", "Saldo+Carta", "PuntiApp+Carta" 
        };
        cardNumberField = new JComboBox<>(sizeOptions);
        formPanel.add(cardNumberField);

        // Etichetta per il saldo
        balanceLabel = new JLabel("Saldo disponibile: €");
        balanceField = new JTextField();
        balanceField.setEditable(false);
        formPanel.add(balanceLabel);
        formPanel.add(balanceField);

        // Etichetta per i puntiApp
        pointsLabel = new JLabel("PuntiApp disponibili:");
        pointsField = new JTextField();
        pointsField.setEditable(false);
        formPanel.add(pointsLabel);
        formPanel.add(pointsField);

        // Etichetta per il numero della carta
        cardNumberInputLabel = new JLabel("Numero carta:");
        cardNumberFieldInput = new JTextField();
        cardNumberFieldInput.setEditable(false);
        formPanel.add(cardNumberInputLabel);
        formPanel.add(cardNumberFieldInput);

        // Etichetta per il CVV
        cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField();
        cvvField.setEditable(false);
        formPanel.add(cvvLabel);
        formPanel.add(cvvField);

        // Etichetta per l'importo da pagare
        amountLabel = new JLabel("Totale da pagare:");
        amountField = new JTextField();
        amountField.setEditable(false);
        formPanel.add(amountLabel);
        formPanel.add(amountField);

        // Bottone per confermare il pagamento
        payButton = new JButton("Conferma pagamento");
        formPanel.add(new JLabel());  // Spazio vuoto
        formPanel.add(payButton);

        // Impostiamo il font per i componenti
        Font font = new Font("Arial", Font.PLAIN, 22);
        methodLabel.setFont(font);
        cardNumberField.setFont(font);
        balanceLabel.setFont(font);
        balanceField.setFont(font);
        pointsLabel.setFont(font);
        pointsField.setFont(font);
        cardNumberInputLabel.setFont(font);
        cardNumberFieldInput.setFont(font);
        cvvLabel.setFont(font);
        cvvField.setFont(font);
        amountLabel.setFont(font);
        amountField.setFont(font);
        payButton.setFont(font);

        // Impostiamo la dimensione della finestra per il display
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 4, screenSize.height / 4);
        setLocationRelativeTo(null);  // Centra la finestra sullo schermo
        validate();

        // Aggiungiamo un ActionListener per la ComboBox
        cardNumberField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUIBasedOnSelection();
            }
        });

        updateUIBasedOnSelection();  // Per impostare l'interfaccia all'avvio

        setVisible(true);
    }

    // Metodo per aggiornare l'interfaccia in base alla selezione della ComboBox
    private void updateUIBasedOnSelection() {
        String selectedPaymentMethod = (String) cardNumberField.getSelectedItem();

        // Nascondi tutti i campi per un aggiornamento pulito
        balanceLabel.setVisible(false);
        balanceField.setVisible(false);
        pointsLabel.setVisible(false);
        pointsField.setVisible(false);
        cardNumberInputLabel.setVisible(false);
        cardNumberFieldInput.setVisible(false);
        cvvLabel.setVisible(false);
        cvvField.setVisible(false);
        amountLabel.setVisible(false);
        amountField.setVisible(false);

        // Gestiamo la visibilità dei campi in base alla selezione
        if (selectedPaymentMethod.equals("Saldo")) {
            balanceLabel.setVisible(true);
            balanceField.setVisible(true);
            balanceField.setText(String.valueOf(saldoUtente)); // Mostra il saldo

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(saldoUtente)); // Mostra l'importo in euro

        } else if (selectedPaymentMethod.equals("PuntiApp")) {
            pointsLabel.setVisible(true);
            pointsField.setVisible(true);
            pointsField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(puntiApp)); // Mostra il totale in puntiApp

        } else if (selectedPaymentMethod.equals("Carta")) {
            cardNumberInputLabel.setVisible(true);
            cardNumberFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText("50.00"); // Mostra un importo esempio

        } else if (selectedPaymentMethod.equals("Saldo+PuntiApp")) {
            balanceLabel.setVisible(true);
            balanceField.setVisible(true);
            balanceField.setText(String.valueOf(saldoUtente)); // Mostra il saldo

            pointsLabel.setVisible(true);
            pointsField.setVisible(true);
            pointsField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText("200.00"); // Mostra un importo esempio

        } else if (selectedPaymentMethod.equals("Carta+PuntiApp")) {
            cardNumberInputLabel.setVisible(true);
            cardNumberFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            pointsLabel.setVisible(true);
            pointsField.setVisible(true);
            pointsField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText("150.00"); // Mostra un importo esempio

        } else if (selectedPaymentMethod.equals("Saldo+Carta")) {
            balanceLabel.setVisible(true);
            balanceField.setVisible(true);
            balanceField.setText(String.valueOf(saldoUtente)); // Mostra il saldo

            cardNumberInputLabel.setVisible(true);
            cardNumberFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText("100.00"); // Mostra un importo esempio

        } else if (selectedPaymentMethod.equals("PuntiApp+Carta")) {
            pointsLabel.setVisible(true);
            pointsField.setVisible(true);
            pointsField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            cardNumberInputLabel.setVisible(true);
            cardNumberFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText("75.00"); // Mostra un importo esempio
        }
    }

    public static void main(String[] args) {
        // Imposta l'aspetto dell'interfaccia grafica in modo più fluido (threading Swing)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crea una nuova istanza di PagamentoView
            	double costo=5.2;
                PagamentoView pagamentoView = new PagamentoView(costo);
                // Puoi usare il metodo setVisible(true) per mostrare la finestra
                pagamentoView.setVisible(true);
            }
        });
    }
}
