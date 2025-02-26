package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.utenze.Utente;

public class LockerDAO implements IPuntoDepositoDAO{

	private String schema;
	private Connection conn;

	public LockerDAO() {
		this.schema = "ShipUp";
	}
	
	@Override
    public ArrayList<IPuntoDeposito> selectAll() {
        ArrayList<IPuntoDeposito> result = new ArrayList<>();

        conn = DBConnection.startConnection(conn);
        Statement st;
        ResultSet rs;
        Locker l;

        try {
            st = conn.createStatement();
            String query = "SELECT IDlocker, ST_X(posizione) AS lon, ST_Y(posizione) AS lat FROM locker";
            rs = st.executeQuery(query);

            while (rs.next()) {
            	
            	int id = rs.getInt("IDlocker");
                double latitudine = rs.getDouble("lat");
                double longitudine = rs.getDouble("lon");
                
                Coordinate posizione = new Coordinate(longitudine, latitudine);
                
                l = new Locker(posizione, id);

                result.add(l);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        return result;
    }
	
	
	@Override
	public IPuntoDeposito selectPuntoDeposito(Coordinate c) { 
	    conn = DBConnection.startConnection(conn);
	    PreparedStatement st;
	    ResultSet rs;
	    IPuntoDeposito l = null;

	    try { 
	        // Query per selezionare IDlocker e le coordinate, con parametri per latitudine e longitudine
	        String query = "SELECT IDlocker, ST_X(posizione) AS lon, ST_Y(posizione) AS lat FROM locker WHERE ST_X(posizione) = ? AND ST_Y(posizione) = ?";
	        st = conn.prepareStatement(query);
	        
	        // Impostiamo i parametri del PreparedStatement
	        st.setDouble(1, c.getLongitudine());
	        st.setDouble(2, c.getLatitudine());

	        rs = st.executeQuery();

	        // Se troviamo un risultato, lo elaboriamo
	        if (rs.next()) {
	            int id = rs.getInt("IDlocker");
	            double latitudine = rs.getDouble("lat");
	            double longitudine = rs.getDouble("lon");

	            // Creiamo una Coordinate con i valori estratti dal database
	            Coordinate posizione = new Coordinate(latitudine, longitudine);
	            
	            // Confrontiamo le coordinate passate con quelle estratte dal database
	            // Aggiungiamo un margine di tolleranza per evitare errori di confronto dovuti alla precisione dei numeri floating-point
	            double tolerance = 0.0001;  // Tolera piccole differenze tra latitudine e longitudine

	            if (Math.abs(c.getLatitudine() - latitudine) < tolerance && Math.abs(c.getLongitudine() - longitudine) < tolerance) {
	                // Se le coordinate corrispondono, creiamo un nuovo Locker con l'ID
	                l = new Locker(posizione, id);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    DBConnection.closeConnection(conn);

	    // Se l'oggetto Locker è stato trovato, lo restituiamo
	    return l;  // Se non trovato, ritorna null
	}


	
	public static void main(String[] args) {
		LockerDAO ld1=new LockerDAO();
		ArrayList<IPuntoDeposito> result = ld1.selectAll();
		for (IPuntoDeposito lo : result)
            System.out.println(lo.toString());
		Coordinate c=new Coordinate(45.4642,9.1900);
		LockerDAO ld2=new LockerDAO();
		IPuntoDeposito l1=ld2.selectPuntoDeposito(c);
		System.out.println(l1.getPosizione().getLatitudine());
		
		

		
	}
	
	
}
