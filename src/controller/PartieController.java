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

/**
 * Controleur de la partie permettant l'initialisation et le bon déroulement d'une partie
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class PartieController {
	/**
	 * Retourne la partie en cours
	 */
	private Partie partie = Partie.getInstance();
	
	/** Nom de l'ordinateur */
	private String[] nomsOrdinateur = {"James Bond", "Roger Rabbit", "George Abitbol", "Dark Vador"};
	
	/**
	 * initialisation d'une nouvelle partie
	 * Ajout du joueur réel et des ordinateurs
	 * @param	nombreDeJoueurs		permet de choisir le nombre de joueurs composant la partie, de 2 à 5
	 * @param	nomJoueur			enregistre le nom du joueur réel
	 */
	public void initialiserPartie(int nombreDeJoueurs, String nomJoueur) {
		
		Set<Joueur> joueurs = new LinkedHashSet<Joueur>();
		
		joueurs.add(new Joueur(nomJoueur, 0));
		for (int i = 0; i < nombreDeJoueurs - 1; i++) 
			joueurs.add(new Ordinateur(nomsOrdinateur[i], i + 1));
		partie.retirerTousLesJoueurs();
		partie.ajouterJoueurs(joueurs);
	}
	
	/** 
	 * Création d'une nouvelle partie 
	 * Le joueur choisit à quel type de variante il veut jouer
	 * @param	numeroVariante	permet de choisir la variante du jeu que l'on veut jouer 
	 */
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
	
	/** Le joueur choisit la carte qu'il souhaite jouer
	 * @param	joueur	le joueur qui joue une carte
	 * @param	numeroCarte   la carte à jouer
	 */
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
	
	/** Création d'un bouton pour jouer une partie
	 * @param	joueur	le joueur qui joue une carte
	 * @param	numeroCarte	la carte que l'on souhaite jouer
	 */
	public void boutonJouer(Joueur joueur, int numeroCarte) {
		Carte carteVoulue = null;
		Iterator<Carte> iterator = joueur.getMain().iterator();
		for (int i = 0; i < numeroCarte && iterator.hasNext(); i++) carteVoulue = iterator.next();
		joueur.jouerCarte(carteVoulue);
	}
	
	/**
	 * Permet au joueur de piocher une carte
	 * @param	joueur	Le joueur qui pioche la carte
	 */
	public void boutonPiocher(Joueur joueur) {
		joueur.piocher();
	}
	
	/** Le joueur peut annoncer Carte
	 * @param	joueur	Le joueur qui annonce Carte
	 */
	public void boutonAnnoncer(Joueur joueur) {
		joueur.setaAnnonceCarte(true);
	}
	
	/**
	 *  Le joueur peut contrer l'ordinateur
	 * @param	ordinateur	l'ordinateur qui sera contré
	 */
	public void boutonContrer(Ordinateur ordinateur) {
		partie.getJoueurs().get(0).contrerJoueur(ordinateur);		
	}
	
	
	/**
	 *  L'ordinateur joue après le joueur 
	 * @param	ordinateur	l'ordinateur qui joue une carte
	 */
	public void faireJouer(Ordinateur ordinateur) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ordinateur.jouerCarte();
	}
	
	/**
	 *  le joueur est autorisé à jouer
	 * @param	joueur	le joueur qui peut jouer une carte
	 */
	public boolean authoriserAJouer(Joueur joueur) {
		if(joueur.peutJouer()) return true;
		else {
			joueur.setPeutJouer(true);
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			return false;
		}
	}
	
}
