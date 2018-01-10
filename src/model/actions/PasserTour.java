package model.actions;

import model.joueurs.Partie;

public class PasserTour implements Action {
	
	private String message = "";
	
	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();		
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().setPeutJouer(false);
		message = Partie.getInstance().findJoueurSuivant().getNom() + " passe son tour";
	}

	public String message() {
		return message;
	}

}
