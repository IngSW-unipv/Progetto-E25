package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LockerPaccoDAO {
	
	private String schema;
	private Connection conn;
	
	
	public LockerPaccoDAO(Connection conn) {
		this.schema = "ShipUp";
		this.conn = conn;
	}
	
	 // Inserisce un'associazione tra locker e spedizione
	 public boolean inserisciLockerPacco(int idLocker, int idSpedizione) {
	        String query = "INSERT INTO locker_pacco (IDlocker, IDspedizione) VALUES (?, ?)";
	        try (PreparedStatement st = conn.prepareStatement(query)) {
	            st.setInt(1, idLocker);
	            st.setInt(2, idSpedizione);
	            return st.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	// Ottiene l'ID del locker associato a una spedizione
	    public Integer getLockerBySpedizione(int idSpedizione) {
	        String query1 = "SELECT IDlocker FROM locker_pacco WHERE IDspedizione = ?";
	        try (PreparedStatement st1 = conn.prepareStatement(query1)) {
	            st1.setInt(1, idSpedizione);
	            ResultSet rs = st1.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("IDlocker");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	 // Ottiene l'ID della spedizione associata a un locker
	    public Integer getSpedizioneByLocker(int idLocker) {
	        String query2 = "SELECT IDspedizione FROM locker_pacco WHERE IDlocker = ?";
	        try (PreparedStatement st2 = conn.prepareStatement(query2)) {
	            st2.setInt(1, idLocker);
	            ResultSet rs = st2.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("IDspedizione");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	 // Rimuove un'associazione tra locker e spedizione
	    public boolean rimuoviLockerPacco(int idLocker, int idSpedizione) {
	        String query3 = "DELETE FROM locker_pacco WHERE IDlocker = ? AND IDspedizione = ?";
	        try (PreparedStatement st3 = conn.prepareStatement(query3)) {
	            st3.setInt(1, idLocker);
	            st3.setInt(2, idSpedizione);
	            return st3.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	public static void main1(String[] args) {
	    try {
	        Connection conn = DBConnection.getConnection();
	        if (conn != null) {
	            System.out.println("Connessione al database riuscita in LockerPaccoDAO!");
	            conn.close();
	        } else {
	            System.out.println("Errore nella connessione in LockerPaccoDAO!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
