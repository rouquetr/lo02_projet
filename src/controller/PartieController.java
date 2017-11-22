package controller;

import model.cartes.PiocheDeBase;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
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
			partie.ajouterJoueur(new Ordinateur(nomsOrdinateur[i], i + 1));
		
		System.out.println(partie.getJoueurs());
		
		partie.setPioche(new PiocheDeBase());
	}
	
	public void lancerPartie() {
		partie.getPioche().melanger();
		partie.getPioche().distribuerCarte(partie.getJoueurs());
		
		partie.getTalon().add(partie.getPioche().recupererPremiereCarte());
	}
	
	public void faireJouer(Joueur joueur, int carteAJouer) {
		joueur.jouerCarte(carteAJouer);
	}
	
}
