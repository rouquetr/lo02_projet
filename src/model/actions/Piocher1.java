package model.actions;

import model.joueurs.Partie;

public class Piocher1 implements Action {

	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(1);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant() + " pioche une carte";
	}
	
}
