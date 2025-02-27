package it.unipv.ingsw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.transazioni.CompositePuntiCarta;
import it.unipv.ingsw.model.transazioni.CompositePuntiSaldo;
import it.unipv.ingsw.model.transazioni.CompositeSaldoCarta;
import it.unipv.ingsw.model.transazioni.IPagamento;
import it.unipv.ingsw.model.transazioni.Pagamento;
import it.unipv.ingsw.model.transazioni.PagamentoCarta;
import it.unipv.ingsw.model.transazioni.PagamentoPuntiApp;
import it.unipv.ingsw.model.transazioni.PagamentoSaldo;
import it.unipv.ingsw.model.transazioni.PagamentoStrategyFactory;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.AvviaSpedizioneView;
import it.unipv.ingsw.view.ModificaProfiloView;
import it.unipv.ingsw.view.PagamentoView;
import it.unipv.ingsw.view.UtenteView;

public class PagamentoController {
    private Utente model;
    private UtenteView view; 
    private Spedizione spedizione;
    private double costoSped;
    private int costoPunti;
    private PagamentoView pagamentoView;
    private AvviaSpedizioneView avView;
    private UtenteDAO utenteDAO;
    
    public PagamentoController(Utente model, Spedizione spedizione, PagamentoView pagamentoView, double costoSped, int costoPunti) {
        this.model = model;
        this.spedizione = spedizione;
        this.pagamentoView = pagamentoView;
        this.costoSped = costoSped;
        this.costoPunti = costoPunti;
        pagaInit();
    }

    private void pagaInit() {
        ActionListener pagaSped = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    manageAction();
                } catch (PaymentException e1) {
                    e1.printStackTrace();
                }
            }
            private void manageAction() throws PaymentException {
                okPagamentoButton();
            }
        };    
        pagamentoView.getPayButton().addActionListener(pagaSped);
    }
    
    private void okPagamentoButton() throws PaymentException {
        ActionListener okListener = new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	System.out.println("entro 1 volta");
                manageAction();
            }

            private void manageAction() {
                // Ottieni l'indice selezionato dalla JComboBox
            	System.out.println("1");
                int selectedIndex;
                double saldo = Double.parseDouble(pagamentoView.getSaldoField().getText());
                String numeroCarta = pagamentoView.getNumeroCartaFieldInput().getText();
                selectedIndex = pagamentoView.getOpzioneMetodoPagamentoField().getSelectedIndex();
                
                try {
                    // Dichiarazione della variabile mode
                    IPagamento mode = null;
                    System.out.println("2");
                    // Switch per selezionare la modalità di pagamento in base alla selezione
                    switch (selectedIndex) {
                        case 0:
                            // Solo saldo
                            mode = PagamentoStrategyFactory.getPagamentoSaldoAdapter(new PagamentoSaldo());
                            break;
                        case 1:
                            // Solo puntiApp
                            mode = PagamentoStrategyFactory.getPagamentoPuntiAppAdapter(new PagamentoPuntiApp());
                            break;
                        case 2:
                            // Solo carta
                            mode = PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta());
                            break;
                        case 3:
                            // Saldo + PuntiApp
                            mode = PagamentoStrategyFactory.getPagamentoPuntiSaldoAdapter(new CompositePuntiSaldo());
                            break;
                        case 4:
                            // PuntiApp + Carta
                            mode = PagamentoStrategyFactory.getPagamentoPuntiCartaAdapter(new CompositePuntiCarta());
                            break;
                        case 5:
                            // Saldo + Carta
                            mode = PagamentoStrategyFactory.getPagamentoSaldoCartaAdapter(new CompositeSaldoCarta());
                            break;
                        default:
                            break;
                    }

                    // Esegui il pagamento se mode non è nullo
                    if (mode != null) {
                        boolean b = false;
                        Pagamento p = new Pagamento(mode);
                        b = p.provaPagamento(costoSped, costoPunti, mode, model);
                        
                        // Aggiorna la UI per riflettere il pagamento eseguito (opzionale)
                        if(selectedIndex == 0 || selectedIndex == 1 || selectedIndex == 3)
    			        	JOptionPane.showMessageDialog(null, "Pagamento eseguito con successo!");
                    }
                    // Reindirizzamento alla view UtenteView
                    // Non chiudere il programma, solo cambia la view
                    new ProfiloUtenteController(model, new UtenteView(model));  // Crea la nuova view senza chiudere il programma
                    
                    // Importante: aggiorna anche la logica se necessario per visualizzare correttamente la nuova view
                    pagamentoView.setVisible(false); // Nascondi la view del pagamento se necessario
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pagamentoView, "Errore nel pagamento: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        pagamentoView.getPayButton().addActionListener(okListener);
    }
}
