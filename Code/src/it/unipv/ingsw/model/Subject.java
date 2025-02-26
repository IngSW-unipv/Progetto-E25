package it.unipv.ingsw.model;

public interface Subject<T> {
	public void addObserver(T dato);
	public void removeObserver(T dato);
	public void notifyObservers();
}
