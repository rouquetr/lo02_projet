package model.cartes;

import java.util.Collections;
import java.util.LinkedList;

public abstract class Paquet extends LinkedList<Carte> {
		
	public void melanger() {
		Collections.shuffle(this);
	}
	
}
