package it.unipv.ingsw.dao;

import java.sql.Connection;

public class PagamentoDAO extends IPagamentoDAO{
	private String schema;
	private Connection conn;
	private static PagamentoDAO instance = null;
	//fare query per pagamenti?
	public static PagamentoDAO getInstance(){
        if (instance == null){
            instance= new PagamentoDAO();
        }
        return instance;
    }
	
	public PagamentoDAO() {
		this.schema = "ShipUp";
	}
}
