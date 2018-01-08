package model.actions;

import model.joueurs.Partie;

public class PasserTour implements Action {
	
	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();		
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().setPeutJouer(false);
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " passe son tour";
	}

}
