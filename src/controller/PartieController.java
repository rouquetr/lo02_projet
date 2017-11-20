package controller;

import model.cartes.PiocheDeBase;
import model.joueurs.Joueur;
import model.joueurs.Partie;

public class PartieController {
	
	private Partie partie = Partie.getInstance();
	
	private String[] nomsOrdinateur = {"James Bond", "Roger Rabbit", "George Abitbol", "Dark Vador"};
	
	public Partie getPartie(){
		return this.partie ; 
	}
	
	public void initialiserPartie(int nombreDeJoueurs, String nomJoueur) {
		
		partie.ajouterJoueur(new Joueur(nomJoueur, 0));
		
		for (int i = 0; i < nombreDeJoueurs - 1; i++) 
			partie.ajouterJoueur(new Joueur(nomsOrdinateur[i], i + 1));
		
		partie.setPioche(new PiocheDeBase());
		
		System.out.println(partie.toString());
	}
	
}
