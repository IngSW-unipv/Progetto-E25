package it.unipv.ingsw.controller;

import javax.swing.*;

import it.unipv.ingsw.model.utenze.Carrier;
import it.unipv.ingsw.view.CarrierView;

public class CarrierController {
    private CarrierView carrierView;
    private Carrier carrier; // Modello carrier, già definito altrove
    
    public CarrierController(CarrierView carrierView, Carrier carrier) {
        this.carrierView = carrierView;
        this.carrier = carrier;
        
        initController();
    }
    
    private void initController() {
        // Rende visibile la view
        carrierView.setVisible(true);
        
        // Aggiunge l'ActionListener al bottone per accettare le spedizioni
        carrierView.getAcceptShipmentsButton().addActionListener(e -> acceptShipments());
    }
    
    private void acceptShipments() {
        // Implementa qui la logica per accettare le spedizioni
        // Ad esempio, si potrebbe aggiornare lo stato delle spedizioni o interagire con il modello.
        JOptionPane.showMessageDialog(carrierView, "Spedizioni accettate!", "Informazione", JOptionPane.INFORMATION_MESSAGE);
    }
}
