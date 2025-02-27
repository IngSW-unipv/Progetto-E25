package it.unipv.ingsw.model;

import it.unipv.ingsw.controller.*;
import it.unipv.ingsw.view.*;

import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsw.controller.MainController;
import it.unipv.ingsw.controller.ProfiloUtenteController;
import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Destinatario;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Utente;
//import it.unipv.ingsw.view.MainView;
import it.unipv.ingsw.view.ItinerarioCarrierView;

public class Execute {
	
	public static void main(String[] args) {
	
		new MainController(new MainView());
	}
}
