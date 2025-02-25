package it.unipv.ingsw.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarrierView extends JFrame {
    private JTable shipmentsTable;
    private JButton acceptShipmentsButton;
    
    public CarrierView() {
        setTitle("Carrier - Visualizza Spedizioni");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Impostiamo le colonne della tabella (ad es. ID, Origine, Destinazione, Stato)
        String[] columnNames = {"ID", "Origine", "Destinazione", "Stato"};
        // Dati fittizi per dimostrazione
        Object[][] data = {
            {"1", "Milano", "Roma", "In transito"},
            {"2", "Napoli", "Torino", "Consegnato"},
            {"3", "Venezia", "Bologna", "In attesa"}
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // disabilita la modifica delle celle
            }
        };
        shipmentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(shipmentsTable);
        
        // Bottone per accettare le spedizioni
        acceptShipmentsButton = new JButton("Accetta Spedizioni");
        
        // Layout della view
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(acceptShipmentsButton, BorderLayout.SOUTH);
    }
    
    public JButton getAcceptShipmentsButton() {
        return acceptShipmentsButton;
    }
    
    public JTable getShipmentsTable() {
        return shipmentsTable;
    }
}
