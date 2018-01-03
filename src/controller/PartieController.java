package controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import model.cartes.Carte;
import model.cartes.PiocheDeBase;
import model.cartes.PiocheMonclar;
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
		
		Set<Joueur> joueurs = new LinkedHashSet<Joueur>();
		
		joueurs.add(new Joueur(nomJoueur, 0));
		for (int i = 0; i < nombreDeJoueurs - 1; i++) 
			joueurs.add(new Ordinateur(nomsOrdinateur[i], i + 1));
		partie.ajouterJoueurs(joueurs);
	}
	
	public void lancerPartie(int numeroVariante) {
		System.out.println(numeroVariante);
		switch (numeroVariante) {
		case 2:
			partie.setPioche(new PiocheMonclar());
			break;
		default:
			partie.setPioche(new PiocheDeBase());
			break;
		}
		
		partie.getPioche().melanger();
		partie.getPioche().distribuerCarte(partie.getJoueurs());
		
		partie.getTalon().clear();
		partie.getTalon().add(partie.getPioche().tirerUneCarte());
		
		partie.setJoueurEnCours(partie.getJoueurs().getFirst());
	}
	
	public void faireJouer(Joueur joueur, int numeroCarte) {
		if(numeroCarte == 0) {
			joueur.piocher();
			if(partie.getPioche().isEmpty()) partie.getTalon().transformerEnPioche();
		} 
		else if (numeroCarte == joueur.getMain().size() + 1) joueur.setaAnnonceCarte(true);
		else {
			Carte carteVoulue = null;
			Iterator<Carte> iterator = joueur.getMain().iterator();
			for (int i = 0; i < numeroCarte && iterator.hasNext(); i++) carteVoulue = iterator.next();
			joueur.jouerCarte(carteVoulue);
		}
	}
	
	public void faireJouer(Ordinateur ordinateur) {
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
