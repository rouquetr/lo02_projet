package model.actions;

import model.joueurs.Partie;

public class Piocher1 implements Action {

	public void actionCli() {
		Partie.getInstance().findJoueurSuivant().piocher(1);
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche une carte";
	}
	
}
