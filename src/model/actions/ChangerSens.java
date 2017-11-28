package model.actions;

import model.joueurs.Partie;

public class ChangerSens implements Action {
	
	public void action() {
		Partie.getInstance().changerSens();
	}
	
}

