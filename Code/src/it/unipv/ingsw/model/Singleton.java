package it.unipv.ingsw.model;

import it.unipv.ingsw.dao.ISuperUserDAO;
import it.unipv.ingsw.dao.IUtenteDAO;
import it.unipv.ingsw.dao.SuperUserDAO;
import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.model.utenze.ASuperUser;

public class Singleton {

	private static Singleton instance = null;
	private ISuperUserDAO superUserDAO;
	private IUtenteDAO utenteDAO;
	private ASuperUser utenteLoggato;

	// Hide the contructor
	private Singleton() {
		superUserDAO=new SuperUserDAO();
		utenteDAO=new UtenteDAO();
	}

	// Allow construction only once
	public static synchronized Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
			System.out.println("Creata una nuova istanza");
		}
		else
			System.out.println("Istanza gia' disponibile");
		return instance;
	}
	
	public ASuperUser getUtenteLoggato() {
		return utenteLoggato;
	}

	public ISuperUserDAO getSuperUserDAO() {
		return superUserDAO;
	}

	public IUtenteDAO getUtenteDAO() {
		return utenteDAO;
	}

	public void setUtenteDAO(IUtenteDAO utenteDAO) {
		this.utenteDAO = utenteDAO;
	}

	public void setUtenteLoggato(ASuperUser utenteLoggato) {
		this.utenteLoggato = utenteLoggato;
	}

	public static void main(String[] args) {
		
		
		Singleton s=Singleton.getInstance();
		
		System.out.println("do something...");
		
		Singleton s1=Singleton.getInstance();
		
		System.out.println(s+"\n"+s1);

	}

}