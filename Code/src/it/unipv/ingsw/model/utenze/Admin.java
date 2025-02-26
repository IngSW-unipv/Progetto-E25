package it.unipv.ingsw.model.utenze;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class Admin extends ASuperUser implements Observer<Utente>{
	private int matricola;
	private List<Utente> utentiDaValidare;
	
	//costruttore
	public Admin(String mail, String password) {
		super(mail, password);
		utentiDaValidare = new ArrayList<>();
	}
	
	//costruttore di default
	public Admin() {
	}

	public Admin(int matricola,String mail, String password) {
		super(mail, password);
		this.matricola=matricola;
	}
	
	
	//metodi da implementare
	public boolean validaAccount() {return true;}
	//public boolean validaAccount(Utente u1,Utente uAttesa1) {return true;}
	public boolean disattivaAccount() {return true;}
//	public void update(Spedizione spedizione) {
		// TODO Auto-generated method stub
	
	@Override
	public void update(Utente dato) {
		System.out.println("il profilo dell'utente con email: '"+dato.getMail()+"' è stato modificato");
		utentiDaValidare.add(dato);
	}
	
	public void validaAccount(Utente utente) {
		utente.setStatoProfilo(true);
	}

	public boolean disattivaAccount(Utente utente) {
		utente.setStatoProfilo(false);
		return true;
	}
	
	public void cancellaAccount(Utente utente) {
		//dao cancellazione utente;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}


}

