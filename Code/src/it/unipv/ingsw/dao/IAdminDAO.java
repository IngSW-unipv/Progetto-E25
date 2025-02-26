package it.unipv.ingsw.dao;

import java.util.ArrayList;

import it.unipv.ingsw.model.utenze.Admin;
import it.unipv.ingsw.model.utenze.Utente;

public interface IAdminDAO {
	public Admin getAdminByMatricola(int matricola); 
	public Admin getAdmin(int matricola,String email,String password);
	public ArrayList<Admin> selectAll() ;
	
}
