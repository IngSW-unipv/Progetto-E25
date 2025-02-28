package it.unipv.ingsw.controller;

import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.view.AdminView;
import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.PanoramicaUtentiView;
import it.unipv.ingsw.controller.PanoramicaUtentiController;
import it.unipv.ingsw.view.UtentiDaConvalidareView;

import javax.swing.JOptionPane;

public class ProfiloAdminController {
    
    private AdminView adminView;
    private Admin admin;
    
    public ProfiloAdminController(Admin admin, AdminView adminView) {
        this.adminView = adminView;
        this.admin = admin;
        initController();
    }
    
    private void initController() {
        // Aggiunge l'ActionListener per il pulsante Logout
        adminView.getLogout().addActionListener(e -> handleLogout());
        
        // Aggiunge l'ActionListener per il pulsante Convalida profilo utenti in attesa
        adminView.getConvalida().addActionListener(e -> handleConvalidaProfili());
        
        // Aggiunge l'ActionListener per il pulsante Disattiva account carrier
        adminView.getDisattiva().addActionListener(e -> handleDisabilitaCarrier());
        
    }
    
    private void handleLogout() {
        int confirm = adminView.displayConfirm();
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(adminView, "Logout effettuato!");
            adminView.dispose();
            
            new MainController(new MainView());
        }
    }
    
    private void handleConvalidaProfili() {
        
        new UtentiDaConvalidareController(new UtentiDaConvalidareView());
    }
    
    private void handleDisabilitaCarrier() {
        new PanoramicaUtentiController(new PanoramicaUtentiView());
    }
    
    
}
