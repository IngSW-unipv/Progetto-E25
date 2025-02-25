package it.unipv.ingsw.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Utente;

import java.sql.Blob;
import java.sql.Connection;

public class UtenteDAO implements IUtenteDAO {

	private String schema;
	private Connection conn;

	public UtenteDAO() {
		this.schema = "ShipUp";
	}

	//Registrazione 
	public boolean inserimentoUtente(Utente u) {

		conn = DBConnection.startConnection(conn);
		PreparedStatement st1,st2;

		boolean esito = true;

		try {
			String query1 = "INSERT INTO `ShipUp`.`superUser` (`email`, `password`)"
					+ " VALUES(?,?)";
			st2 = conn.prepareStatement(query1);

			st2.setString(1, u.getMail());
			st2.setString(2, u.getPassword());
			st2.executeUpdate(query1);
			
			String query2 = "INSERT INTO `utente` (`email`, `nome`, `cognome`, `dataNascita`, `numeroTelefono`, `indirizzoCivico`,`fotoDocumento`, `statoProfilo`)"
					+ " VALUES(?,?,?,?,?,?,?,?)";
			st1 = conn.prepareStatement(query2);

			st1.setString(1, u.getMail());
			st1.setString(2, u.getNome());
			st1.setString(3, u.getCognome());
			st1.setString(4, u.getNumeroTelefono());
			st1.setString(5, u.getIndirizzoCivico());
			st1.setObject(6, u.getDataNascita());
			st1.setObject(7, u.getFotoDocumento());
			st1.setObject(8, u.getStatoProfilo());
			st1.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}
	public Utente getUtenteByEmailPassword(String mail,String password) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Utente result=null;
				
		try {
			String query= "select * from superuser natural join utente where email = ? and password = ?";
			
			st1=conn.prepareStatement(query);
			st1.setString(1, mail);
			st1.setString(2, password);
			rs1=st1.executeQuery();
			if(rs1.next()) {
				result= new Utente(rs1.getString("email"),rs1.getString("password"),rs1.getString("nome"), rs1.getString("cognome"),rs1.getString("dataNascita"),rs1.getString("numeroTelefono"),rs1.getString("indirizzoCivico"),rs1.getString("fotoDocumento"));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}
	
	public boolean aggiornamentoUtente(Utente u) {
	    conn = DBConnection.startConnection(conn);
	    PreparedStatement st1, st2;
	    boolean esito = true;

	    try {
	        // Fase 1: Verifica se l'utente esiste
	        String query = "SELECT id FROM `ShipUp`.`utente` WHERE `email` = ?";
	        st1 = conn.prepareStatement(query);
	        st1.setString(1, u.getMail());
	        ResultSet rs = st1.executeQuery();

	        // Verifica se l'utente esiste
	        if (!rs.next()) {
	            // Se non c'è l'utente, ritorna false
	            esito = false;
	        } else {
	            // Fase 2: Esegui l'aggiornamento dei dati dell'utente
	            String query1 = "UPDATE `ShipUp`.`utente` SET `email` = ?, `nome` = ?, `cognome` = ?, "
	                    + "`dataNascita` = ?, `numeroTelefono` = ?, `indirizzoCivico` = ?, "
	                    + "`fotoDocumento` = ?, `statoProfilo` = ? WHERE `id` = ?";

	            st2 = conn.prepareStatement(query1);
	            st2.setString(1, u.getMail());
	            st2.setString(2, u.getNome());
	            st2.setString(3, u.getCognome());
	            st2.setObject(4, u.getDataNascita());  // Usa setObject per LocalDate
	            st2.setString(5, u.getNumeroTelefono());
	            st2.setString(6, u.getIndirizzoCivico());
	            st2.setObject(7, u.getFotoDocumento()); // Se è un oggetto, altrimenti usa setString o altro metodo
	            st2.setBoolean(8, u.getStatoProfilo());  // Assumendo che sia un booleano
	            st2.setInt(9, rs.getInt("id"));
	            

	            st2.executeUpdate();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        esito = false;
	    } finally {
	    	DBConnection.closeConnection(conn); // Assicurati di chiudere sempre la connessione
	    }

	    return esito;
	}

	@Override
    public ArrayList<Utente> selectAll() {
        ArrayList<Utente> result = new ArrayList<>();

        conn = DBConnection.startConnection(conn);
        Statement st1,st2;
        ResultSet rs1;
        Utente u;

        try {
            st1 = conn.createStatement();
            String query = "SELECT * FROM SUPERUSER NATURAL JOIN UTENTE";
            rs1 = st1.executeQuery(query);

            while (rs1.next()) {
            	
                 u = new Utente(rs1.getString(1),rs1.getString(2),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9));

                 result.add(u);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        return result;
    }
	
	public static void main(String[] args) {
		UtenteDAO u=new UtenteDAO();
		Utente result = u.getUtenteByEmailPassword("email","pwd");
		
        System.out.println(result.toString());
		
		
	}
}
