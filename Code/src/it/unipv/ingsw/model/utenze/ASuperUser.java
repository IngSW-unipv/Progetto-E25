package it.unipv.ingsw.model.utenze;

public abstract class ASuperUser {
	private String mail,password;
	private static boolean utenteLoggato;
	
	protected ASuperUser(String mail,String password) {
		this.mail=mail;
		this.password=password;
		this.utenteLoggato=false;
	}
	
    public static boolean login(String mail, String password) {
        if (mail.equals(mail) && password.equals(password)) {
            utenteLoggato = true;
            return true;  //login riuscito
        } 
        return false;  //credenziali sbagliate
    }
    
    public static boolean logout() {
        if (utenteLoggato) {
            utenteLoggato = false;
            return true;     //logout riuscito
        } 
        return false;   //nessun utente loggato
    }    

    public static boolean isLoggedIn() {
        return utenteLoggato;
    }
}
