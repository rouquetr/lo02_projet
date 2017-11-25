package model.cartes;

import java.util.Iterator;
import java.util.LinkedList;

import model.joueurs.Joueur;

public abstract class Pioche extends Paquet {
	
	public void distribuerCarte(LinkedList<Joueur> joueurs) {
		
		for (int i = 0; i < 8; i++) {
			Iterator<Joueur> iterator = joueurs.iterator();
			while(iterator.hasNext()) iterator.next().piocher();
		}
		
	}
	
	public Carte tirerUneCarte() {
		Carte cartePiochee = this.getLast();
		this.remove(cartePiochee);
		
		return cartePiochee;
	}

}
