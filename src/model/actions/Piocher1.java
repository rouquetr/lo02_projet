package model.actions;

import model.joueurs.Partie;

/**
 * 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class Piocher1 implements Action {
	
	private String message = "";

	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	/** Le joueur suivant pioche 1 cartes **/
	public void action() {
		Partie.getInstance().findJoueurSuivant().piocher(1);	//faire piocher 1 carte au joueur suivant
		message = Partie.getInstance().findJoueurSuivant().getNom() + " pioche une carte";		//Affichage du nom du joueur qui pioche la carte
	}
	
	public String message() {
		return message;			//affichage du message
	}
	
}
