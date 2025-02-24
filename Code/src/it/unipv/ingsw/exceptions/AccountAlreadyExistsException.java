package it.unipv.ingsw.exceptions;

public class AccountAlreadyExistsException extends Exception{
	
	public AccountAlreadyExistsException() {
        super("Account gia' esistente");
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }
	
}