package it.unipv.ingsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import it.unipv.ingsw.dao.ISpedizioneDAO;
import it.unipv.ingsw.dao.SpedizioneDAO;
import it.unipv.ingsw.model.spedizione.GestoreSpedizioni;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.view.CarrierView;

public class CarrierController {
    private CarrierView carrierView;
    private Carrier carrier; // il modello Carrier, già definito altrove
    private MatchingService ms;
    private GestoreSpedizioni gs;
    private List<Spedizione> spedizioniCompatibili;

    public CarrierController(CarrierView carrierView, Carrier carrier) {
        this.carrierView = carrierView;
        this.carrier = carrier;
        this.ms = new MatchingService();
        this.gs = new GestoreSpedizioni(ms);
        
        
        initController();
        loadShipments();
    }
    
    private void initController() {
        //mostro view
        carrierView.setVisible(true);
        
        //listener per il bottone
        carrierView.getAcceptShipmentsButton().addActionListener(e -> acceptShipments());
    }
    
    private void loadShipments() {
    	
//    	System.out.println("inizio-fine itinerario carrier::::");
//    	System.out.println("("+carrier.getItinerario().getInizio().getLongitudine()+","+carrier.getItinerario().getInizio().getLatitudine()+")");
//    	System.out.println("("+carrier.getItinerario().getFine().getLongitudine()+","+carrier.getItinerario().getFine().getLatitudine()+")");
    	spedizioniCompatibili = gs.presaInCaricoSpedizione(carrier);
    	
        DefaultTableModel tableModel = (DefaultTableModel) carrierView.getShipmentsTable().getModel();
        //rimuove eventuali righe esistenti
        tableModel.setRowCount(0);
        //aggiungo ogni spedizione come nuova riga nella tabella
        for (Spedizione sped : spedizioniCompatibili) {
            Object[] rowData = {
                sped.getIDSpedizione(),
                "("+sped.getItinerarioCorrente().getInizio().getLongitudine()+","+sped.getItinerarioCorrente().getInizio().getLatitudine()+")",
                "("+sped.getItinerarioCorrente().getFine().getLongitudine()+","+sped.getItinerarioCorrente().getFine().getLatitudine()+")",
                (double) Math.round(sped.getItinerarioCorrente().getInizio().distanza(sped.getItinerarioCorrente().getFine()) * 100) / 100 //formula per arrotondare a 2 decimali
                
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void acceptShipments() {
        //logica per accettare le spedizioni
    	gs.accettaPresaInCarico(carrier, spedizioniCompatibili);
    	//verifica 
    	for (Spedizione sped : carrier.getSpedizioniAssegnate()) {
    		System.out.println("id"+sped.getIDSpedizione()+
                " ("+sped.getItinerarioCorrente().getInizio().getLongitudine()+","+sped.getItinerarioCorrente().getInizio().getLatitudine()+") "+
                " ("+sped.getItinerarioCorrente().getFine().getLongitudine()+","+sped.getItinerarioCorrente().getFine().getLatitudine()+") "+
                (double) Math.round(sped.getItinerarioCorrente().getInizio().distanza(sped.getItinerarioCorrente().getFine()) * 100) / 100); //formula per arrotondare a 2 decimali
             
        }
        JOptionPane.showMessageDialog(carrierView, "Spedizioni accettate!\nRiceverai una mail con i QRcode per il ritiro e la consegna dei pacchi", "Informazione", JOptionPane.INFORMATION_MESSAGE);
        carrierView.dispose();
    }
}