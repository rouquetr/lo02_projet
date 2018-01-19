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

	 /** Partie en cours */
	private Partie partie = Partie.getInstance();
	
	/** Nom de l'ordinateur */
	private String[] nomsOrdinateur = {"James Bond", "Roger Rabbit", "George Abitbol", "Dark Vador"};
	
	/**
	 * initialisation d'une nouvelle partie
	 * Ajout du joueur réel et des ordinateurs
	 * @param	nombreDeJoueurs		Nombre de joueurs de la partie
	 * @param	nomJoueur			Nom du joueur réel
	 */
	public void initialiserPartie(int nombreDeJoueurs, String nomJoueur) {
		
		Set<Joueur> joueurs = new LinkedHashSet<Joueur>();
		
		joueurs.add(new Joueur(nomJoueur, 0));
		for (int i = 0; i < nombreDeJoueurs - 1; i++) 
			joueurs.add(new Ordinateur(nomsOrdinateur[i], i + 1));
		partie.retirerTousLesJoueurs();		// on retire tous les joueurs afin d'éviter des duplicatas lorsque l'on relance une partie
		partie.ajouterJoueurs(joueurs);
	}
	
	/** 
	 * Création d'une nouvelle partie 
	 * @param	numeroVariante	numero de la variante à laquelle on veut jouer 
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
		partie.commencerNouvellePartie(variante); // on effectue toutes les opérations nécessaires pour commencer une nouvelle partie
	}
	
	/** Le joueur choisit la carte qu'il souhaite jouer
	 * @param	joueur	le joueur qui joue une carte
	 * @param	numeroAction   le numero de l'action a effectuer
	 */
	public void faireJouer(Joueur joueur, int numeroAction) {
		if(numeroAction == 0) joueur.piocher();				// si 0, le joueur pioche
		else if (numeroAction == joueur.getMain().size() + 1) joueur.setaAnnonceCarte(true);		// si le numero est superieur de 1 au nombre de cartes dans la main, le joueur annonce Carte
		else {		// sinon, on recupere la carte choisie par le joueur, et on lui fait la jouer
			Carte carteVoulue = null;
			Iterator<Carte> iterator = joueur.getMain().iterator();
			for (int i = 0; i < numeroAction && iterator.hasNext(); i++) carteVoulue = iterator.next();
			joueur.jouerCarte(carteVoulue);
		}
	}
	
	/**
	 *  L'ordinateur joue après le joueur 
	 * @param	ordinateur	l'ordinateur qui doit jouer
	 */
	public void faireJouer(Ordinateur ordinateur) {
		try {
			Thread.sleep(500);		// on delaye légèrement pour permettre de suivre les différents coups des ordinateurs
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ordinateur.jouerCarte();
	}
	
	/**
	 *  Permet de vérifier si un joueur peut jouer
	 * @param	joueur	le joueur dont on veut verifier s'il peut jouer
	 * @return 	boolean 	true si le joueur peut jouer, false sinon
	 */
	public boolean authoriserAJouer(Joueur joueur) {
		if(joueur.peutJouer()) return true;
		else {														// si le joueur ne peut pas jouer, il passe son tour
			joueur.setPeutJouer(true);								// on l'authorise à jouer pour le prochain tour
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			return false;
		}
	}
	
	
	/** 
	 * Permet de faire jouer une carte au joueur humain
	 * @param	joueur	le joueur humain
	 * @param	numeroCarte	la carte que le joueur souhaite joueur
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
	
	/** Permet au joueur d'annoncer Carte
	 * @param	joueur	Le joueur qui annonce Carte
	 */
	public void boutonAnnoncer(Joueur joueur) {
		joueur.setaAnnonceCarte(true);
	}
	
	/**
	 *  Permet au joueur de contrer un ordinateur
	 * @param	ordinateur	l'ordinateur qui sera contré
	 */
	public void boutonContrer(Ordinateur ordinateur) {
		partie.getJoueurs().get(0).contrerJoueur(ordinateur);		
	}
	
}
