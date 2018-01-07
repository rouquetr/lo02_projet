package model.actions;

import model.joueurs.Partie;

public class Piocher2 implements Action {

	public void actionCli() {
		Partie.getInstance().findJoueurSuivant().piocher(2);
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche 2 cartes";
	}
	
}
