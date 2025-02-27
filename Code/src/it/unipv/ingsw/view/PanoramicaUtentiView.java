package it.unipv.ingsw.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanoramicaUtentiView extends JFrame {
    private JTable usersTable;
    private DefaultTableModel tableModel;

    public UtentiDaConvalidareView() {
        setTitle("Tutti gli utenti da convalidare:");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Creazione della tabella
        String[] columnNames = {"ID", "Email", "Nome", "Cognome"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // le celle non sono modificabili
            }
        };
        usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        
        // Layout della view
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public JTable getUsersTable() {
        return usersTable;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

}