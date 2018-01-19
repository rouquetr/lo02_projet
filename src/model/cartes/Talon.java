package model.cartes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.joueurs.Partie;

/**
 * Représente le talon de la partie
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class Talon extends Paquet {
	
	/** 
	 * Singleton du talon
	 */
	private static Talon uniqueInstance;
	
	/** 
	 * couleur de la carte posée sur le talon
	 */
	private int couleur = 5;
	
	/** 
	 * Constructeur privé du Talon
	 */
	private Talon() {
		super();
	}
	
	/** 
	 * permet de récupérer l'unique instance du talon
	 * @return l'instance du talon
	 */
	public static Talon getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Talon();
		return uniqueInstance;
	}

	/** 
	 * permet d'ajouter des cartes sur le talon 
	 * override la méthode add de LinkedList dont est issu le paquet
	 * @param carte la carte à poser sur le talon
	 * @return true si la carte a bien été ajoutée, false sinon
	 */
	@Override
	public boolean add(Carte carte) {
		if(size() == 0 || carte.getCouleur() == couleur || carte.getValeur() == getLast().getValeur()) {	// on verifie si la carte est bien compatible avec le talon
			couleur = carte.getCouleur();		// le talon prend la couleur de la carte jouée
			return super.add(carte);
		} else return false;
	}
	
	/** 
	 * @return la couleur du talon
	 */
	public int getCouleur() {
		return couleur;
	}
	
	/**
	 * modifie la couleur de la carte du talon
	 * @param couleur 
	 */
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Permet de récupérer la carte posée sur le dessus de talon de manière formatée
	 * @return la chaîne de caractères
	 */
	public String afficherTalon() {
		return getLast().afficherCarteAvecDeterminant();
	}
	
	
	/**
	 * Permet de transformer le talon en pioche lorsque toutes les cartes sont utilisées
	 */
	public void transformerEnPioche() throws NoSuchElementException {
		Pioche pioche = Partie.getInstance().getPioche();		// on récupère la pioche
		Iterator<Carte> iterator = iterator();
		while (iterator.hasNext()) pioche.add(iterator.next());		// On ajoute toutes les cartes du talon à la pioche
		pioche.remove(getLast());			// on retire cependant la dernière carte de la pioche
		removeAll(pioche);					// on retire toutes les cartes présentes dans la pioche du talon, et avec la ligne précédente on garde donc une carte dans le talon
	}
	
}
