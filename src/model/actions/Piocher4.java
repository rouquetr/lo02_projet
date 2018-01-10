package model.actions;

import model.joueurs.Partie;

public class Piocher4 implements Action {
	
	private String message = "";
	
	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(4);
		message = Partie.getInstance().findJoueurSuivant().getNom() + " pioche 4 cartes";
	}
	
	public String message() {
		return message;
	}
	
}
