package model.cartes;

import java.util.Iterator;
import java.util.LinkedList;

import model.joueurs.Joueur;

/**
 * Représente les cartes que le joueur peut piocher
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
	 * @param	joueurs		Collection de joueurs présent dans la partie
	 */
	public void distribuerCarte(LinkedList<Joueur> joueurs) {

		Iterator<Joueur> iterator = joueurs.iterator();
		
		while (iterator.hasNext()) iterator.next().getMain().clear();
		
		for (int i = 0; i < 8; i++) {
			iterator = joueurs.iterator();
			while(iterator.hasNext()) iterator.next().piocher(1);
		}
		
	}
	
	/** Permet de piocher une carte 
	 * 
	 */
	public Carte tirerUneCarte() {
		Carte cartePiochee = this.getLast();
		this.remove(cartePiochee);
		if(isEmpty()) Talon.getInstance().transformerEnPioche();
		return cartePiochee;
	}
	
	/** Retourne le nom de la pioche
	 * 
	 */
	public String getNom() {
		return nom;
	}

}
