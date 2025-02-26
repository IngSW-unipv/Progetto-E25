package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;

public class AdminDAO implements IAdminDAO{
	private String schema;
	private Connection conn;

	public AdminDAO() {
		this.schema = "ShipUp";
	}
	public Admin getAdminByMatricola(int matricola) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Admin result=null;
				
		try {
			String query= "select * from admin where matricola = ?";
			
			st1=conn.prepareStatement(query);
			st1.setInt(1, matricola);
			rs1=st1.executeQuery();
			if(rs1.next()) {
				result= new Admin(rs1.getString("email"),rs1.getString("password"));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}
	
	public Admin getAdmin(int matricola,String email,String password) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Admin result=null;
				
		try {
			String query= "select * from admin natural join superuser where matricola = ?, email = ?, password = ?";
			
			st1=conn.prepareStatement(query);
			st1.setInt(1, matricola);
			st1.setString(2, email);
			st1.setString(3, password);
			rs1=st1.executeQuery();
			if(rs1.next()) {
				result= new Admin(rs1.getString("email"),rs1.getString("password"));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}
	
	public static void main(String[] args) {
		AdminDAO adminDAO= new AdminDAO();
		adminDAO.getAdminByMatricola(1);
		
	}
}
