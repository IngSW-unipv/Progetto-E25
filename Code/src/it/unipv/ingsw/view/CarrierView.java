package it.unipv.ingsw.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarrierView extends JFrame {
    private JTable shipmentsTable;
    private JButton acceptShipmentsButton;
    private JLabel titleLabel;
    
    public CarrierView() {
        setTitle("Carrier - Visualizza Spedizioni");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        titleLabel = new JLabel("Spedizioni compatibili con la tua tratta:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Definiamo le colonne della tabella
        String[] columnNames = {"IDspedizione", "coordinataInizio", "coordinataFine", "distanza"};
        // La tabella parte vuota, verrà popolata dal controller
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //rende le celle non modificabili
            }
        };
        shipmentsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(shipmentsTable);
        
        // Bottone per accettare le spedizioni
        acceptShipmentsButton = new JButton("Accetta Spedizioni");
        
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(acceptShipmentsButton, BorderLayout.SOUTH);
    }
    
    public JButton getAcceptShipmentsButton() {
        return acceptShipmentsButton;
    }
    
    public JTable getShipmentsTable() {
        return shipmentsTable;
    }
}