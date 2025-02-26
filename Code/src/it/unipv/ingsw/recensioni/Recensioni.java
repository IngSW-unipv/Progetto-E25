package it.unipv.ingsw.recensioni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Recensioni {

	String recensione_scritta;
	public Recensioni() {
		// TODO Auto-generated constructor stub
	}
	
	public void scrivi() {
		System.out.printf("Vuoi lasciare una recensione scritta?");
		
		InputStreamReader in= new InputStreamReader(System.in);
		BufferedReader keyboard=new BufferedReader(in);
		String name;
		try {
			name=keyboard.readLine();
			recensione_scritta=name;
		}catch(IOException e){
			System.out.printf("Errore durante inserimento recensione");	
		}
	}
	
	public void vota() {
		System.out.printf("Come valuteresti il nostro servizio?");
		//forse con interfaccia grafica
		
	}
	
	public String getRecensione() {
		return recensione_scritta;
	}

}
