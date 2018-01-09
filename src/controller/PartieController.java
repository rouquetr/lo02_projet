package controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import model.cartes.Carte;
import model.cartes.Pioche;
import model.cartes.PiocheDeBase;
import model.cartes.PiocheMonclar;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

public class PartieController {
	
	private Partie partie = Partie.getInstance();
	
	private String[] nomsOrdinateur = {"James Bond", "Roger Rabbit", "George Abitbol", "Dark Vador"};
	
	public void initialiserPartie(int nombreDeJoueurs, String nomJoueur) {
		
		Set<Joueur> joueurs = new LinkedHashSet<Joueur>();
		
		joueurs.add(new Joueur(nomJoueur, 0));
		for (int i = 0; i < nombreDeJoueurs - 1; i++) 
			joueurs.add(new Ordinateur(nomsOrdinateur[i], i + 1));
		partie.ajouterJoueurs(joueurs);
	}
	
	public void lancerPartie(int numeroVariante) {
		Pioche variante = null;
		switch (numeroVariante) {
		case 2:
			variante = new PiocheMonclar();
			break;
		default:
			variante = new PiocheDeBase();
			break;
		}
		partie.commencerNouvellePartie(variante);
	}
	
	public void faireJouer(Joueur joueur, int numeroCarte) {
		if(numeroCarte == 0) joueur.piocher();
		else if (numeroCarte == joueur.getMain().size() + 1) joueur.setaAnnonceCarte(true);
		else {
			Carte carteVoulue = null;
			Iterator<Carte> iterator = joueur.getMain().iterator();
			for (int i = 0; i < numeroCarte && iterator.hasNext(); i++) carteVoulue = iterator.next();
			joueur.jouerCarte(carteVoulue);
		}
	}
	
	public void boutonJouer(Joueur joueur, int numeroCarte) {
		Carte carteVoulue = null;
		Iterator<Carte> iterator = joueur.getMain().iterator();
		for (int i = 0; i < numeroCarte && iterator.hasNext(); i++) carteVoulue = iterator.next();
		joueur.jouerCarte(carteVoulue);
	}
	
	public void boutonPiocher(Joueur joueur) {
		joueur.piocher();
	}
	
	public void faireJouer(Ordinateur ordinateur) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ordinateur.jouerCarte();
	}
	
	public boolean authoriserAJouer(Joueur joueur) {
		if(joueur.peutJouer()) return true;
		else {
			joueur.setPeutJouer(true);
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			return false;
		}
	}
	
	public void terminerPartie() {
		partie.mettreAJourScores();
	}
	
}
