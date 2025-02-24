package it.unipv.ingsw.model;


import java.util.ArrayList;

import it.unipv.ingsw.controller.MainController;
import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.QRcode;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Scompartimento;
import it.unipv.ingsw.model.spedizione.shippable.Size;
import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.view.MainView;

public class Execute {
	
	public static void main(String[] args) {

		new MainController(new MainView());
		Utente u1=new Utente("CarloRosso@gmail.com",null,"Carlo","Rossi","34523762386","Via De MHB",null,null);
		Utente u2=new Utente();
		Admin a1= new Admin("mail","psw");
		Utente uAttesa1=u1.modificaProfilo("CarloRossi@gmail.com",null,"Carli","Rosso!","345237623856",null,null,null);
		//a1.validaAccount(Utente u1,Utente uAttesa1);
		
		Coordinate punto= new Coordinate(10,10);
		IPuntoDeposito p= new Locker(punto,15);
		
		ArrayList<Locker>lockers = new ArrayList<>();
		
		Spedizione s = new Spedizione(null, null, null, 0, p, p, null); 
		Spedizione s2 = new Spedizione(null, null, null, 0, p, p, null); 
		IShippable p1 = new Pacco(Size.L,1.2);
		IShippable p2 = new Pacco(Size.XL,1.2);
		Locker lf = new Locker(null, 3);
		Scompartimento sc= new Scompartimento(15, Size.S);
		sc.setOccupato(false);
		Scompartimento sc2= new Scompartimento(16, Size.XL);
		sc2.setOccupato(false);
		Scompartimento sc3= new Scompartimento(17, Size.L);
		sc3.setOccupato(false);
		
		Utente dest = new Utente("utente2@mail.com");
		Utente u = new Utente("utente1@mail.com", null, null, null, null, null, null, null);
		
		
	//prova aggiunta scompartimenti
		lf.aggiungiScompartimento(sc);
		lf.aggiungiScompartimento(sc2);
		lf.aggiungiScompartimento(sc3);
		
		QRcode q=new QRcode();
		q.generaQRcode();
	//prova avvio spedizione
		s.setPacco(p1);
		s.avvioSpedizione(u, lf, dest);
		s.confermaAvvioSpedizione(lf,s.getCodice());
		
		s2.setPacco(p2);
		s2.avvioSpedizione(u, lf, dest);
		s2.confermaAvvioSpedizione(lf,q);
		
		
		
//		checkQR()

		
	}
}
