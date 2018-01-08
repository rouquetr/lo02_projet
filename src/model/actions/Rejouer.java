package model.actions;

import model.joueurs.Partie;

public class Rejouer implements Action{

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurPrecedent());
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " rejoue";
	}
	
}

