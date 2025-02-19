package it.unipv.ingsw.model.utenze;

public class Saldo {
	private double denaro;
	private int puntiApp;
	
	public Saldo(double denaro, int puntiApp) {
		this.denaro = denaro;
		this.puntiApp = puntiApp;
	}

	public double getDenaro() {
		return denaro;
	}

	public void setDenaro(double denaro) {
		this.denaro = denaro;
	}

	public int getPuntiApp() {
		return puntiApp;
	}

	public void setPuntiApp(int puntiApp) {
		this.puntiApp = puntiApp;
	}
	
	
}
