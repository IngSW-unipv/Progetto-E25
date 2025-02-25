package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.shippable.Size;
import it.unipv.ingsw.model.utenze.Destinatario;
import it.unipv.ingsw.model.utenze.Mittente;

public class SpedizioneDAO implements ISpedizioneDAO{

	private String schema;
	private Connection conn;
	
	public SpedizioneDAO() {
		this.schema = "ShipUp";
	}
	
	//seleziona tutte le spedizioni in attesa di esser consegnate
	@Override
	public List<Spedizione> selectAllInAttesa() {
		
		ArrayList<Spedizione> result = new ArrayList<>();

        conn = DBConnection.startConnection(conn);
        Statement st;
        ResultSet rs;
        Spedizione s;
        IShippable ship;
        IPuntoDeposito pd1;
        IPuntoDeposito pd2;

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM spedizione natural join pacco WHERE statoSpedizione = 'IN_ATTESA'";
            rs = st.executeQuery(query);

            while (rs.next()) {
            	
            	ship = new Pacco(Size.valueOf(rs.getString("size")), rs.getDouble("peso"));
            	//pd1 = new Locker(); devo risalire alla posizione del locker 
            	
            	//int id, IShippable shippable, IPuntoDeposito a, IPuntoDeposito b
                s = new Spedizione(rs.getInt(0), ship, null, null);
                

                result.add(s);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        return result;
		
	}

	@Override
	public void addSpedizione(Spedizione spedizione) {
		
		
	}
	
	public static void main(String[] args) {
		SpedizioneDAO sd = new SpedizioneDAO();
		sd.selectAllInAttesa();
	}
	
}
