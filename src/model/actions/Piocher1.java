package model.actions;

import model.joueurs.Partie;

public class Piocher1 implements Action {
	
	private String message = "";

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(1);
		message = Partie.getInstance().findJoueurSuivant() + " pioche une carte";
	}
	
	public String message() {
		return message;
	}
	
}
