package it.unipv.ingsw.model.spedizione.shippable;

import it.unipv.ingsw.model.spedizione.shippable.*;

public class Pacco implements IShippable{
	
	private double weight;
	private Size size;

	public Pacco(Size size, double weight) {
		this.size=size;
		this.weight=weight;
	}
	
	@Override
	public Size getSize() {
		return size;
	}
	
	@Override
	public double getWeight() { 
		return weight;
	}
	
	
	public void setSize(Size size) {
		this.size = size;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
}
