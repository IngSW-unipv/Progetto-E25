package it.unipv.ingsw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Utente;
import java.sql.Blob;
import java.sql.Connection;

public class SuperUserDAO implements ISuperUserDAO{
	private String schema;
	private Connection conn;
	
	public SuperUserDAO() {
		this.schema = "ShipUp";
	}
	
	public ASuperUser getUtenteByEmail(String email) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ASuperUser result=null;
				
		try {
			String query= "select * from utente where email = ? ";
			st1=conn.prepareStatement(query);
			st1.setString(1, email);
			rs1=st1.executeQuery();
			if(rs1.next()) {
				result= new Utente(rs1.getString(1),rs1.getString(2), rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),(Blob) rs1.getObject(8));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}
	
	public boolean insertUtente (Utente c) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		
		try {
			String query="insert into utente (name,surname,email,password) values (?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, c.getNome());
			st1.setString(2, c.getCognome());
			st1.setString(3, c.getMail());
			st1.setString(4, c.getPassword());
			st1.executeUpdate(); 
			return true;
		} catch  (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
	        DBConnection.closeConnection(conn); 
	    }

	}
		
	public String selectPassword(ASuperUser u) {
        String email = u.getMail();
        String result = "";
        conn = DBConnection.startConnection(conn);
        PreparedStatement st1;
        ResultSet rs1;

        try {
            String query = "select password from utente where email= ?";
            st1 = conn.prepareStatement(query);
            st1.setString(1, email);
            rs1 = st1.executeQuery(); 

            if (rs1.next()) {
                result = rs1.getString("password"); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }
        return result;
    }


	public String selectEmail(ASuperUser u) {

		String email = u.getMail();
		String result = new String();
		conn = DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "select email from utente " + "where email= '" + email + "'";

			rs1 = st1.executeQuery(query);

			if (rs1.next()) {
				result = rs1.getString("email");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	public boolean deleteSuperUser(ASuperUser u) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		boolean esito = true;

		try {
			
			
			String query = "delete from `utente` where (`email` = '"+u.getMail()+"')";
					
			st1 = conn.prepareStatement(query);

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	
}