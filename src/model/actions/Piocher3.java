package model.actions;

import model.joueurs.Partie;

/**
 * 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class Piocher3 implements Action {
	
	private String message;

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	/** Le joueur suivant pioche 3 cartes **/
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(3);	//faire piocher 3 carte au joueur suivant
		message = Partie.getInstance().findJoueurSuivant() + " pioche trois cartes";	//Affichage du nom du joueur qui pioche les carte
	}
	
	public String message() {
		return message;
	}
	
}
