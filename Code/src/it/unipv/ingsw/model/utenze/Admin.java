package it.unipv.ingsw.model.utenze;

import it.unipv.ingsw.model.Observer;
import it.unipv.ingsw.model.spedizione.Spedizione;

public class Admin extends ASuperUser implements Observer<Utente>{
	//costruttore
	public Admin(String mail, String password) {
		super(mail, password);
	}
	
	
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


}

