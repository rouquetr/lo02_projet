package model.actions;

import model.joueurs.Partie;

public class Rejouer implements Action{

	public void action() {
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurPrecedent());
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " rejoue";
	}
	
}

