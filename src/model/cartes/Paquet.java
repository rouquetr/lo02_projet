package model.cartes;

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

}
