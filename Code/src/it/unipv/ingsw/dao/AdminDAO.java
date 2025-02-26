package it.unipv.ingsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			String query= "select * from admin natural join superuser where email = ? and matricola = ? and password = ?";
			
			st1=conn.prepareStatement(query);
			st1.setString(1, email);
			st1.setInt(2, matricola);
			st1.setString(3, password);
			rs1=st1.executeQuery();
			if(rs1.next()) {
				result= new Admin(rs1.getInt("matricola"), rs1.getString("email"),rs1.getString("password"));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		//System.out.println("qui");
		//System.out.println(result);
		return result;
	}
	
	@Override
    public ArrayList<Admin> selectAll() {
        ArrayList<Admin> result = new ArrayList<>();

        conn = DBConnection.startConnection(conn);
        Statement st1,st2;
        ResultSet rs1;
        Admin a;

        try {
            st1 = conn.createStatement();
            String query = "SELECT * FROM SUPERUSER NATURAL JOIN ADMIN";
            rs1 = st1.executeQuery(query);

            while (rs1.next()) {
            	
                 a = new Admin(rs1.getInt("matricola"),rs1.getString("email"),rs1.getString("password"));

                 result.add(a);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        return result;
    }
	
	public static void main(String[] args) {
		AdminDAO adminDAO= new AdminDAO();
		System.out.println(adminDAO.getAdmin(2, "a", "p").getMail());
		
	}
}
