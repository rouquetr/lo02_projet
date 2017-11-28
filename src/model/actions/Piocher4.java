package model.actions;

import model.joueurs.Partie;

public class Piocher4 implements Action {
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(4);
	}
	
}
