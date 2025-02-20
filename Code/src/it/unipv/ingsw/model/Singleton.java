package it.unipv.ingsw.model;

import it.unipv.ingsw.dao.IUtenteDAO;
import it.unipv.ingsw.dao.UtenteDAO;
import it.unipv.ingsw.model.utenze.Utente;

public class Singleton {

	private static Singleton instance = null;
	private IUtenteDAO utenteDAO;
	private Utente utenteLoggato;

	// Hide the contructor
	private Singleton() {
		utenteDAO=new UtenteDAO();
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
	

	public Utente getUtenteLoggato() {
		return utenteLoggato;
	}

	public void setUtenteLoggato(Utente utenteLoggato) {
		this.utenteLoggato = utenteLoggato;
	}
	
	public IUtenteDAO getUtenteDAO() {
		return utenteDAO;
	}

	public static void main(String[] args) {
		
		
		Singleton s=Singleton.getInstance();
		
		System.out.println("do something...");
		
		Singleton s1=Singleton.getInstance();
		
		System.out.println(s+"\n"+s1);

	}

}