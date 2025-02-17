package it.unipv.ingsw.model.utenze;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.unipv.ingsw.model.Subject;

public class Utente extends ASuperUser implements Subject{
	private String nome,cognome,numeroTelefono,indirizzoCivico;
	private LocalDate dataNascita;
	private Blob fotoDocumento;
	private boolean statoProfilo;
	protected String formatoNome = "^[A-Za-zàèéìòóùçÁÉÍÓÚÑñ ]{1,100}$"; // Consente lettere, accenti e spazi
	protected String formatoMail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$";
	protected String formatoPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,32}$"; //Contenga almeno una lettera minuscola ([a-z]), almeno una lettera maiuscola ([A-Z]), almeno un numero (\\d), almeno un carattere speciale ([!@#$%^&*]), lunghezza compresa tra 8 e 32 caratteri
	protected String formatoData = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"; //formato YYYY-MM-GG 
	protected String formatoNumero = "^\\+\\d{1,3}\\s?\\d{1,4}[\\s-]?\\d{1,4}[\\s-]?\\d{1,4}$";
	protected String formatoIndirizzo = "^[A-Za-z0-9\\s,.-]+\\s\\d{1,5}\\s[A-Za-z\\s]+$";
	
	//costruttore
	protected Utente(String mail, String password,String nome, String cognome,String numeroTelefono, String indirizzoCivico, LocalDate dataNascita, Blob fotoDocumento) {
		super(mail,password);
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
		this.indirizzoCivico = indirizzoCivico;
		this.dataNascita = dataNascita;
		this.fotoDocumento = fotoDocumento;
		this.statoProfilo = false;
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

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public Blob getFotoDocumento() {
		return fotoDocumento;
	}

	public boolean isStatoProfilo() {
		return statoProfilo;
	}
	
	//setter
	
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

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setFotoDocumento(Blob fotoDocumento) {
		this.fotoDocumento = fotoDocumento;
	}	

	public void setStatoProfilo(boolean statoProfilo) {
		this.statoProfilo = statoProfilo;
	}
	
	private boolean isPngImage(Blob imageBlob) throws SQLException, IOException {
        // Leggi i byte dal BLOB
        byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());

        // I primi 8 byte di un file PNG sono univoci
        byte[] pngMagicBytes = { (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47,
                                 (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A };

        // Confronta i primi 8 byte del BLOB con i byte del PNG
        for (int i = 0; i < pngMagicBytes.length; i++) {
            if (imageBytes[i] != pngMagicBytes[i]) {
                return false; 
            }
        }
        return true; //PNG
    }
	
	public Utente registrazione(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico, String data, Blob fotoDocumento) throws SQLException, IOException {
		if (nome == null || cognome == null || mail == null || password == null || dataNascita == null || numeroTelefono == null || indirizzoCivico == null || fotoDocumento == null) {
            System.out.println("Tutti i campi sono obbligatori.");
            return null;
        }
        if(!nome.matches(formatoNome) || !cognome.matches(formatoNome) || !mail.matches(formatoMail) || !password.matches(formatoPassword) || !data.matches(formatoData) || !numeroTelefono.matches(formatoNumero) || !indirizzoCivico.matches(formatoIndirizzo) || !isPngImage(fotoDocumento)) { 
        	System.out.println("Uno dei campi non rispetta le regole");
        	return null;
		}	
        LocalDate dataNascita = LocalDate.parse(data);
        Utente nuovo_utente = new Utente(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
        System.out.println("Registrazione completata con successo!");
        return nuovo_utente;
    }   
    
	public boolean deleteFotoDocumento(Blob fotoDocumento) {
		this.fotoDocumento = null;
		return true;
	}

	//metodi da implementare
	public void modificaProfilo() {}
	//public void cancellaAccount() {}
	public void addObserver() {}
	public boolean removeObserver(){return true;}
	public void notify_() {}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente profilo = (Utente) obj;
		return Objects.equals(cognome, profilo.cognome) && Objects.equals(dataNascita, profilo.dataNascita)
				&& Objects.equals(fotoDocumento, profilo.fotoDocumento)
				&& Objects.equals(numeroTelefono, profilo.numeroTelefono) && Objects.equals(indirizzoCivico, profilo.indirizzoCivico) && Objects.equals(nome, profilo.nome)
				&& statoProfilo == profilo.statoProfilo;
	}
	@Override
	public String toString() {
		return "Utente- Nome:" + nome + ", Cognome:" + cognome + ", Numero di telefono:" +numeroTelefono+ ", Indirizzo civico:" + indirizzoCivico
				+ ", dataNascita=" + dataNascita;
	}
}
