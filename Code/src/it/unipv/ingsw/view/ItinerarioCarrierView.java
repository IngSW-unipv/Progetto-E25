package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.*;

public class ItinerarioCarrierView extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel coordinatePanel;
    private JTextField startXField;
    private JTextField startYField;
    private JTextField endXField;
    private JTextField endYField;
    private JButton sendButton;
    
    public ItinerarioCarrierView() {
        setTitle("Inserimento Coordinate");
        setSize(400, 300);
        
        // Utilizziamo un GridLayout simile a RegistrazioneView:
        // 5 righe e 2 colonne con spaziatura orizzontale e verticale
        coordinatePanel = new JPanel(new GridLayout(5, 2, 10, 10));
        add(coordinatePanel);
        
        // Riga 1: Coordinate del punto di partenza - X
        coordinatePanel.add(new JLabel("Punto di partenza X:"));
        startXField = new JTextField();
        coordinatePanel.add(startXField);
        
        // Riga 2: Coordinate del punto di partenza - Y
        coordinatePanel.add(new JLabel("Punto di partenza Y:"));
        startYField = new JTextField();
        coordinatePanel.add(startYField);
        
        // Riga 3: Coordinate del punto di arrivo - X
        coordinatePanel.add(new JLabel("Punto di arrivo X:"));
        endXField = new JTextField();
        coordinatePanel.add(endXField);
        
        // Riga 4: Coordinate del punto di arrivo - Y
        coordinatePanel.add(new JLabel("Punto di arrivo Y:"));
        endYField = new JTextField();
        coordinatePanel.add(endYField);
        
        // Riga 5: Bottone per l'invio dei dati
        sendButton = new JButton("Invia");
        coordinatePanel.add(sendButton);
        
        // Per riempire la griglia (5 righe x 2 colonne = 10 celle) aggiungiamo un'etichetta vuota
        coordinatePanel.add(new JLabel(""));
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Metodi getter per permettere al controller di accedere ai dati

    public JTextField getStartXField() {
        return startXField;
    }

    public JTextField getStartYField() {
        return startYField;
    }

    public JTextField getEndXField() {
        return endXField;
    }

    public JTextField getEndYField() {
        return endYField;
    }

    public JButton getSendButton() {
        return sendButton;
    }
}
