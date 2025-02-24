package it.unipv.ingsw.model.utenze;

import it.unipv.ingsw.model.spedizione.Spedizione;

public abstract class ASuperUser {
	private String mail,password;
	private static boolean utenteLoggato;
	
	public ASuperUser(String mail,String password) {
		this.mail=mail;
		this.password=password;
		this.utenteLoggato=false;
	}
	
	public ASuperUser(String mail) {
		this.mail=mail;
		this.utenteLoggato=false;
	}
	
	public ASuperUser() {
	}
	
	//getter e setter
    public String getMail() {
		return mail;
	}
    
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean login(String mail, String password) {
        if (this.mail.equals(mail) && this.password.equals(password)) {
            utenteLoggato = true;
            return true;  //login riuscito
        } 
        return false;  //credenziali sbagliate
    }
    
    public boolean logout() {
        if (utenteLoggato) {
            utenteLoggato = false;
            return true;     //logout riuscito
        } 
        return false;   //nessun utente loggato
    }    

    public boolean isLoggedIn() {
        return utenteLoggato;
    }
}
