package controller;

import java.util.LinkedHashSet;

import model.actions.ActionNeutre;
import model.cartes.Carte;
import model.cartes.Pioche;
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
		
		LinkedHashSet<Carte> piocheDeTest = new LinkedHashSet<Carte>();
		
		for (int i = 0; i < 13; i++)
			for (int k = 0; k < nomsOrdinateur.length; k++) 
				piocheDeTest.add(new Carte(i, k, i, new ActionNeutre()));			
		
		partie.setPioche(new Pioche(piocheDeTest));
		
		System.out.println(partie.toString());
	}
	
}
