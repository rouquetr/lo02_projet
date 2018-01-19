package model.cartes;

import java.util.Collections;
import java.util.LinkedList;


/**
 * Il s'agit d'une collection pouvant uniquement comporter des objets de type Carte
 * Cette collection étend LinkedList, afin de garantir l'ordre des cartes dans le paquet
 * @author Rouquet Raphael - Mannan Ismail
 */

public abstract class Paquet extends LinkedList<Carte> {
		
	/**
	 *  Permet de mélanger des cartes
	 */
	public void melanger() {
		Collections.shuffle(this);
	}
	
}
