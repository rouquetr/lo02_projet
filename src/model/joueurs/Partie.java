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
	 * On a choisi LinkedList pour garantir l'ordre des joueurs au cours de la partie (à cause de l'enchaînement des tours)
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
	 * @param	un objet de type pioche: la variante choisie
	 */
	public void commencerNouvellePartie(Pioche variante) {
		this.pioche = variante;		
		this.pioche.melanger();						//on mélange la pioche
		this.pioche.distribuerCarte(this.joueurs);	// puis on distribue les cartes
		
		this.talon.clear();							// on nettoie le talon, s'il y'avait une partie en cours précédemment
		this.talon.add(this.pioche.tirerUneCarte());	// on ajoute une carte au talon
		
		this.joueurEnCours = this.joueurs.getFirst(); // on lance le premier tour de jeu
		this.setChanged();
		this.notifyObservers("commencerNouvellePartie");	// on notifie les observers qu'une partie vient de commencer
	}
	
	/**
	 * permet de modifier le sens de la partie en cours
	 */
	public void changerSens() {
		Collections.reverse(joueurs);
	}
	
	/** 
	 * Permet de récupérer le talon 
	 * @return le talon
	 */
	public Talon getTalon() {
		return talon;
	}
	
	/**
	 * Permet de récupérer la pioche
	 * @return la pioche
	 */
	public Pioche getPioche() {
		return pioche;
	}
	
	/**
	 * Permet de récupérer les joueurs qui composent la partie
	 * @return la liste des joueurs
	 */
	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Permet de récupérer les joueurs ordonnés selon leurs points
	 * @return	la liste des joueurs ordonnée selon leurs points
	 */
	public LinkedList<Joueur> getJoueursByScore() {
		LinkedList<Joueur> classement = new LinkedList<>(joueurs);		// on créé une nouvelle liste afin de ne pas altérer l'ancienne
		classement.sort(new Comparator<Joueur>() {		// on compare chaque joueur pour les ordonner selon leur nombre de points
			@Override
			public int compare(Joueur o1, Joueur o2) {
				return Integer.compare(o1.getPoints(), o2.getPoints());
			}
		});
		return classement;
	}
	
	/**
	 * Permet d'ajouter des joueurs à la partie
	 * @param un Set de joueurs à ajouter, afin de garantir l'unicité des joueurs
	 */
	public void ajouterJoueurs(Set<Joueur> joueurs) {
		this.joueurs.addAll(joueurs);
		this.setChanged();
		this.notifyObservers("ajouterJoueurs");	// on notifie les observers que des joueurs ont été ajoutés à la partie
	}
	
	/**
	 * permet de nettoyer la partie en retirant tous les joueurs
	 */
	public void retirerTousLesJoueurs() {
		this.joueurs.removeAll(this.joueurs);
	}
	
	/**
	 * Permet de récupérer le joueur en cours
	 * @return le joueur en cours
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
		this.notifyObservers("setJoueurEnCours");	// on notifie les observers que le joueur en cours a été changé, et donc que c'est à un autre joueur de jouer
	}
	
	/** 
	 * Permet de récupérer le joueur précédent le joueur en cours
	 * @return le joueur précédent
	 */
	public Joueur findJoueurPrecedent() {
		Iterator<Joueur> iterator = joueurs.descendingIterator();		// on créé un iterator descendant
		while(iterator.hasNext()) 
			if(iterator.next().equals(joueurEnCours)) {		// on cherche le joueur en cours
				if (!iterator.hasNext()) return joueurs.get(joueurs.size() - 1);		// si il n'y a pas de suite à l'iterator, on retourne le dernier joueur de la liste
				return iterator.next();												// sinon on retourne la suite de l'iterator
			}
		return null;																	// si on ne trouve personne (erreur ou nombre de joueurs égal à 1), on retourne null
	}
	
	/**
	 * Permet de récupérer le joueur suivant le joueur en cours
	 * @return le joueur suivant
	 */
	public Joueur findJoueurSuivant() {
		Iterator<Joueur> iterator = joueurs.iterator();
		while(iterator.hasNext()) 
			if(iterator.next().equals(joueurEnCours)) {		// on cherche le joueur en cours
				if (!iterator.hasNext()) return joueurs.get(0);	// si il n'y a pas de suite à l'itérateur, on retourne le premier joueur de la liste
				return iterator.next();							// sinon on retourne le joueur suivant
			}
		return null;												// si on ne trouve personne (erreur ou nombre de joueurs égal à 1), on retourne null
	}
	
	/**
	 * permet de retourner un message les informations de la partie (le nom de la variante, les joueurs qui la compose et la carte visible du talon)
	 * @return la chaîne de caractères
	 */
	public String afficherPartie() {
		String message = "La partie (variante: " + pioche.getNom() +") est composée de:\n";		// on donne le nom de la variante jouée
		Iterator<Joueur> iterator = joueurs.iterator();
		while (iterator.hasNext()) {			// on liste les joueurs avec leur nom et leur nombre de cartes
			Joueur joueur = iterator.next();
			message += joueur.getNom() + " avec " + joueur.getMain().size() + " cartes\n";
		}
		message += "La carte visible du talon est " + talon.afficherTalon();	// on affiche la carte visible du talon
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
		this.notifyObservers("mettreAJourScores");		// on notifie les observers que la partie vient de se terminer
	}
	
}
