package model.actions;

import model.joueurs.Partie;

public class Piocher3 implements Action {
	
	private String message;

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(3);
		message = Partie.getInstance().findJoueurSuivant() + " pioche trois cartes";
	}
	
	public String message() {
		return message;
	}
	
}
