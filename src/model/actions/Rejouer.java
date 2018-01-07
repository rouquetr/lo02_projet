package model.actions;

import model.joueurs.Partie;

public class Rejouer implements Action{

	public void actionCli() {
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurPrecedent());
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " rejoue";
	}
	
}

