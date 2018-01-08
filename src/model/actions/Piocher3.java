package model.actions;

import model.joueurs.Partie;

public class Piocher3 implements Action {

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(3);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche trois cartes";
	}
	
}
