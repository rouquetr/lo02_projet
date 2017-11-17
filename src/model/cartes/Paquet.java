package model.cartes;

import java.util.TreeSet;

public abstract class Paquet {
	
	private TreeSet<Carte> cartes;
	
	public Paquet() {
		this.cartes = new TreeSet<Carte>();
	}
	
	public Paquet(TreeSet<Carte> cartes) {
		this.cartes = cartes;
	}

	public TreeSet<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(TreeSet<Carte> cartes) {
		this.cartes = cartes;
	}

}
