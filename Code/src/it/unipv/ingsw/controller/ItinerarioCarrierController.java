package it.unipv.ingsw.controller;

import javax.swing.*;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.Itinerario;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.ItinerarioCarrierView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/*
 * Unused!!! (aspetto di essser sicuro per rimuovere)
 */
public class ItinerarioCarrierController {

    private ItinerarioCarrierView view;
    private Carrier carrier;
    private Itinerario it;

    public ItinerarioCarrierController(ItinerarioCarrierView view, Carrier carrier) {
        this.view = view;
        this.carrier = carrier;
        initController();
        System.out.println("costruttore");
    }
    
    private void initController() {
    	System.out.println("ggggggggggggggg");
        // Registrazione dell'ActionListener sul bottone "Invia"
        view.getSendButton().addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSendButton();
            }
        });
    }
    
    private void handleSendButton() {
        try {
            
        	System.out.println("ffffffffffffff");
            //prendo e converto i valori inseriti nei campi della view
            double startX = Double.parseDouble(view.getStartXField().getText());
            double startY = Double.parseDouble(view.getStartYField().getText());
            double endX = Double.parseDouble(view.getEndXField().getText());
            double endY = Double.parseDouble(view.getEndYField().getText());
            
            
            System.out.println("Punto di partenza: (" + startX + ", " + startY + ")");
            System.out.println("Punto di arrivo: (" + endX + ", " + endY + ")");
            
            MatchingService m = new MatchingService();
			GestoreSpedizioni gs = new GestoreSpedizioni(m);
			
			List<Spedizione> lista = new ArrayList<Spedizione>();
			Spedizione s = new Spedizione();
			lista.add(s);
			
            //passo dati al modello 
            it = new Itinerario(new Coordinate(startX,startY), new Coordinate(endX,endY));
            
            carrier.setItinerario(it);
            
            System.out.println(carrier.getItinerario().toString());
            gs.presaInCaricoSpedizione(carrier, lista);
           
        } catch (NumberFormatException ex) {
            // Se l'input non è numerico, mostra un messaggio di errore
            JOptionPane.showMessageDialog(view, 
                    "Inserisci valori numerici validi!", 
                    "Errore di input", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    

}
