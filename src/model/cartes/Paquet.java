package model.cartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public abstract class Paquet extends LinkedHashSet<Carte> {
		
	public void melanger() {
		ArrayList<Carte> liste = new ArrayList<Carte>(this);
		Collections.shuffle(liste);
		this.removeAll(this);
		this.addAll(liste);
	}
	
	public Carte getFirstElement() {
		return this.iterator().next();
	}
	
	public Carte getLastElement() {
		ArrayList<Carte> liste = new ArrayList<Carte>(this);
		return liste.get(liste.size()-1);
	}

}
