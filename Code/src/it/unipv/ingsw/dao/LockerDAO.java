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
                
                Coordinate posizione = new Coordinate(latitudine, longitudine);
                
                l = new Locker(posizione, id);

                result.add(l);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        return result;
    }
	
	
	public static void main(String[] args) {
		LockerDAO l=new LockerDAO();
		ArrayList<IPuntoDeposito> result = l.selectAll();
		for (IPuntoDeposito lo : result)
            System.out.println(lo.toString());
		
		

		
	}

}
