package it.unipv.ingsw.model.spedizione.shippable;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;

public class Pacco implements IShippable{
	
	private int weight;
	private int size;
	
	public Pacco() {
		// TODO Auto-generated constructor stub
		size=0;
		weight=0;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public int getWeight() { 
		return weight;
	}
	
	
	public int setSize() {
		// TODO Auto-generated method stub
		int size;
		System.out.printf("Scegli la dimensione del pacco: piccola, media, grande oppure extra large");
		size=1;
		return size;
	}
	
	public int setWeight() { 
		InputStreamReader in=new InputStreamReader(System.in);
		BufferedReader keyboard=new BufferedReader(in);
		
		String line;
		System.out.printf("Inserisci peso del pacco");
		try {
			line=keyboard.readLine();	
			weight=Integer.parseInt(line);
		}catch(IOException e){
			System.out.printf("Errore inserimento dati destinatario");
			//capire cosa fare se abbiamo un eccezione
		}
		return weight;
	}
	
}
