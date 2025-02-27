package it.unipv.ingsw.model.utenze;

import it.unipv.ingsw.exceptions.EmptyFieldException;
import it.unipv.ingsw.exceptions.WrongAdminException;
import it.unipv.ingsw.exceptions.WrongFieldException;
import it.unipv.ingsw.model.Singleton;
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

	public boolean login(String mail, String password) throws EmptyFieldException, NullPointerException, WrongFieldException{
        if (this.mail.equals(mail) && this.password.equals(password)) {
            utenteLoggato = true;
            return true;  //login riuscito
        } 
        return false;  //credenziali sbagliate
    }
	
	
	public void loginUtente(String email, String password) throws EmptyFieldException, NullPointerException, WrongFieldException {
		campiUtenteCheck(email, password);
        ASuperUser su = Singleton.getInstance().getSuperUserDAO().getUtenteByEmail(email);
        if (su == null) {
            throw new NullPointerException("Utente non valido");
        }
        passwordUtenteCheck(email, password);
        Singleton.getInstance().setUtenteLoggato(su);
        utenteLoggato = true;
    }
	
	private void campiUtenteCheck(String email, String password) throws EmptyFieldException {
		if (email.isEmpty() == true || password.isEmpty() == true) {
			throw new EmptyFieldException();
		}
		
	}
	private void passwordUtenteCheck(String email, String password) throws WrongFieldException {
		ASuperUser su = Singleton.getInstance().getSuperUserDAO().getUtenteByEmail(email);
        String pw= Singleton.getInstance().getSuperUserDAO().selectPassword(su);

        if (!pw.equals(password)) {  
            throw new WrongFieldException();
        }
    }
    
	public void loginAdmin (String id) throws WrongAdminException,EmptyFieldException, WrongFieldException{
		campiAdminCheck(id);	
		ASuperUser su = Singleton.getInstance().getSuperUserDAO().getAdminById(id);
		if(su==null) {
			throw new WrongAdminException();
		} 
		passwordAdminCheck(mail,password);
		Singleton.getInstance().setUtenteLoggato(su);
		utenteLoggato = true;
	}
	
	private void campiAdminCheck(String id) throws EmptyFieldException {
		if(id.isEmpty()==true) {
			throw new EmptyFieldException();
		}
	}
	
	 // method for check if the password and the email are correct
	private void passwordAdminCheck(String email, String password) throws WrongFieldException {
		ASuperUser su = Singleton.getInstance().getSuperUserDAO().getUtenteByEmail(email);
        String pw= Singleton.getInstance().getSuperUserDAO().selectPassword(su);

        if (!pw.equals(password)) {  
            throw new WrongFieldException();
        }
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

    public abstract void inviaNotificaCarrier(String messaggio);

    public abstract void inviaEmailCarrier(String soggetto, String corpo);
    
}
