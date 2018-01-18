package model.actions;

import model.joueurs.Partie;

/**
 * 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class Piocher2 implements Action {
	
	private String message;

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	/** Le joueur suivant pioche 2 cartes **/
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(2);	/** faire piocher 2 carte au joueur suivant **/
		message = Partie.getInstance().findJoueurSuivant() + " pioche 2 cartes";	//Affichage du nom du joueur qui pioche les carte
	}
	
	public String message() {
		return message;
	}
	
}
