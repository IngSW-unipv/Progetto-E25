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
	private PagamentoView pagamentoView;
	private AvviaSpedizioneView avView;
	private UtenteDAO utenteDAO;
	
	public PagamentoController(Utente model, Spedizione spedizione,PagamentoView pagamentoView, double costoSped) {
		this.model=model;
		this.spedizione=spedizione;
		this.pagamentoView=pagamentoView;
		this.costoSped=costoSped;
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
	    // Ottieni l'indice selezionato dalla JComboBox
	    int selectedIndex;
	    double saldo =  Double.parseDouble(pagamentoView.getSaldoField().getText());
	    String numeroCarta = pagamentoView.getNumeroCartaFieldInput().getText();
	    System.out.println(saldo);
	    selectedIndex= pagamentoView.getOpzioneMetodoPagamentoField().getSelectedIndex();
	    
	    System.out.println("Indice selezionato in okPagamentoButton: " + selectedIndex); // Debug per verificare l'indice

	    // Dichiarazione della variabile mode
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
	            // PuntiApp + Carta (potresti aggiungere altre logiche qui se necessario)
	            mode = PagamentoStrategyFactory.getPagamentoPuntiCartaAdapter(new CompositePuntiCarta());
	            break;
	        default:
	            break;
	    }

	    // Ora che hai la modalità di pagamento, puoi procedere con l'esecuzione dell'operazione
	    if (mode != null) {
	        // Esegui il pagamento
	        boolean b = false;
	        Pagamento p = new Pagamento(mode);
	        b = p.provaPagamento(costoSped, model.getSaldo().getPuntiApp(), mode, model);

	        // Aggiorna la UI per riflettere il pagamento eseguito (opzionale)
	        // Forse una finestra di conferma, un aggiornamento del saldo, ecc.
	        if(selectedIndex == 0 || selectedIndex == 1 || selectedIndex == 3)
	        	JOptionPane.showMessageDialog(null, "Pagamento eseguito con successo!");
	    }

	    new ProfiloUtenteController(model, new UtenteView(model)); 
	}

		          

		//double exW = model.getCl().getWallet();
		
        //double saldo =  Double.parseDouble(pagamentoView.getSaldoField().getText());
       // double saldoUtente =  Double.parseDouble(pagamentoView.getSaldoUtente().getText()); //GUARDO
        //int punti = Integer.parseInt(pagamentoView.getPuntiField().getText());
        //String numeroCarta = pagamentoView.getNumeroCartaFieldInput().getText();
        //String cvv = pagamentoView.getCvvField().getText();
       // double amount = Double.parseDouble(pagamentoView.getAmountField().getText());
       
		/*Pagamento pay=new Pagamento(mode, model.getCl().getEmail(), "shipup"); //getUtente
		IStdPrimePaymentStrategy stdprimestr = StdPrimePaymentFactory.getInstance().getStrategy(model.getCl().getPrime());
		double total = stdprimestr.pay( model.getCart().getTotal() );
		try {
			pay.provaPagamento(total, puntiApp)
			model.makeOrder();
			view.displayInfo("Payment of: "+ total + "euro succesfully ended");
			view.setInfoLabText(model.getCart().getSkuqty().size());						
		} catch (PaymentException ex) {
			view.displayWarn(ex.getMessage());
		} catch (IllegalArgumentException exx) {
			model.getCl().setWallet(exW);
			view.displayWarn(exx.getMessage());
		}
											
			}
				view.setWalletLabImp(model.getCl().getWallet());
			}
		};*/
	}
