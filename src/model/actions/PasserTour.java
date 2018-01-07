package model.actions;

import model.joueurs.Partie;

public class PasserTour implements Action {
	
	public void actionCli() {
		Partie.getInstance().findJoueurSuivant().setPeutJouer(false);
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " passe son tour";
	}

}
