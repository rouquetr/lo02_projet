package model.cartes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public abstract class Paquet {
	
	private LinkedHashSet<Carte> cartes;
	
	public Paquet() {
		this.cartes = new LinkedHashSet<Carte>();
	}
	
	public Paquet(LinkedHashSet<Carte> cartes) {
		this.cartes = cartes;
	}

	public LinkedHashSet<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(LinkedHashSet<Carte> cartes) {
		this.cartes = cartes;
	}
	
	public void addCarte(Carte carte) {
		this.cartes.add(carte);
	}
	
	public void removeCarte(Carte carte) {
		this.cartes.remove(carte);
	}
	
	public void melanger() {
		ArrayList<Carte> liste = new ArrayList<Carte>();
		liste.addAll(cartes);
		Collections.shuffle(liste);
		cartes.removeAll(cartes);
		cartes.addAll(liste);
	}

	@Override
	public String toString() {
		return "Paquet(" + cartes.size() + ")[cartes=" + cartes.toString() + "]";
	}

}
