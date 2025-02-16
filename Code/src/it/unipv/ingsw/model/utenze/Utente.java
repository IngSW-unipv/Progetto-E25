package it.unipv.ingsw.model.utenze;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Objects;

import it.unipv.ingsw.model.Subject;

public class Utente extends ASuperUser implements Subject{
	private String nome,cognome,numeroTelefono,indirizzoCivico;
	private LocalDate dataNascita;
	private Blob fotoDocumento;
	private boolean statoProfilo;
	
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
	
	public Utente registrazione(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico, LocalDate dataNascita, Blob fotoDocumento) {
        if (nome == null || cognome == null || mail == null || password == null || dataNascita == null || numeroTelefono == null || indirizzoCivico == null || fotoDocumento == null) {
            System.out.println("Tutti i campi sono obbligatori.");
            return null;
        }
        Utente utente = new Utente(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
        System.out.println("Registrazione completata con successo!");
        return utente;
    }
	
	//metodi da implementare
	public void modificaProfilo() {}
	public void cancellaAccount() {}
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
