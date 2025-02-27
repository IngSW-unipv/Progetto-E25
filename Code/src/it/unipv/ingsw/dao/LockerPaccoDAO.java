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
	
	
	public LockerPaccoDAO() {
		this.schema = "ShipUp";
	}
	
	

	public void assocciaPaccoLocker(int idLocker, int idSpedizione) {
		Connection conn = null;
        PreparedStatement st = null;
        
		try { 
			conn = DBConnection.getConnection();
			String query = "INSERT INTO locker_pacco (id_locker, id_spedizione) VALUES (?, ?)";
			st = conn.prepareStatement(query);
			st.setInt(1, idLocker);
			st.setInt(2, idSpedizione);
			st.executeUpdate();
			
			System.out.println("Pacco associato al locker con successo!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				 if (st != null) st.close();
	             if (conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();

			}
		}
	}
	
//	 // Metodo per ottenere l'ID del locker associato a una spedizione
//	public Integer getLockerBySpedizione(int idSpedizione) {
//		 String query = "SELECT IDlocker FROM locker_pacco WHERE IDspedizione = ?";
//	     Integer idLocker = null;
//	     
//	     try(Connection conn = DBConnection.getConnection();
//	    	PreparedStatement st = conn.prepareStatement(query)) {
//	    	 st.setInt(1, idSpedizione);
//	    	 ResultSet rs = st.executeQuery();
//	    	 
//	    	 if(rs.next()) {
//	    		 idLocker = rs.getInt("IDlocker");
//	    	 }
//	    	 
//	     } catch(SQLException e) {
//	    	 e.printStackTrace();
//	     }
//	     return idLocker;
//	     
//	}
//	 // Metodo per ottenere tutte le spedizioni in un determinato locker
//	public List<Integer> getSpedizioniByLocker(int idLocker) {
//        String query = "SELECT IDspedizione FROM locker_pacco WHERE IDlocker = ?";
//        List<Integer> spedizioni = new ArrayList<>();
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement st = conn.prepareStatement(query)) {
//
//            st.setInt(1, idLocker);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                spedizioni.add(rs.getInt("IDspedizione"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return spedizioni;
//    }
//	
//	 // Metodo per rimuovere un pacco da un locker
//    public void rimuoviPaccoDaLocker(int idLocker, int idSpedizione) {
//        String query = "DELETE FROM locker_pacco WHERE IDlocker = ? AND IDspedizione = ?";
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement st = conn.prepareStatement(query)) {
//
//            st.setInt(1, idLocker);
//            st.setInt(2, idSpedizione);
//            st.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public static void main(String[] args) {
//        LockerPaccoDAO dao = new LockerPaccoDAO();
//
//        // Associare un pacco a un locker
//        dao.associaPaccoALocker(1, 1);
//        System.out.println("Pacco associato al locker.");
//
//        // Recuperare il locker di una spedizione
//        Integer locker = dao.getLockerBySpedizione(1);
//        System.out.println("Locker della spedizione 1: " + locker);
//
//        // Recuperare tutte le spedizioni di un locker
//        List<Integer> spedizioni = dao.getSpedizioniByLocker(1);
//        System.out.println("Spedizioni nel locker 1: " + spedizioni);
//
//        // Rimuovere un pacco dal locker
//        dao.rimuoviPaccoDaLocker(1, 1);
//        System.out.println("Pacco rimosso dal locker.");
//    }
//
//	private void associaPaccoALocker(int i, int j) {
//		
//	}
	
	public static void main(String[] args) {
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
