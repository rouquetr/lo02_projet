package model.actions;

import model.joueurs.Partie;

public class PasserTour implements Action {
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().setPeutJouer(false);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " passe son tour";
	}

}
