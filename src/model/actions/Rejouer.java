package model.actions;

import model.joueurs.Joueur;

public class Rejouer extends Action{

	private static Joueur joueur; 
	
	public static void action() {
		System.out.println("Vous rejouez");
		joueur.setPosition(joueur.getPosition()-1); 			//Au prochain incrément, c'est le joueur actuel qui rejoue
	}
	
}

