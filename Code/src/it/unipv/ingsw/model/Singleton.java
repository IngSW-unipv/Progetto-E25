package it.unipv.ingsw.model;

import it.unipv.ingsw.dao.ISuperUserDAO;
import it.unipv.ingsw.dao.SuperUserDAO;
import it.unipv.ingsw.model.utenze.ASuperUser;

public class Singleton {

	private static Singleton instance = null;
	private ISuperUserDAO superUserDAO;
	private ASuperUser utenteLoggato;

	// Hide the contructor
	private Singleton() {
		superUserDAO=new SuperUserDAO();
	}

	// Allow construction only once
	public static synchronized Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
			System.out.println("Create new instance");
		}
		else
			System.out.println("Instance already available");
		return instance;
	}
	

	public ASuperUser getUtenteLoggato() {
		return utenteLoggato;
	}

	public void setUtenteLoggato(ASuperUser utenteLoggato) {
		this.utenteLoggato = utenteLoggato;
	}
	
	public ISuperUserDAO getSuperUserDAO() {
		return superUserDAO;
	}

	public static void main(String[] args) {
		
		
		Singleton s=Singleton.getInstance();
		
		System.out.println("do something...");
		
		Singleton s1=Singleton.getInstance();
		
		System.out.println(s+"\n"+s1);

	}

}