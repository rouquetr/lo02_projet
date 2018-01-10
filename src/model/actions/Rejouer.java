package model.actions;

import model.joueurs.Partie;

public class Rejouer implements Action{
	
	private String message = "";

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().getJoueurEnCours().setDoitRejouer(true);
		message = Partie.getInstance().getJoueurEnCours().getNom() + " rejoue";
	}
	
	public String message() {
		return message;
	}
	
}

