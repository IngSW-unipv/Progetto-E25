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
import it.unipv.ingsw.view.PagamentoView;
import it.unipv.ingsw.view.UtenteView;

public class PagamentoController {
	private Utente model;
	private UtenteView view; 
	private Spedizione spedizione;
	private PagamentoView pagamentoView;
	private AvviaSpedizioneView avView;
	private UtenteDAO utenteDAO;
	
	public PagamentoController(Utente model, Spedizione spedizione,PagamentoView pagamentoView) {
		this.model=model;
		this.spedizione=spedizione;
		this.pagamentoView=pagamentoView;
		pagaInit();
	}

	private void pagaInit() {
		ActionListener pagaSped=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manageAction();
				} catch (PaymentException e1) {
					// TODO Auto-generated catch block
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
	    int selectedIndex;
	    double saldo =  Double.parseDouble(pagamentoView.getSaldoField().getText());
	    String numeroCarta = pagamentoView.getNumeroCartaFieldInput().getText();
	    System.out.println(saldo);
	    selectedIndex= pagamentoView.getOpzioneMetodoPagamentoField().getSelectedIndex();   
	    System.out.println("Indice selezionato in okPagamentoButton: " + selectedIndex); // Debug per verificare l'indice
	    IPagamento mode = null;

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
	        case 6:
	            // PuntiApp + Carta 
	            mode = PagamentoStrategyFactory.getPagamentoPuntiCartaAdapter(new CompositePuntiCarta());
	            break;
	        default:
	            break;
	    }
	    
	    if (mode != null) {
	        boolean b = false;
	        Pagamento p = new Pagamento(mode);
	        b = p.provaPagamento(model.getSaldo().getDenaro(), model.getSaldo().getPuntiApp(), mode, model);
	        JOptionPane.showMessageDialog(null, "Pagamento eseguito con successo!");
	    }

	    new ProfiloUtenteController(model, new UtenteView()); 
	}
}

