package it.unipv.ingsw.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.InfoUtenteDaConvalidareView;
import it.unipv.ingsw.view.PanoramicaUtentiView;
import it.unipv.ingsw.view.UtentiDaConvalidareView;


public class PanoramicaUtentiController {
    private PanoramicaUtentiView panoramicaUtentiView;
    private List<Utente> utenti; // in un'app reale, potresti usare un DAO per recuperare i dati
    private UtenteDAO utenteDAO;
    
    public PanoramicaUtentiController(PanoramicaUtentiView view) {
        this.panoramicaUtentiView = view;
        utenteDAO = new UtenteDAO();
        this.utenti = utenteDAO.selectAll();
        riempiTabella();
        initController();
    }
    
    private void riempiTabella() {
        DefaultTableModel model = panoramicaUtentiView.getTableModel();
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
    	panoramicaUtentiView.getUsersTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Se è un doppio click
                if (e.getClickCount() == 2) {
                    int selectedRow = panoramicaUtentiView.getUsersTable().getSelectedRow();
                    if(selectedRow != -1) {
                        int userId = (int) panoramicaUtentiView.getTableModel().getValueAt(selectedRow, 0);
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
    	panoramicaUtentiView.setVisible(true);
    }
    
}