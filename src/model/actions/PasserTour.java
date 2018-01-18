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
	
	/** Le joueur suivant passe son tour **/
	public void action() {
		Partie.getInstance().findJoueurSuivant().setPeutJouer(false);	//Le joueur suivant passe son tour
		message = Partie.getInstance().findJoueurSuivant().getNom() + " passe son tour";	//Affichage du nom du joueur qui passe son tour 
	}

	public String message() {
		return message;
	}

}
