package it.unipv.ingsw.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.unipv.ingsw.dao.LockerPaccoDAO;

public class TestLockerPaccoDAO {

	 public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/ShipUp?serverTimezone=Europe/Rome";  // Cambia con i tuoi dati di connessione
	        String user = "root"; // Il mio username
	        String password = "ShipUp"; // La nostra password del database

	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            LockerPaccoDAO lockerPaccoDAO = new LockerPaccoDAO(conn);

	            // TEST INSERIMENTO
	            System.out.println("Inserimento di LockerPacco...");
	            boolean inserito = lockerPaccoDAO.inserisciLockerPacco(1, 3);
	            System.out.println("Inserimento riuscito? " + inserito);

	            // TEST RICERCA LOCKER DA SPEDIZIONE
	            System.out.println("Recupero ID Locker per spedizione ID 3...");
	            Integer idLocker = lockerPaccoDAO.getLockerBySpedizione(3);
	            System.out.println("Locker trovato: " + idLocker);

	            // TEST RICERCA SPEDIZIONE DA LOCKER
	            System.out.println("Recupero ID Spedizione per locker ID 1...");
	            Integer idSpedizione = lockerPaccoDAO.getSpedizioneByLocker(1);
	            System.out.println("Spedizione trovata: " + idSpedizione);

	            // TEST CANCELLAZIONE
	            System.out.println("Rimozione di LockerPacco...");
	            boolean rimosso = lockerPaccoDAO.rimuoviLockerPacco(1, 3);
	            System.out.println("Rimozione riuscita? " + rimosso);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
}
