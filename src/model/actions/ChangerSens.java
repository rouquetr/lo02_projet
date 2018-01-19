package model.actions;

import model.joueurs.Partie;

/**
 * Représente une classe permettant de changer le sens d'une partie
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class ChangerSens implements Action {
	
	public void actionCli() {
		action();
	}
	
	public void actionGui() {
		action();
	}
	
	
	/** On récupère l'instance de la Partie et on change le sens de cette partie **/
	public void action() {
		Partie partie = Partie.getInstance();		//on récupère la partie en cours
		if(partie.getJoueurs().size() == 2) partie.findJoueurSuivant().setPeutJouer(false);			//Si la partie est composée de 2 joueurs, le joueur suivant passe son tour
		else partie.changerSens();			//sinon on change de sens 
	}
	
	public String message() {
		return "La partie change de sens";
	}
	
}

