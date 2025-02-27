package it.unipv.ingsw.view;

import javax.swing.*;

import it.unipv.ingsw.model.utenze.Utente;

import java.awt.*;

public class InfoUtenteDaConvalidareView extends JFrame {
    private JLabel idLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel emailLabel;
    private JLabel statoProfiloLabel;
    private JLabel dataNascitaLabel;
    private JLabel numeroTelefonoLabel;
    private JLabel indirizzoCivicoLabel;
    private JLabel fotoDocumentoLabel;
    private JButton validateButton;
    private JButton invalidateButton;
    
    public InfoUtenteDaConvalidareView() {
        setTitle("Dettagli Utente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Pannello per i dettagli
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        
        idLabel = new JLabel();
        emailLabel = new JLabel();
        nomeLabel = new JLabel();
        cognomeLabel= new JLabel();
        dataNascitaLabel= new JLabel();
        numeroTelefonoLabel= new JLabel();
        indirizzoCivicoLabel= new JLabel();
        fotoDocumentoLabel= new JLabel();
        statoProfiloLabel = new JLabel();
        
        detailsPanel.add(idLabel);
        detailsPanel.add(emailLabel);
        detailsPanel.add(nomeLabel);
        detailsPanel.add(cognomeLabel);
        detailsPanel.add(dataNascitaLabel);
        detailsPanel.add(numeroTelefonoLabel);
        detailsPanel.add(fotoDocumentoLabel);
        detailsPanel.add(statoProfiloLabel);
        
        // Pannello per i bottoni
        JPanel buttonsPanel = new JPanel();
        validateButton = new JButton("Convalida Profilo");
        invalidateButton = new JButton("Non convalidare Profilo");
        buttonsPanel.add(validateButton);
        buttonsPanel.add(invalidateButton);
        
        // Layout finale
        setLayout(new BorderLayout());
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    public void setUserDetails(Utente user) {
    	idLabel.setText("ID:   " + user.getIdUtente());
    	emailLabel.setText("email:   " + user.getMail());
    	nomeLabel.setText("nome:   " + user.getNome());
    	cognomeLabel.setText("cognome:   " + user.getCognome());
    	dataNascitaLabel.setText("data di nascita:   " + user.getDataNascita());
    	numeroTelefonoLabel.setText("numero di telefono:   " + user.getNumeroTelefono());
    	indirizzoCivicoLabel.setText("indirizzo civico:   " + user.getIndirizzoCivico());
    	fotoDocumentoLabel.setText("foto documento:   " + user.getFotoDocumento());
    	statoProfiloLabel.setText("stato profilo:   " + (user.getStatoProfilo() ? "Convalidato" : "Non convalidato"));

    }
    
    public JButton getValidateButton() {
        return validateButton;
    }
    
    public JButton getInvalidateButton() {
        return invalidateButton;
    }

}
