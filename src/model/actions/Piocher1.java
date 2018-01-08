package model.actions;

import model.joueurs.Partie;

public class Piocher1 implements Action {

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(1);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche une carte";
	}
	
}
