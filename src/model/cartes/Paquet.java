package model.cartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public abstract class Paquet extends LinkedHashSet<Carte> {
		
	public void melanger() {
		ArrayList<Carte> liste = new ArrayList<Carte>();
		liste.addAll(this);
		Collections.shuffle(liste);
		this.removeAll(this);
		this.addAll(liste);
	}

}
