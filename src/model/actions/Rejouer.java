package model.actions;

import model.joueurs.Partie;

public class Rejouer implements Action{

	public void action() {
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurPr�c�dent());
	}
	
	public String message() {
		return Partie.getInstance().findJoueurSuivant().getNom() + " rejoue";
	}
	
}

