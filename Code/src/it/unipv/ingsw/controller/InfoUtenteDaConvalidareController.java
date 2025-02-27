package it.unipv.ingsw.controller;

import javax.swing.*;

import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.controller.UtentiDaConvalidareController;
import it.unipv.ingsw.view.InfoUtenteDaConvalidareView;

public class InfoUtenteDaConvalidareController {
    private InfoUtenteDaConvalidareView infoUtenteDaConvalidareView;
    private Utente utente;
    
    public InfoUtenteDaConvalidareController(InfoUtenteDaConvalidareView view, Utente utente) {
        this.infoUtenteDaConvalidareView = view;
        this.utente = utente;
        initController();
    }
    
    private void initController() {
        // Imposta i dettagli dell'utente nella view
    	infoUtenteDaConvalidareView.setUserDetails(utente);
        
        // Aggiunge gli ActionListener ai bottoni
    	infoUtenteDaConvalidareView.getValidateButton().addActionListener(e -> validateUser());
    	infoUtenteDaConvalidareView.getInvalidateButton().addActionListener(e -> invalidateUser());
        
    	infoUtenteDaConvalidareView.setVisible(true);
    }
    
    private void validateUser() {
        // Logica per convalidare l'utente
        utente.setStatoProfilo(true);
        JOptionPane.showMessageDialog(infoUtenteDaConvalidareView, "Profilo convalidato!");
        infoUtenteDaConvalidareView.dispose();
    }
    
    private void invalidateUser() {
        // Logica per non convalidare l'utente
        utente.setStatoProfilo(false);
        JOptionPane.showMessageDialog(infoUtenteDaConvalidareView, "Profilo non convalidato!");
        infoUtenteDaConvalidareView.dispose();
    }
}
