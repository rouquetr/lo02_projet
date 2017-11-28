package model.actions;

import model.joueurs.Partie;

public class PasserTour implements Action {
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().setPeutJouer(false);
	}

}
