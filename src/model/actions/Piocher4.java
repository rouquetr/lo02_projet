package model.actions;

import model.joueurs.Partie;

/**
 * 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class Piocher4 implements Action {
	
	private String message = "";
	
	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	/** Le joueur pioche 4 cartes **/
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(4);	
		message = Partie.getInstance().findJoueurSuivant().getNom() + " pioche 4 cartes";	//Affichage du nom du joueur qui pioche les carte
	}
	
	public String message() {
		return message;
	}
	
}
