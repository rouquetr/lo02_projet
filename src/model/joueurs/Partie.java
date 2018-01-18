package model.joueurs;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Set;

import model.cartes.Pioche;
import model.cartes.Talon;

/**
 * Représente la partie en cours
 * Hérite de la classe Observable
 * @author Rouquet Rafael - Mannan Ismail
 *
 */

public class Partie extends Observable {
	
	/**
	 * singleton de la partie
	 */
	private static Partie uniqueInstance;
	
	/**
	 * récupère le talon pour la partie
	 */
	private Talon talon = Talon.getInstance();
	/**
	 * pioche disponible pour la partie
	 */
	private Pioche pioche;
	
	/**
	 * liste des joueurs qui composent la partie
	 */
	private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	/**
	 * joueur en cours
	 */
	private Joueur joueurEnCours = null;
	
	/**
	 * Nombre minimal de joueur pour une partie
	 */
    public final static int MINJOUEUR = 2;
    /**
	 * Nombre maximal de joueur pour une partie
	 */
    public final static int MAXJOUEUR = 5;
	
    /** 
     * Constructeur privé pour assurer l'unicité de la partie
     */
	private Partie() {	}
	
	/**
	 * récupère la partie en cours
	 */
	public static Partie getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Partie();
		return uniqueInstance;
	}
	
	/**
	 * Permet au joueur de commencer une nouvelle partie
	 * Mélange, distribution des cartes
	 * @param	variante
	 */
	public void commencerNouvellePartie(Pioche variante) {
		this.pioche = variante;
		this.pioche.melanger();
		this.pioche.distribuerCarte(this.joueurs);
		
		this.talon.clear();
		this.talon.add(this.pioche.tirerUneCarte());
		
		this.joueurEnCours = this.joueurs.getFirst();
		this.setChanged();
		this.notifyObservers("commencerNouvellePartie");
	}
	
	/**
	 * permet de modifier le sens de la partie en cours
	 */
	public void changerSens() {
		Collections.reverse(joueurs);
	}
	
	/** 
	 * retourne le talon 
	 */
	public Talon getTalon() {
		return talon;
	}
	
	/**
	 * retourne la pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}
	
	/**
	 * modifie la pioche
	 * @param	pioche
	 */
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;	
	}
	
	/**
	 * retourne les joueurs qui composent la partie
	 */
	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * retourne les points de chaque joueur pour établir un classement
	 */
	public LinkedList<Joueur> getJoueursByScore() {
		LinkedList<Joueur> classement = new LinkedList<>(joueurs);
		classement.sort(new Comparator<Joueur>() {
			@Override
			public int compare(Joueur o1, Joueur o2) {
				return Integer.compare(o1.getPoints(), o2.getPoints());
			}
		});
		return classement;
	}
	
	public void ajouterJoueurs(Set<Joueur> joueurs) {
		this.joueurs.addAll(joueurs);
		this.setChanged();
		this.notifyObservers("ajouterJoueurs");
	}
	
	/**
	 * permet de retirer les joueurs de la collection
	 */
	public void retirerTousLesJoueurs() {
		this.joueurs.removeAll(this.joueurs);
	}
	
	/**
	 * récupère le joueur en cours
	 */
	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}
	
	/**
	 * modifie le joueur en cours
	 */
	public void setJoueurEnCours(Joueur joueurEnCours) {
		this.joueurEnCours = joueurEnCours;
		this.setChanged();
		this.notifyObservers("setJoueurEnCours");	
	}
	
	/** 
	 * permet de connaitre l'identité du joueur précédent le joueur en cours
	 */
	public Joueur findJoueurPrecedent() {
		Iterator<Joueur> iterator = joueurs.descendingIterator();
		while(iterator.hasNext()) 
			if(iterator.next().equals(joueurEnCours)) {
				if (!iterator.hasNext()) return joueurs.get(joueurs.size() - 1);
				return iterator.next();
			}
		return null;
	}
	
	/**
	 * permet de connaitre l'identité du joueur suivant le joueur en cours
	 */
	public Joueur findJoueurSuivant() {
		Iterator<Joueur> iterator = joueurs.iterator();
		while(iterator.hasNext()) 
			if(iterator.next().equals(joueurEnCours)) {
				if (!iterator.hasNext()) return joueurs.get(0);
				return iterator.next();
			}
		return null;
	}
	
	/**
	 * permet d'afficher la partie avec le nom de la variante, les joueurs qui la compose et la carte visible du talon
	 */
	public String afficherPartie() {
		String message = "La partie (variante: " + pioche.getNom() +") est compos�e de:\n";
		Iterator<Joueur> iterator = joueurs.iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			message += joueur.getNom() + " avec " + joueur.getMain().size() + " cartes\n";
		}
		message += "La carte visible du talon est " + talon.afficherTalon();
		return message;
	}
	
	/**
	 * permet de mettre à jour le score de chaque joueur
	 */
	public void mettreAJourScores() {
		Iterator<Joueur> iterator = joueurs.iterator();
		while (iterator.hasNext()) 	{
			Joueur joueur = iterator.next();
			joueur.compterPoints();
		}
		this.setChanged();
		this.notifyObservers("mettreAJourScores");	
	}
	
}
