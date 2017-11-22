package model.cartes;

import java.util.Iterator;
import java.util.LinkedHashSet;

import model.joueurs.Joueur;

public abstract class Pioche extends Paquet {
	
	public void distribuerCarte(LinkedHashSet<Joueur> joueurs) {
		
		for (int i = 0; i < 8; i++) {
			Iterator<Joueur> iterator = joueurs.iterator();
			while(iterator.hasNext()) iterator.next().piocher();
		}
		
	}
	
	public Carte recupererPremiereCarte() {
		Iterator<Carte> iterator = this.iterator();
		
		Carte cartePiochee = iterator.next();
		this.remove(cartePiochee);
		
		return cartePiochee;
	}

}
