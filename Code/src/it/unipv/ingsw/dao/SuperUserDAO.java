package it.unipv.ingsw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Admin;
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
				result= new Utente(rs1.getString(1),rs1.getString(2), rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}
	
	public boolean insertUtente (Utente u) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1,st2;
	
		try {
			String query1 = "INSERT INTO `ShipUp`.`superUser` (`email`, `password`)"
					+ " VALUES(?,?)";
			st2 = conn.prepareStatement(query1);

			st2.setString(1, u.getMail());
			st2.setString(2, u.getPassword());
			st2.executeUpdate();
			
			String query="insert into utente (`email`, `nome`, `cognome`, `dataNascita`, `numeroTelefono`, `indirizzoCivico`,`fotoDocumento`, `statoProfilo`) VALUES(?,?,?,?,?,?,?,?)";
			st1 = conn.prepareStatement(query); 
			st1.setString(1, u.getMail());
			st1.setString(2, u.getNome());
			st1.setString(3, u.getCognome());
			st1.setString(4, u.getNumeroTelefono());
			st1.setString(5, u.getIndirizzoCivico());
			st1.setObject(6, u.getDataNascita());
			st1.setObject(7, u.getFotoDocumento());
			st1.setObject(8, u.getStatoProfilo());
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

	public ASuperUser getAdminById (String id) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		ASuperUser result=null;
				
		try {
			String query= "select * from admin where matricola = ? ";
			st1=conn.prepareStatement(query);
			st1.setString(1, id);
			rs1=st1.executeQuery();
			
			if(rs1.next()) {
				switch(id.charAt(0)) {
					case 'i':
						//result = new InventoryOperator(rs1.getString(1),rs1.getString(2), rs1.getString(3),rs1.getString(4));
						result = new Admin(rs1.getString(1),rs1.getString(2));
						break;
					//case 's':
						//result = new SupplyOperator(rs1.getString(1),rs1.getString(2), rs1.getString(3),rs1.getString(4));
						//break;
					//case 'p':
						//result = new PickingOperator(rs1.getString(1),rs1.getString(2), rs1.getString(3),rs1.getString(4));
						//break;
				}
			}	
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}

	public boolean insertAdmin(Admin ad) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean result=true;
		
		try {
			String query=" insert into admin (matricola,email) values (?,?)";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, ad.getMatricola());
			st1.setString(2, ad.getMail());
			st1.executeUpdate();
			
		} catch (Exception e) {
		    e.printStackTrace();
		    result=false;
		} finally {
		    DBConnection.closeConnection(conn);
		}
		return result;
		
	}

}