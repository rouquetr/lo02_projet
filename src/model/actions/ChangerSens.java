package model.actions;

import model.joueurs.Partie;

public class ChangerSens implements Action {
	
	public void actionCli() {
		Partie partie = Partie.getInstance();
		if(partie.getJoueurs().size() == 2) partie.findJoueurSuivant().setPeutJouer(false);
		else partie.changerSens();
	}
	public void actionGui() {
	}
	
	public String message() {
		return "La partie change de sens";
	}
	
}

