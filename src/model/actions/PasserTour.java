package model.actions;

import model.joueurs.Joueur;

public class PasserTour implements Action {
	
	private static Joueur joueur;
	
	public void action() {
		System.out.println("Le joueur suivant passe son tour");
		//joueur.setPosition(joueur.getPosition()+2);			//Le joueur +1 passe son tour, c'est au tour du joueur +2
	}

}
