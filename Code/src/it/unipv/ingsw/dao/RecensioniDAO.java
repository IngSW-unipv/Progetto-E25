package it.unipv.ingsw.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import it.unipv.ingsw.model.utenze.Utente;
import it.unipv.ingsw.recensioni.*;
import it.unipv.ingsw.recensioni.*;
import it.unipv.ingsw.model.spedizione.*;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.spedizione.puntoDeposito.*;

public class RecensioniDAO implements IRecensioniDAO{
	private String schema;

	private Connection conn;

	public RecensioniDAO() {
		this.schema = "ShipUp";
	}

	
	@Override
	public void addRecensione(Recensioni r, int i) {
		
		conn = DBConnection.startConnection(conn);
		
		if (conn != null) {
		    System.out.println("Connessione al database riuscita!");
		} else {
		    System.out.println("Connessione al database fallita!");
		}
		
		PreparedStatement st;
		
		
		
		try {
			String query= "INSERT INTO `ShipUp`.`recensione` (`IDspedizione`,`comoditaLocker`, `testo`, `costoSpedizione` , `soddisfazioneGenerale`, `tempoSpedizione`, `semplicita`, `compenso`, `affidabilita`, `rapiditaConsegna`)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
			st=conn.prepareStatement(query);
			st.setInt(1, i);
			st.setInt(2, r.getPunteggioComoditaLocker());
			st.setString(3, r.getRecensione()); 
			st.setInt(4, r.getPunteggioCostoSpedizione());
			st.setInt(5, r.getPunteggioSoddisfazioneGenerale());
			st.setInt(6, r.getPunteggioTempoSpedione());
			st.setInt(7, r.getPunteggioSemplicita());
			st.setInt(8, r.getPunteggioCompenso());
			st.setInt(9, r.getPunetggioAffidabilita());
			st.setInt(10, r.getPunetggioRapiditaConsegna());
			st.execute();
		//	conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		DBConnection.closeConnection(conn);
	//	System.out.printf("it works");
	}

	
	public static void main(String[] args) {
		
		Coordinate a = new Coordinate(4,5);
		Coordinate b = new Coordinate(7,-2);
		IPuntoDeposito l1 = new Locker(a,1);
		IPuntoDeposito l2 = new Locker(b,2);
		
		Recensioni r1=new Recensioni("ciao",1, 1,2,3,4,5,3,4);
		Spedizione s=new Spedizione(4, null, l1, l2);
	//	System.out.printf("Recensione: "+ r1.getRecensione()+"\n");
		RecensioniDAO r=new RecensioniDAO();
		r.addRecensione(r1, 4);
	}
}
