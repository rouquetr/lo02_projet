package model.actions;

import model.joueurs.Partie;

public class Piocher2 implements Action {
	
	private String message;

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(2);
		message = Partie.getInstance().findJoueurSuivant() + " pioche 2 cartes";
	}
	
	public String message() {
		return message;
	}
	
}
