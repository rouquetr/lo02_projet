package model.actions;

import model.joueurs.Partie;

public class ChangerSens implements Action {

	private static Partie partie;
	
	public void action() {
		partie.changerSens();
	}
	
}

