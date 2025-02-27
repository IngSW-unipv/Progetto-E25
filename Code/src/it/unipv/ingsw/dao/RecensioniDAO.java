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
			//String query= "INSERT INTO `ShipUp`.`recensione` ( 'testo', 'costoSpedizione', 'soddisfazioneGenerale', 'tempoSpedizione', 'semplicita', 'compenso', 'affidabilita', 'rapiditaConsegna')"; //non va bene
			String query="INSERT INTO 'ShipUp' VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			st=conn.prepareStatement(query);
			st.setString(1, r.getRecensione()); //errore in questa riga
			st.setInt(2, r.getPunteggioCostoSpedizione());
			st.setInt(3, r.getPunteggioSoddisfazioneGenerale());
			st.setInt(4, r.getPunteggioTempoSpedione());
			st.setInt(5, r.getPunteggioSemplicita());
			st.setInt(6, r.getPunteggioCompenso());
			st.setInt(7, r.getPunetggioAffidabilita());
			st.setInt(8, r.getPunetggioRapiditaConsegna());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		DBConnection.closeConnection(conn);
	//	System.out.printf("it works");
	}

	
	public static void main(String[] args) {
		Recensioni r1=new Recensioni("ciao", 1,2,3,4,5,6,7);
	//	System.out.printf("Recensione: "+ r1.getRecensione()+"\n");
		RecensioniDAO r=new RecensioniDAO();
		r.addRecensione(r1);
	}
}
