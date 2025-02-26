package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.recensioni.*;
import it.unipv.ingsw.recensioni.*;

public class RecensioniDAO implements IRecensioniDAO{
	private String schema;

	private Connection conn;

	public RecensioniDAO() {
		this.schema = "ShipUp";
	}
	
	@Override
	public void addRecensione(Recensioni r) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st;
		
		try {
			String query= "INSERT INTO `ShipUp`.`recensione` ('testo')"; //non va bene
			st=conn.prepareStatement(query);
			st.setString(1, r.getRecensione()); //errore in questa riga
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		DBConnection.closeConnection(conn);
		System.out.printf("it works");
	}

	
	public static void main(String[] args) {
		Recensioni r1=new Recensioni();
		r1.setRecensione("ciao");
		RecensioniDAO r=new RecensioniDAO();
		r.addRecensione(r1);
	}
}
