package it.unipv.ingsw.model.utenze;

import java.sql.Blob;
import java.time.LocalDate;

public class Mittente extends EndUser{
	private double saldo;
	
	public Mittente(String mail, String password, String nome, String cognome, String numeroTelefono, String indirizzoCivico,LocalDate dataNascita, Blob fotoDocumento) {
		super(mail, password, nome, cognome, numeroTelefono, indirizzoCivico, dataNascita, fotoDocumento);
		saldo=0;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
}
