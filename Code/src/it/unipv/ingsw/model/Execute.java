package it.unipv.ingsw.model;


import it.unipv.ingsw.controller.MainController;
import it.unipv.ingsw.view.MainView;

public class Execute {
	
	public static void main(String[] args) {
		new MainController(new MainView());
		
	}
}
