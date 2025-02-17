package it.unipv.ingsw.model.utenze;

public abstract class ASuperUser {
	private String mail,password;
	private static boolean utenteLoggato;
	
	protected ASuperUser(String mail,String password) {
		this.mail=mail;
		this.password=password;
		this.utenteLoggato=false;
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
