package it.unipv.ingsw.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.MatchingService;
import it.unipv.ingsw.model.spedizione.Spedizione;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;
import it.unipv.ingsw.model.spedizione.puntoDeposito.Locker;
import it.unipv.ingsw.model.spedizione.shippable.IShippable;
import it.unipv.ingsw.model.spedizione.shippable.Pacco;
import it.unipv.ingsw.model.spedizione.shippable.Size;
import it.unipv.ingsw.model.utenze.Destinatario;
import it.unipv.ingsw.model.utenze.Mittente;
import it.unipv.ingsw.model.utenze.Utente;


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
        Statement st1;
        Statement st2;
        ResultSet rs1;
        ResultSet rs2;
        Spedizione s;
        IShippable ship;
        IPuntoDeposito pd1;
        IPuntoDeposito pd2;
        Coordinate c1;
        Coordinate c2;

        try {
            st1 = conn.createStatement();
            String query1 = "SELECT * FROM spedizione natural join pacco WHERE statoSpedizione = 'IN_ATTESA'";
            rs1 = st1.executeQuery(query1);
            
            while (rs1.next()) {
            	
            	ship = new Pacco(Size.valueOf(rs1.getString("size")), rs1.getDouble("peso"));
            	
            	//ricavo la posizione del locker iniziale e finale 
            	st2 = conn.createStatement();
            	
                String query2 = "SELECT ST_X(posizione) AS lon, ST_Y(posizione) AS lat FROM locker WHERE IDlocker = "+rs1.getInt("lockerIniziale");
                rs2 = st2.executeQuery(query2);
                rs2.next();
                c1 = new Coordinate(rs2.getDouble("lon"),rs2.getDouble("lat"));
            	pd1 = new Locker(c1);
            	
            	query2 = "SELECT ST_X(posizione) AS lon, ST_Y(posizione) AS lat FROM locker WHERE IDlocker = "+rs1.getInt("lockerFinale");
                rs2 = st2.executeQuery(query2);
                rs2.next();
                c2 = new Coordinate(rs2.getDouble("lon"),rs2.getDouble("lat"));
            	pd2 = new Locker(c2);
            	
            	//costruttore spedizione = (int id, IShippable shippable, IPuntoDeposito a, IPuntoDeposito b)
                s = new Spedizione(rs1.getInt("IDspedizione"), ship, pd1, pd2);
                
                System.out.println(s);
                result.add(s);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        System.out.println("size:"+result.size());
        return result;
		
	}

	//carica nel database i dati relativi alla spedizione appena avviata
	@Override
	public void addSpedizione(Spedizione spedizione) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st;
		ResultSet rs;
		
		if (conn != null) {
		    System.out.println("Connessione al database riuscita\n");
		} else {
		    System.out.println("Connessione al database fallita\n");
		}
		
		try {
			String query1= "INSERT INTO `ShipUp`.`spedizione` (`statoSpedizione`, `dataAvvio`, `dataFine`, `mittente`, `destinatario`, `lockerIniziale`,`lockerFinale`, `assicurazione`)"
					+ " VALUES(?,?,?,?,?,?,?,?)";
			st = conn.prepareStatement(query1);
			
			st.setString(1, spedizione.getStatoSpedizione());
			st.setObject(2, spedizione.getDataInizio()); 
			st.setObject(3, spedizione.getDataFine());
			st.setObject(4, spedizione.getMittente().getMail()); //email mittente
			st.setObject(5, spedizione.getDestinatario().getMail()); //email destinatario
			st.setInt(6, spedizione.getPartenza().getID()); 
			st.setInt(7, spedizione.getPartenza().getID()); 
			st.setInt(8, spedizione.getAssicurazione());
			st.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//fare natural join tra questa query, mittente e destinatario. poi, where idmittente=?, maildestinatario=? 
/*	try {
			String query1= "SELECT * FROM SPEDIZIONE NATURAL JOIN UTENTE where email=? and IDspedizione=?;";
			st=conn.prepareStatement(query1);
			st.setString(1,spedizione.getMittente().getMail());
			st.setInt(2,spedizione.getIDSpedizione());
			rs=st.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
		
		
		DBConnection.closeConnection(conn);
		
	}
	
	public Spedizione aggiornaStatoSpedizione(Spedizione spedizione, String stato) {
		
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1, st2;
		Spedizione spedizioneModificata = spedizione;
		
		try {
		//verifico se la spedizione esiste
		String query = "SELECT IDspedizione FROM `ShipUp`.`spedizione` WHERE `IDspedizione` = ?";
		st1 = conn.prepareStatement(query);
		st1.setInt(1, spedizione.getIDSpedizione());
		ResultSet rs = st1.executeQuery();
		
		//se non esiste la setta a null
		if (!rs.next()) {
			spedizioneModificata = null;
		} else {
		//aggiorno lo stato della spedizione
		String query1 = "UPDATE `ShipUp`.`spedizione` s SET s.statoSpedizione = ? WHERE s.IDspedizione = ?";

		
		st2 = conn.prepareStatement(query1);
		st2.setString(1, stato);
		st2.setInt(2, spedizione.getIDSpedizione());
		
		st2.executeUpdate();
		
		//aggiorno lo stato
		spedizioneModificata.setStatoSpedizione(stato);
		
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		
		return spedizioneModificata;
	}
	
	 public boolean lockerExists(int lockerID) {
	        boolean exists = false;
	        try (Connection conn = DBConnection.startConnection(null);
	             PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) FROM locker WHERE IDlocker = ?")) {
	            st.setInt(1, lockerID);
	            ResultSet rs = st.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                exists = true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return exists;
	    }

	
	public static void main(String[] args) {
		SpedizioneDAO sd = new SpedizioneDAO();
		
		Mittente m=new Mittente("email", null, null, null, null, null, null, null);
		Destinatario d=new Destinatario("user2@example.com", null, null, null, null, null, null, null);
		
		Coordinate a = new Coordinate(4,5);
		Coordinate b = new Coordinate(7,-2);
		
		IPuntoDeposito l1 = new Locker(a,1);
		IPuntoDeposito l2 = new Locker(b,2);
		
		
		Spedizione s2=new Spedizione(m, d, null, 10, l1, l2, null, null);
		Date dateInizio=new Date();
		s2.setDataInizio(dateInizio);
		s2.setStatoSpedizione("In attesa nel locker partenza");
		
//		sd.addSpedizione(s2);
		
		List<Spedizione> spedizioni = sd.selectAllInAttesa();
//		Spedizione s = spedizioni.get(0);
		if (!spedizioni.isEmpty()) {
			 Spedizione s = spedizioni.get(0);
			 System.out.println("Prima spedizione trovata: " + s);
			 sd.aggiornaStatoSpedizione(s, "test");
		} else {
			System.out.println("Nessuna spedizione trovata!");
		}
		
		
//		sd.aggiornaStatoSpedizione(s, "test");
//		Spedizione s = spedizioni.get(0);
//		sd.aggiornaStatoSpedizione(s, "test");
		
		
	}
	
}
