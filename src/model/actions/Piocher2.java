package model.actions;

import model.joueurs.Partie;

public class Piocher2 implements Action {

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(2);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche 2 cartes";
	}
	
}
