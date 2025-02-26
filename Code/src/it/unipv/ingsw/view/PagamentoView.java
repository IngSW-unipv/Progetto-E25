package it.unipv.ingsw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
    private JComboBox<String> opzioneMetodoPagamentoField;
    private JLabel cvvLabel;
    private JTextField cvvField;
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton payButton;
    private JLabel saldoLabel;
    private JTextField saldoField;
    private JLabel puntiLabel;
    private JTextField puntiField;
    private JLabel numeroCartaInputLabel;
    private JTextField numeroCartaFieldInput;
    private int selectedIndex;
    //private PagamentoView pagamentoView;

    // Dati finti per l'utente (questi dovrebbero essere sostituiti con i dati reali)
    private double saldoUtente = 100.0; // Esempio di saldo
    private int puntiApp = 200;         // Esempio di puntiApp

    public PagamentoView(double costoSpedizione) {
        setTitle("Pagamento");
        setSize(1000, 600);
        setLayout(new BorderLayout());

        formPanel = new JPanel(new GridLayout(7, 2, 10, 10));  
        add(formPanel, BorderLayout.CENTER);

        // Etichetta per la selezione del metodo di pagamento
        JLabel methodLabel = new JLabel("  Seleziona il metodo di pagamento:");
        formPanel.add(methodLabel);

        // ComboBox per la selezione del metodo di pagamento
        String[] sizeOptions = { 
            "Saldo", "PuntiApp", "Carta", "Saldo+PuntiApp", "Carta+PuntiApp", "Saldo+Carta", "PuntiApp+Carta" 
        };
        opzioneMetodoPagamentoField = new JComboBox<>(sizeOptions);
        formPanel.add(opzioneMetodoPagamentoField);

        // Etichetta per il saldo
        saldoLabel = new JLabel("  Saldo attuale: €");
        saldoField = new JTextField();
        saldoField.setEditable(false);
        formPanel.add(saldoLabel);
        formPanel.add(saldoField);

        // Etichetta per i puntiApp
        puntiLabel = new JLabel("  PuntiApp accumulati:");
        puntiField = new JTextField();
        puntiField.setEditable(false);
        formPanel.add(puntiLabel);
        formPanel.add(puntiField);

        // Etichetta per il numero della carta
        numeroCartaInputLabel = new JLabel("  Numero carta:");
        numeroCartaFieldInput = new JTextField();
        numeroCartaFieldInput.setEditable(true);
        formPanel.add(numeroCartaInputLabel);
        formPanel.add(numeroCartaFieldInput);

        // Etichetta per il CVV
        cvvLabel = new JLabel("  CVV:");
        cvvField = new JTextField();
        cvvField.setEditable(true);
        formPanel.add(cvvLabel);
        formPanel.add(cvvField);

        // Etichetta per l'importo da pagare
        amountLabel = new JLabel("  Totale da pagare:");
        amountField = new JTextField();
        amountField.setEditable(false);
        formPanel.add(amountLabel);
        formPanel.add(amountField);

        // Bottone per confermare il pagamento
        payButton = new JButton("  Conferma pagamento");
        formPanel.add(new JLabel());  // Spazio vuoto
        formPanel.add(payButton);

        // Impostiamo il font per i componenti
        Font font = new Font("Arial", Font.PLAIN, 22);
        methodLabel.setFont(font);
        opzioneMetodoPagamentoField.setFont(font);
        saldoLabel.setFont(font);
        saldoField.setFont(font);
        puntiLabel.setFont(font);
        puntiField.setFont(font);
        numeroCartaInputLabel.setFont(font);
        numeroCartaFieldInput.setFont(font);
        cvvLabel.setFont(font);
        cvvField.setFont(font);
        amountLabel.setFont(font);
        amountField.setFont(font);
        payButton.setFont(font);

        setLocationRelativeTo(null);  // Centra la finestra sullo schermo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        validate();
        
        opzioneMetodoPagamentoField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera l'indice selezionato
                int selectedIndex = opzioneMetodoPagamentoField.getSelectedIndex();
                
                // Verifica se l'indice cambia correttamente
                System.out.println("Indice selezionato nel listener della ComboBox: " + selectedIndex);

                // Verifica anche la voce selezionata nella ComboBox
                String selectedOption = (String) opzioneMetodoPagamentoField.getSelectedItem(); //OKKK
                System.out.println("Opzione selezionata: " + selectedOption);
                
                // Aggiungi anche l'aggiornamento della UI
                updateUIBasedOnSelection(costoSpedizione);
            }
        });

        updateUIBasedOnSelection(costoSpedizione);  // Per impostare l'interfaccia all'avvio

        setVisible(true);
    }

    // Metodo per aggiornare l'interfaccia in base alla selezione della ComboBox
    private void updateUIBasedOnSelection(double costoSpedizione) {
        String selectedPaymentMethod = (String) opzioneMetodoPagamentoField.getSelectedItem();

        // Nascondi tutti i campi per un aggiornamento pulito
        saldoLabel.setVisible(false);
        saldoField.setVisible(false);
        puntiLabel.setVisible(false);
        puntiField.setVisible(false);
        numeroCartaInputLabel.setVisible(false);
        numeroCartaFieldInput.setVisible(false);
        cvvLabel.setVisible(false);
        cvvField.setVisible(false);
        amountLabel.setVisible(false);
        amountField.setVisible(false);

        // Gestiamo la visibilità dei campi in base alla selezione
        if (selectedPaymentMethod.equals("Saldo")) {
            saldoLabel.setVisible(true);
            saldoField.setVisible(true);
            saldoField.setText(String.valueOf(saldoUtente)); // Mostra il saldo

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(costoSpedizione)); // Mostra il costo spedizione

        } else if (selectedPaymentMethod.equals("PuntiApp")) {
            puntiLabel.setVisible(true);
            puntiField.setVisible(true);
            puntiField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp come importo

        } else if (selectedPaymentMethod.equals("Carta")) {
            numeroCartaInputLabel.setVisible(true);
            numeroCartaFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(costoSpedizione)); // Mostra il costo spedizione

        } else if (selectedPaymentMethod.equals("Saldo+PuntiApp")) {
            saldoLabel.setVisible(true);
            saldoField.setVisible(true);
            saldoField.setText(String.valueOf(saldoUtente)); // Mostra il saldo

            puntiLabel.setVisible(true);
            puntiField.setVisible(true);
            puntiField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(costoSpedizione)); // Mostra il costo spedizione

        } else if (selectedPaymentMethod.equals("Carta+PuntiApp")) {
            numeroCartaInputLabel.setVisible(true);
            numeroCartaFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            puntiLabel.setVisible(true);
            puntiField.setVisible(true);
            puntiField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(costoSpedizione)); // Mostra il costo spedizione

        } else if (selectedPaymentMethod.equals("Saldo+Carta")) {
            saldoLabel.setVisible(true);
            saldoField.setVisible(true);
            saldoField.setText(String.valueOf(saldoUtente)); // Mostra il saldo

            numeroCartaInputLabel.setVisible(true);
            numeroCartaFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(costoSpedizione)); // Mostra il costo spedizione

        } else if (selectedPaymentMethod.equals("PuntiApp+Carta")) {
            puntiLabel.setVisible(true);
            puntiField.setVisible(true);
            puntiField.setText(String.valueOf(puntiApp)); // Mostra i puntiApp

            numeroCartaInputLabel.setVisible(true);
            numeroCartaFieldInput.setVisible(true);
            cvvLabel.setVisible(true);
            cvvField.setVisible(true);

            amountLabel.setVisible(true);
            amountField.setVisible(true);
            amountField.setText(String.valueOf(costoSpedizione)); // Mostra il costo spedizione
        }
        this.revalidate();  // Rende il componente visibile con le modifiche applicate
        this.repaint(); 
    }
    

	public int getSelectedIndex() {
		return selectedIndex;
	}
	
    public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public JComboBox<String> getOpzioneMetodoPagamentoField() {
		return opzioneMetodoPagamentoField;
	}

	public void setOpzioneMetodoPagamentoField(JComboBox<String> opzioneMetodoPagamentoField) {
		this.opzioneMetodoPagamentoField = opzioneMetodoPagamentoField;
	}

	public JTextField getCvvField() {
		return cvvField;
	}

	public void setCvvField(JTextField cvvField) {
		this.cvvField = cvvField;
	}

	public JTextField getAmountField() {
		return amountField;
	}

	public void setAmountField(JTextField amountField) {
		this.amountField = amountField;
	}

	public JTextField getSaldoField() {
		return saldoField;
	}

	public void setSaldoField(JTextField saldoField) {
		this.saldoField = saldoField;
	}

	public JTextField getPuntiField() {
		return puntiField;
	}

	public void setPuntiField(JTextField puntiField) {
		this.puntiField = puntiField;
	}

	public JTextField getNumeroCartaFieldInput() {
		return numeroCartaFieldInput;
	}

	public void setNumeroCartaFieldInput(JTextField numeroCartaFieldInput) {
		this.numeroCartaFieldInput = numeroCartaFieldInput;
	}

	public double getSaldoUtente() {
		return saldoUtente;
	}

	public void setSaldoUtente(double saldoUtente) {
		this.saldoUtente = saldoUtente;
	}
	
	public JButton getPayButton() {
		return payButton;
	}

	public void setPayButton(JButton payButton) {
		this.payButton = payButton;
	}
	

	public static void main(String[] args) {
        // Imposta l'aspetto dell'interfaccia grafica in modo più fluido (threading Swing)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crea una nuova istanza di PagamentoView
                double costo = 2.5;
                PagamentoView pagamentoView = new PagamentoView(costo);
                // Puoi usare il metodo setVisible(true) per mostrare la finestra
                pagamentoView.setVisible(false);
            }
        });
    }
}
