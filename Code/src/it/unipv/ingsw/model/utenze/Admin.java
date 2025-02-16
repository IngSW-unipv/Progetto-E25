package it.unipv.ingsw.model.utenze;

import it.unipv.ingsw.model.Observer;

public class Admin extends ASuperUser implements Observer{
	
	//costruttore
	public Admin(String mail, String password) {
		super(mail, password);
	}
	
	//metodi da implementare
	public boolean validaAccount() {return true;}
	public boolean disattivaAccount() {return true;}
	public void update() {}

}
