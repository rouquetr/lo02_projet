package model.actions;

import model.joueurs.Partie;

public class Piocher4 implements Action {
	
	public void actionCli() {
		Partie.getInstance().findJoueurSuivant().piocher(4);
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " pioche 4 cartes";
	}
	
}
