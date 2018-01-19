package model.cartes;

import java.util.Iterator;
import java.util.LinkedList;

import model.joueurs.Joueur;

/**
 * Représente la pioche d'une partie
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public abstract class Pioche extends Paquet {
	
	/** nom de la pioche, pour différencier les variantes **/
	private String nom;
	
	/** Constructeur de la pioche 
	 * @param	nom
	 */
	public Pioche(String nom) {
		this.nom = nom;
	}
	
	/** Les cartes sont distribuées aux joueurs
	 * @param	joueurs		Collection de joueurs présents dans la partie
	 */
	public void distribuerCarte(LinkedList<Joueur> joueurs) {

		Iterator<Joueur> iterator = joueurs.iterator();
		
		while (iterator.hasNext()) iterator.next().getMain().clear();
		
		for (int i = 0; i < 8; i++) {
			iterator = joueurs.iterator();
			while(iterator.hasNext()) iterator.next().piocher(1);
		}
		
	}
	
	/** 
	 * Permet de piocher une carte 
	 * @return la carte piochée
	 */
	public Carte tirerUneCarte() {
		Carte cartePiochee = this.getLast();		// on récupère la carte sur le dessus de la pioche
		this.remove(cartePiochee);				// on la retire de la pioche
		if(isEmpty()) Talon.getInstance().transformerEnPioche();		// si la pioche est vide, on transforme le talon en pioche
		return cartePiochee;
	}
	
	/** 
	 * Retourne le nom de la pioche
	 * @return la chaîne de caractères
	 */
	public String getNom() {
		return nom;
	}

}
