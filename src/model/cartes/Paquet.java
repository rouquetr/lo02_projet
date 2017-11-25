package model.cartes;

import java.util.Collections;
import java.util.LinkedList;

public abstract class Paquet extends LinkedList<Carte> {
		
	public void melanger() {
		Collections.shuffle(this);
	}
	public boolean ComparerCarte(Carte carteAJouer, Carte carteTalon) { 
		if(carteTalon.equals(carteAJouer.getCouleur()) || carteTalon.equals(carteAJouer.getValeur())) {
			return true;
		}
		else return false;
	}
}
