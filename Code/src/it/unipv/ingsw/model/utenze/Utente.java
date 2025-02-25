package it.unipv.ingsw.model.utenze;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.swing.JOptionPane;

import it.unipv.ingsw.exceptions.AccountAlreadyExistsException;
import it.unipv.ingsw.exceptions.EmptyFieldException;
import it.unipv.ingsw.exceptions.WrongFieldException;
import it.unipv.ingsw.model.Singleton;
import it.unipv.ingsw.model.Subject;

public class Utente extends ASuperUser implements Subject{
	private String nome,cognome,numeroTelefono,indirizzoCivico,dataNascita;
	private String fotoDocumento;
	private Saldo saldo;
	private boolean statoProfilo;
	//map di admin
	private String formatoNome = "^[A-Za-zàèéìòóùçÁÉÍÓÚÑñ ]{1,100}$"; // Consente lettere, accenti e spazi
	private String formatoMail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$"; //formato nomemail@example.com
	private String formatoPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,32}$"; //Contenga almeno una lettera minuscola, maiuscola, un numero, un carattere speciale e una lunghezza compresa tra 8 e 32 caratteri 
	private String formatoNumero = "^\\+\\d{1,3}\\s?\\d{1,4}[\\s-]?\\d{1,4}[\\s-]?\\d{1,4}$";
	private String formatoIndirizzo = "^[A-Za-z0-9\\s,.-]+\\s\\d{1,5}\\s[A-Za-z\\s]+$";
	
	//costruttore
	public Utente(String mail, String password,String nome, String cognome, String dataNascita,String numeroTelefono, String indirizzoCivico, String fotoDocumento) {
		super(mail,password);
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
		this.indirizzoCivico = indirizzoCivico;
		this.dataNascita = dataNascita;
		this.fotoDocumento = fotoDocumento;
		this.statoProfilo = false;
		this.saldo= new Saldo(0.0,0);
	}
	
	//utente non registrato
	public Utente(String mail) {
		super(mail);
	}
	
	
	//getter
	public Utente() {
	}
	
	//getter

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	
	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public String getIndirizzoCivico() {
		return indirizzoCivico;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public String getFotoDocumento() {
		return fotoDocumento;
	}

	public boolean getStatoProfilo() {
		return statoProfilo;
	}
	
	public Saldo getSaldo() {
		return saldo;
	}
	
	//setter

	public void setSaldo(Saldo saldo) {
		this.saldo = saldo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	public void setIndirizzoCivico(String indirizzoCivico) {
		this.indirizzoCivico = indirizzoCivico;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setFotoDocumento(String fotoDocumento) {
		this.fotoDocumento = fotoDocumento;
	}	

	public void setStatoProfilo(boolean statoProfilo) {
		this.statoProfilo = statoProfilo;
	}

	public Utente registrazione(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico, String dataNascita, String fotoDocumento) throws SQLException, IOException, AccountAlreadyExistsException, EmptyFieldException, WrongFieldException  {
		fieldCheck(mail,password,nome,cognome,numeroTelefono,indirizzoCivico,dataNascita,fotoDocumento);
		Utente newUtente=new Utente(mail, password, nome,  cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
        //boolean result = false;
        if (Singleton.getInstance().getSuperUserDAO().getUtenteByEmail((mail)) == null) {
        	Singleton.getInstance().getSuperUserDAO().insertUtente(newUtente);
        	System.out.println("Registrazione completata con successo!");
        	JOptionPane.showMessageDialog(null, "Registrazione completata con successo!", "Benvenuto in ShipUp!", JOptionPane.INFORMATION_MESSAGE);
            //result = true;
        } else {
            throw new AccountAlreadyExistsException();
        }
		if(super.isLoggedIn()){	
			System.out.println("Utente già registrato. Effettua l'accesso!");
			 JOptionPane.showMessageDialog(null,"Utente già registrato. Effettua l'accesso!","Errore",JOptionPane.ERROR_MESSAGE);
			return null;
		}
        return newUtente;
    }   
	
	 public static boolean isPng(String filePath) {
	        return filePath != null && filePath.toLowerCase().endsWith(".png");
	 }
	 
	private void fieldCheck(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico, String dataNascita, String fotoDocumento) throws EmptyFieldException, SQLException, IOException, WrongFieldException {
		if ( mail.isEmpty() || nome.isEmpty() || cognome.isEmpty() || dataNascita.isEmpty() || numeroTelefono.isEmpty() || indirizzoCivico.isEmpty()|| password.equals("") ) //
		{
			throw new EmptyFieldException();
		}
		//if(!nome.matches(formatoNome) || !cognome.matches(formatoNome) || !mail.matches(formatoMail) || !password.matches(formatoPassword) || !numeroTelefono.matches(formatoNumero) || !indirizzoCivico.matches(formatoIndirizzo) || !isPng(fotoDocumento) ) { 
        	//throw new WrongFieldException();
		//}	
	}
	
	public Utente modificaProfilo(String mail,String password,String nome, String cognome, String numeroTelefono, String indirizzoCivico, String dataNascita, String fotoDocumento) { 
		if(!super.isLoggedIn() || !statoProfilo) {
		Utente utenteInAttesa = new Utente(); //parametri passati: null,ferri,34637738,null,...
		if(mail != null && mail.matches(formatoMail) && !super.getMail().equals(mail)) 
			utenteInAttesa.setMail(mail);
		if(password != null && password.matches(formatoPassword) && !super.getPassword().equals(password)) 
			utenteInAttesa.setPassword(password);
		if(nome != null && nome.matches(formatoNome) && !this.nome.equals(nome)) 
			utenteInAttesa.setNome(nome);
		if(cognome != null && cognome.matches(formatoNome) && !this.cognome.equals(cognome)) 
			utenteInAttesa.setCognome(cognome);
		if(numeroTelefono != null && numeroTelefono.matches(formatoNumero) && !this.numeroTelefono.equals(numeroTelefono)) 
			utenteInAttesa.setNumeroTelefono(numeroTelefono);
		if(indirizzoCivico != null && indirizzoCivico.matches(formatoIndirizzo) && !this.indirizzoCivico.equals(indirizzoCivico)) 
			utenteInAttesa.setIndirizzoCivico(indirizzoCivico);
		if(dataNascita != null && !this.dataNascita.equals(dataNascita)) { 
			utenteInAttesa.setDataNascita(dataNascita);
		}
		if(fotoDocumento != null && !this.fotoDocumento.equals(fotoDocumento))  //&& isPngImage(fotoDocumento)
			utenteInAttesa.setFotoDocumento(fotoDocumento);
		//notify()
		return utenteInAttesa; //utente fittizio con modifiche
		}
		else
			return null;
	}
	
	public void cancellaAccount() {
		if(super.isLoggedIn() && statoProfilo) {
			super.setMail(null);
			super.setPassword(null);
			nome=null;
			cognome=null;
			numeroTelefono=null;
			indirizzoCivico=null;
			dataNascita=null;
			fotoDocumento=null;
			//query che cancella utente da DB
		}	
		//Non posso fare questo metodo se non ho un profilo attivo
		super.setMail(null);
		super.setPassword(null);
		nome=null;
		cognome=null;
		numeroTelefono=null;
		indirizzoCivico=null;
		dataNascita=null;
		fotoDocumento=null;
		//query che cancella utente da DB
	}
	
	
	
	public void addObserver() {}
	public boolean removeObserver(){return true;}
	public void notifyObservers() {}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente profilo = (Utente) obj;
		return Objects.equals(cognome, profilo.cognome) && Objects.equals(dataNascita, profilo.dataNascita) && Objects.equals(fotoDocumento, profilo.fotoDocumento) && Objects.equals(numeroTelefono, profilo.numeroTelefono) && Objects.equals(indirizzoCivico, profilo.indirizzoCivico) && Objects.equals(nome, profilo.nome) && statoProfilo == profilo.statoProfilo;
	}
	@Override
	public String toString() {
		return "Utente- Mail:" +super.getMail()+ ", Password:"+super.getPassword()+", Nome:" + nome + ", Cognome:" + cognome + ", Numero di telefono:" +numeroTelefono+ ", Indirizzo civico:" + indirizzoCivico+ ", dataNascita=" + dataNascita;
	}
}
