package model.actions;

import model.joueurs.Partie;

/**
 * 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class Rejouer implements Action{
	
	private String message = "";

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	/** Le joueur rejoue une carte **/
	public void action() {
		Partie.getInstance().getJoueurEnCours().setDoitRejouer(true);		
		message = Partie.getInstance().getJoueurEnCours().getNom() + " rejoue";		
	}
	
	public String message() {
		return message;
	}
	
}

