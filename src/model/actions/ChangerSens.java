package model.actions;

import model.joueurs.Partie;

public class ChangerSens implements Action {
	
	public void action() {
		Partie.getInstance().changerSens();
	}
	
	public String message() {
		return "La partie change de sens";
	}
	
}

