package it.unipv.ingsw.model;

import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;

public class Execute {
	public static void main(String[] args){
	Utente u1=new Utente("CarloRosso@gmail.com",null,"Carlo","Rossi","34523762386","Via De MHB",null,null);
	Utente u2=new Utente();
	Admin a1= new Admin("mail","psw");
	Utente uAttesa1=u1.modificaProfilo("CarloRossi@gmail.com",null,"Carli","Rosso!","345237623856",null,null,null);
	System.out.println(uAttesa1.toString());
	
	}
}
