package it.unipv.ingsw.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.model.utenze.ASuperUser;
import it.unipv.ingsw.model.utenze.Saldo;
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
				result.setIdUtente(rs1.getInt("ID"));
			}
			
		} catch  (Exception e) { 
			e.printStackTrace();
		} finally {
	        DBConnection.closeConnection(conn); 
	    }
		return result;
	}
	
	public Utente aggiornamentoUtente(Utente utenteCorrente, String password, String nome, String cognome, String numeroTelefono, String dataNascita, String indirizzoCivico, String fotoDocumento) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1, st2;
		Utente utenteModificato = utenteCorrente;
		
		try {
		// Fase 1: Verifica se l'utente esiste
		String query = "SELECT id FROM `ShipUp`.`utente` WHERE `email` = ?";
		st1 = conn.prepareStatement(query);
		st1.setString(1, utenteCorrente.getMail()); //u.getMail()
		ResultSet rs = st1.executeQuery();
		
		// Verifica se l'utente esiste
		if (!rs.next()) {
		// Se non c'è l'utente, ritorna null
		utenteModificato = null;
		} else {
		// Fase 2: Esegui l'aggiornamento dei dati dell'utente
		String query1 = "UPDATE `ShipUp`.`utente` u "
		               + "JOIN `ShipUp`.`superuser` s ON u.email = s.email "
		               + "SET u.nome = ?, u.cognome = ?, u.dataNascita = ?, u.numeroTelefono = ?, "
		               + "u.indirizzoCivico = ?, u.fotoDocumento = ?, u.statoProfilo = ?, s.password = ? "
		               + "WHERE u.id = ?";

		
		st2 = conn.prepareStatement(query1);
		st2.setString(1, nome);
		st2.setString(2, cognome);
		st2.setString(3, dataNascita);  
		st2.setString(4, numeroTelefono);
		st2.setString(5, indirizzoCivico);
		st2.setString(6, fotoDocumento); // Se è un oggetto, altrimenti usa setString o altro metodo
		st2.setBoolean(7, false);  // Assumendo che sia un booleano
		st2.setString(8, password);
		st2.setInt(9, rs.getInt("id"));
		
		st2.executeUpdate();
		
		// Crea un nuovo oggetto Utente con i dati aggiornati
		utenteModificato.setMail(utenteCorrente.getMail()); // La mail non cambia
		utenteModificato.setNome(nome);
		utenteModificato.setCognome(cognome);
		utenteModificato.setNumeroTelefono(numeroTelefono);
		utenteModificato.setDataNascita(dataNascita);
		utenteModificato.setIndirizzoCivico(indirizzoCivico);
		utenteModificato.setFotoDocumento(fotoDocumento);
		utenteModificato.setPassword(password);
		utenteModificato.setStatoProfilo(true); // Aggiorna lo stato del profilo
		utenteModificato.setIdUtente(rs.getInt("id"));
		}
		
		} catch (Exception e) {
		e.printStackTrace();
		utenteModificato = null;
		} finally {
		DBConnection.closeConnection(conn); // Assicurati di chiudere sempre la connessione
		}
		
		return utenteModificato; // Restituisce l'utente con i dati aggiornati
}

	public boolean deleteUtente(Utente u) {

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
	
	 // Nuovo metodo che legge Saldo e Punti App per un utente dato email e password
    public Saldo getSaldoEPuntiAppByUtente(Utente u) {
        conn = DBConnection.startConnection(conn);
        PreparedStatement st;
        ResultSet rs;
        Saldo saldo = null;

        try {
            // Query per ottenere i campi quantitaDenaro e puntiApp
            String query = "select * from utente u join saldo s where u.id = s.idutente and u.id = ?";
            st = conn.prepareStatement(query);
            st.setInt(1, u.getIdUtente());
            rs = st.executeQuery();

            if (rs.next()) {
                double quantitaDenaro = rs.getDouble("quantitaDenaro");
                int puntiApp = rs.getInt("puntiApp");
                u.getSaldo().setDenaro(quantitaDenaro);
                u.getSaldo().setPuntiApp(puntiApp);
                //saldo.setDenaro(quantitaDenaro);
                //saldo.setPuntiApp(puntiApp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(conn);
        }

        return u.getSaldo();
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
            String query = "SELECT * FROM utente NATURAL JOIN superuser";
            rs1 = st1.executeQuery(query);

            while (rs1.next()) {
            	
            	u = new Utente(rs1.getString("email"),rs1.getString("password"),rs1.getString("nome"),rs1.getString("cognome"),rs1.getString("dataNascita"),rs1.getString("numeroTelefono"),rs1.getString("indirizzoCivico"),rs1.getString("fotoDocumento"));
                u.setIdUtente(rs1.getInt("ID"));
                 result.add(u);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        DBConnection.closeConnection(conn);
        return result;
    }
	
	public ArrayList<Utente> utentiDisattivati(){
		ArrayList<Utente> result = new ArrayList<>();
		
		conn = DBConnection.startConnection(conn);
        Statement st1;
        ResultSet rs1;
        Utente u;

        try {
            st1 = conn.createStatement();
            String query = "SELECT * FROM utente NATURAL JOIN superuser WHERE statoProfilo = false";
            rs1 = st1.executeQuery(query);

            while (rs1.next()) {
            	
            	//String mail, String password,String nome, String cognome, String dataNascita,String numeroTelefono, String indirizzoCivico, String fotoDocumento
                 u = new Utente(rs1.getString("email"),rs1.getString("password"),rs1.getString("nome"),rs1.getString("cognome"),rs1.getString("dataNascita"),rs1.getString("numeroTelefono"),rs1.getString("indirizzoCivico"),rs1.getString("fotoDocumento"));
                 u.setIdUtente(rs1.getInt("ID"));
                 result.add(u);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        DBConnection.closeConnection(conn);
		
		return result;
	}
	
	public void impostaStatoUtente(Utente utente, boolean stato) {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st1;

		try {
			String query = "UPDATE `ShipUp`.`utente` u SET u.statoProfilo = ? WHERE u.id = ?";
	
			st1 = conn.prepareStatement(query);
			st1.setBoolean(1, stato);
			st1.setInt(2, utente.getIdUtente());
			st1.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
	}
	
	public static void main(String[] args) {
		UtenteDAO u=new UtenteDAO();
		Utente result = u.getUtenteByEmailPassword("email","pwd");
        System.out.println(result.toString());
		
		
	}
}
