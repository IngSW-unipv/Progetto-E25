package it.unipv.ingsw.model;

import java.util.ArrayList;
import java.util.Date;
import it.unipv.ingsw.model.spedizione.*;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;
import it.unipv.ingsw.model.spedizione.shippable.*;
import it.unipv.ingsw.model.utenze.*;
import java.awt.geom.Point2D;

public class Execute {
	
	public static void main(String[] args) {
		Utente u1=new Utente("CarloRosso@gmail.com",null,"Carlo","Rossi","34523762386","Via De MHB",null,null);
		Utente u2=new Utente();
		Admin a1= new Admin("mail","psw");
		Utente uAttesa1=u1.modificaProfilo("CarloRossi@gmail.com",null,"Carli","Rosso!","345237623856",null,null,null);
		//a1.validaAccount(Utente u1,Utente uAttesa1);
		
		Coordinate punto= new Coordinate(10,10);
		IPuntoDeposito p= new Locker(punto,15, null, null);
		
		ArrayList<Locker>lockers = new ArrayList<>();
		
		Spedizione s = new Spedizione(null, null, null, 0, p, lockers); 
		Spedizione s2 = new Spedizione(null, null, null, 0, p, lockers); 
		IShippable p1 = new Pacco(Size.L,1.2);
		IShippable p2 = new Pacco(Size.XL,1.2);
		Locker lf = new Locker(null, 3, s, null);
		Scompartimento sc= new Scompartimento(15, Size.S);
		sc.setOccupato(false);
		Scompartimento sc2= new Scompartimento(16, Size.XL);
		sc2.setOccupato(false);
		Scompartimento sc3= new Scompartimento(17, Size.L);
		sc3.setOccupato(false);
		
		Utente dest = new Utente("utente2@mail.com");
		Utente u = new Utente("utente1@mail.com", null, null, null, null, null, null, null);
		
		
	//prova aggiunta scompartimenti
		lf.aggiungiScompartimento(15,sc);
		lf.aggiungiScompartimento(16,sc2);
		lf.aggiungiScompartimento(17,sc3);
		
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
		Locker locker1 = new Locker(null, 1, s);
		Locker locker2 = new Locker(null, 2, s);
		//aggiunta scompartimenti
		Scompartimento sc4 = new Scompartimento(20, Size.M);
		Scompartimento sc5 = new Scompartimento(15, Size.S);
		//aggiunta scomp al locker
		locker1.aggiungiScompartimento(1, sc4);
		locker1.aggiungiScompartimento(2, sc5);
		//apri scompartimento
		locker1.getScompartimento(1).Open();
		System.out.println("Apertura dello scompartimento con ID 1 nel locker 1:");
		locker1.getScompartimento(2).Open();
		System.out.println("Apertura dello scompartimento con ID 2 nel locker 1:");
		
	}
}
