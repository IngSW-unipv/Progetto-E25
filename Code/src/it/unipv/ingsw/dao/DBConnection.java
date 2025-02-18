package it.unipv.ingsw.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;
    private static String username;
	private static String password;
	private static String dbDriver;
	private static String dbURL;

    private static void init() {
        try {
            Properties properties = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("database.properties");

            if (input == null) {
                throw new FileNotFoundException("File database.properties non trovato!");
            }

            properties.load(input);

            dbDriver = properties.getProperty("db.driver");
            dbURL = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");

            // Carica il driver JDBC
            Class.forName(dbDriver);

            // Crea la connessione
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connessione al database avvenuta con successo!");

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la connessione al database", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    
    public static Connection startConnection(Connection conn, String schema)
	{
		init();			
		
		if ( isOpen(conn) )
			closeConnection(conn);
	
		try 
		{				
			dbURL=String.format(dbURL,schema); 
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbURL, username, password);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}

	public static Connection closeConnection(Connection conn)
	{
		if ( !isOpen(conn) )
			return null;
		try 
		{

			conn.close();
			conn = null;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return conn;
	}

}
