package it.unipv.ingsw.model;

import it.unipv.ingsw.model.spedizione.*;

import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Scompartimento;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.shippable.Size;
import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

public class Execute {
	
	public static void main(String[] args) {
		Utente u1=new Utente("CarloRosso@gmail.com",null,"Carlo","Rossi","34523762386","Via De MHB",null,null);
		Utente u2=new Utente();
		Admin a1= new Admin("mail","psw");
		Utente uAttesa1=u1.modificaProfilo("CarloRossi@gmail.com",null,"Carli","Rosso!","345237623856",null,null,null);
		//a1.validaAccount(Utente u1,Utente uAttesa1);
		Spedizione s = new Spedizione(null, null, null, 0, null);
		IShippable p1 = new Pacco(Size.S,1.2);
		Locker lf = new Locker(null, 3);
		Scompartimento sc= new Scompartimento(15, Size.M);
		sc.setOccupato(false);
		Scompartimento sc2= new Scompartimento(16, Size.S);
		sc2.setOccupato(false);
		Scompartimento sc3= new Scompartimento(17, Size.S);
		sc3.setOccupato(false);
		
		Utente dest = new Utente("utente2@mail.com");
		
		Utente u = new Utente("utente1@mail.com", null, null, null, null, null, null, null);
		
		
	//	s.avvioSpedizione(u, lf, dest);
		lf.aggiungiScompartimento(sc);
		lf.aggiungiScompartimento(sc2);
		lf.aggiungiScompartimento(sc3);
		
		s.setPacco(p1);
		s.avvioSpedizione(u, lf, dest);
		
//		checkQR()
		Locker locker1 = new Locker(null, 1);
		Locker locker2 = new Locker(null, 2);
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
