package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			String query= "INSERT INTO `ShipUp`.`recensione` ('testo')";
			st=conn.prepareStatement(query);
			st.setString(1, r.getRecensione());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		DBConnection.closeConnection(conn);
	}

}
