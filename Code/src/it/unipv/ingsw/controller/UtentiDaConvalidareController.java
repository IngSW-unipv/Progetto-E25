package it.unipv.ingsw.controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.UtentiDaConvalidareView;
import it.unipv.ingsw.view.InfoUtenteDaConvalidareView;
import it.unipv.ingsw.controller.InfoUtenteDaConvalidareController;
import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.view.UtentiDaConvalidareView;

public class UtentiDaConvalidareController {
    private UtentiDaConvalidareView utentiDaConvalidareView;
    private List<Utente> utenti; // in un'app reale, potresti usare un DAO per recuperare i dati
    private UtenteDAO utenteDAO;
    
    public UtentiDaConvalidareController(UtentiDaConvalidareView view) {
        this.utentiDaConvalidareView = view;
        utenteDAO = new UtenteDAO();
        this.utenti = utenteDAO.utentiDisattivati();
        riempiTabella();
        initController();
    }
    
    private void riempiTabella() {
        DefaultTableModel model = utentiDaConvalidareView.getTableModel();
        // Rimuove eventuali righe esistenti
        model.setRowCount(0);
        for(Utente user : utenti) {
        	System.out.println(user.toString());
            Object[] rowData = {
                user.getIdUtente(),
                user.getMail(),
                user.getNome(),
                user.getCognome()
            };
            model.addRow(rowData);
        }
    }
    
    private void initController() {
        // Aggiunge un listener per il doppio click su una riga della tabella
    	utentiDaConvalidareView.getUsersTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Se è un doppio click
                if (e.getClickCount() == 2) {
                    int selectedRow = utentiDaConvalidareView.getUsersTable().getSelectedRow();
                    if(selectedRow != -1) {
                        int userId = (int) utentiDaConvalidareView.getTableModel().getValueAt(selectedRow, 0);
                        // Cerca l'utente nella lista in base all'ID
                        for(Utente user : utenti) {
                            if(user.getIdUtente() == userId) {
                                new InfoUtenteDaConvalidareController(new InfoUtenteDaConvalidareView(), user); 
                                break;
                            }
                        }
                    }
                }
            }
        });
        
        // Mostra la view
    	utentiDaConvalidareView.setVisible(true);
    }
    
    
    
 
    

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
    		new UtentiDaConvalidareController(new UtentiDaConvalidareView());
        });
    }
   
    
    
 
   
}
