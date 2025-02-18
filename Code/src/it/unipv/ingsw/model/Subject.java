package it.unipv.ingsw.model;

public interface Subject {
	public void addObserver();
	public boolean removeObserver();
	public void notifyObservers();
}
