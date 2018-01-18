package model.cartes;

/**
 * Représente le tas de carte disponible pour une partie
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
import java.util.Collections;
import java.util.LinkedList;

public abstract class Paquet extends LinkedList<Carte> {
		
	/**
	 *  les cartes du paquet sont mélangées
	 * 
	 */
	public void melanger() {
		Collections.shuffle(this);
	}
	
}
