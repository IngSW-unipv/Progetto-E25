package it.unipv.ingsw.model.spedizione.shippable;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;

import it.unipv.ingsw.model.spedizione.shippable.DimensioneShippable;

public class Pacco implements IShippable{
	
	private int weight;
	private int size;
	private String id;
	private boolean paccoRitirato; // indica se il pacco e stato ritirato o meno
	private DimensioneShippable DIMENSIONE;
	
	public Pacco() {
		// TODO Auto-generated constructor stub
		size=0;
		weight=0;
		this.id = id;
		this.paccoRitirato = paccoRitirato;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean paccoRitirato() {
		return paccoRitirato;
	}
	
	public void setPaccoRitirato (boolean paccoRitirato) {
		this.paccoRitirato = paccoRitirato;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public int getWeight() { 
		return weight;
	}
	
	
	public String setSize() {
		// TODO Auto-generated method stub
		InputStreamReader in=new InputStreamReader(System.in);
		BufferedReader keyboard=new BufferedReader(in);
		String size=null;
		
		try {
			System.out.printf("Scegli la dimensione del pacco: piccola, media, grande oppure extra large");
			size=keyboard.readLine();	
		}catch(IOException e){
			System.out.printf("Errore inserimento dati destinatario");
			//capire cosa fare se abbiamo un eccezione
		}
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
			System.out.printf("Errore inserimento peso destinatario");
			//capire cosa fare se abbiamo un eccezione
		}
		
		if(weight<10) {
			return weight;
		} else {
			System.out.printf("Peso pacco troppo elevato");
			return 0;
		}
	}
	
}
