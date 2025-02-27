package it.unipv.ingsw.controller;

import javax.swing.*;

import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.controller.UtentiDaConvalidareController;
import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.view.InfoUtenteDaConvalidareView;

public class InfoUtenteDaConvalidareController {
    private InfoUtenteDaConvalidareView infoUtenteDaConvalidareView;
    private Utente utente;
    private UtenteDAO utenteDAO;
    
    public InfoUtenteDaConvalidareController(InfoUtenteDaConvalidareView view, Utente utente) {
    	utenteDAO = new UtenteDAO();
        this.infoUtenteDaConvalidareView = view;
        this.utente = utente;
        initController();
    }
    
    private void initController() {
    	infoUtenteDaConvalidareView.setUserDetails(utente);
        
    	infoUtenteDaConvalidareView.getValidateButton().addActionListener(e -> validateUser());
    	infoUtenteDaConvalidareView.getInvalidateButton().addActionListener(e -> invalidateUser());
        
    	infoUtenteDaConvalidareView.setVisible(true);
    }
    
    private void validateUser() {
        utente.setStatoProfilo(true);
        utenteDAO.impostaStatoUtente(utente,true);
        JOptionPane.showMessageDialog(infoUtenteDaConvalidareView, "Profilo convalidato!");
        infoUtenteDaConvalidareView.dispose();
    }
    
    private void invalidateUser() {
        utente.setStatoProfilo(false);
        utenteDAO.impostaStatoUtente(utente,false);
        JOptionPane.showMessageDialog(infoUtenteDaConvalidareView, "Profilo non convalidato!");
        infoUtenteDaConvalidareView.dispose();
    }
}
