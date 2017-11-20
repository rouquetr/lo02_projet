package model.cartes;

import java.util.Iterator;
import java.util.LinkedHashSet;

import model.joueurs.Joueur;

public abstract class Pioche extends Paquet {
	
	public void distribuerCarte(LinkedHashSet<Joueur> joueurs) {
		
		Iterator<Joueur> iterator = joueurs.iterator();
		
		while(iterator.hasNext()) {
			iterator.next().getMain().addCarte(piocher());
		}
		
	}
	
	public Carte piocher() {
		Iterator<Carte> iterator = this.getCartes().iterator();
		
		Carte cartePiochee = iterator.next();
		this.getCartes().remove(cartePiochee);
		
		return cartePiochee;
	}

}
