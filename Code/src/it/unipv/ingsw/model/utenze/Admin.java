package it.unipv.ingsw.model.utenze;

import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class Admin extends ASuperUser implements Observer<Utente>{
	private int matricola;
	//costruttore
	public Admin(String mail, String password) {
		super(mail, password);
	}
	
	//costruttore di default
	public Admin() {
	}

	
	//metodi da implementare
	public boolean validaAccount() {return true;}
	//public boolean validaAccount(Utente u1,Utente uAttesa1) {return true;}
	public boolean disattivaAccount() {return true;}
//	public void update(Spedizione spedizione) {
		// TODO Auto-generated method stub
	
	@Override
	public void update(Utente dato) {
		System.out.println("Aggiornato il profilo dell'utente con email: "+dato.getMail());
		
	}
	
	public boolean validaAccount(Utente utente) {
		return true;
	}

	public boolean disattivaAccount(Utente utente) {
		return true;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}


}

