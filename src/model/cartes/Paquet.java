package model.cartes;

import java.util.Collections;
import java.util.LinkedList;

public abstract class Paquet extends LinkedList<Carte> {
		
	public void melanger() {
		Collections.shuffle(this);
	}
	public boolean ComparerCarte(Carte carte1, Carte carte2) { 
		if(carte1.getCouleur() == carte2.getCouleur() || carte1.getValeur() == carte2.getValeur()) return true;
		else return false;
	}
}
