package model.actions;

import model.joueurs.Partie;

public abstract class Action {
	
	static Partie partie = Partie.getInstance();
	
	public static Partie getPartie() {
		return partie;
	}	

}
