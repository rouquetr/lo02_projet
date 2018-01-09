package model.cartes;

import java.util.Iterator;
import java.util.LinkedList;

import model.joueurs.Joueur;

public abstract class Pioche extends Paquet {
	
	private String nom;
	
	public Pioche(String nom) {
		this.nom = nom;
	}
	
	public void distribuerCarte(LinkedList<Joueur> joueurs) {

		Iterator<Joueur> iterator = joueurs.iterator();
		
		while (iterator.hasNext()) iterator.next().getMain().clear();
		
		for (int i = 0; i < 1; i++) {
			iterator = joueurs.iterator();
			while(iterator.hasNext()) iterator.next().piocher(1);
		}
		
	}
	
	public Carte tirerUneCarte() {
		Carte cartePiochee = this.getLast();
		this.remove(cartePiochee);
		if(isEmpty()) Talon.getInstance().transformerEnPioche();
		return cartePiochee;
	}
	
	public String getNom() {
		return nom;
	}

}
