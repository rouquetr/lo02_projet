package model.actions;

import model.joueurs.Partie;

public class Piocher3 implements Action {

	public void actionCli() {
		Partie.getInstance().findJoueurSuivant().piocher(3);
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche trois cartes";
	}
	
}
