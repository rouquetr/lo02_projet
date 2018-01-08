package model.actions;

import model.joueurs.Partie;

public class Piocher4 implements Action {
	
	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(4);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " pioche 4 cartes";
	}
	
}
