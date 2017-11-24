package model.cartes;

public class Talon extends Paquet {
	
	private static Talon uniqueInstance;
	private Carte derniereCarteJouee;
	
	private Talon() {
		super();
	}
	
	public static Talon getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Talon();
		return uniqueInstance;
	}
	
	public Carte getDerniereCarteJouee() {
		return derniereCarteJouee;
	}

	@Override
	public boolean add(Carte carte) {
		if (this.size() == 0 || this.derniereCarteJouee.getValeur() == carte.getValeur() || this.derniereCarteJouee.getCouleur() == carte.getCouleur()) {
			this.derniereCarteJouee = carte;
			return super.add(carte);
		}
		else return false;
	}
	
	public String afficherTalon() {
		return this.derniereCarteJouee.afficherCarteAvecDeterminant();
	}
	
}
