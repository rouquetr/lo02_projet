package model.cartes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.actions.ChangerForme;
import model.joueurs.Partie;

public class Talon extends Paquet {
	
	private static Talon uniqueInstance;
	private int couleur = 5;
	
	private Talon() {
		super();
	}
	
	public static Talon getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Talon();
		return uniqueInstance;
	}

	@Override
	public boolean add(Carte carte) {
		if(size() == 0 || carte.getCouleur() == couleur || Carte.ComparerCarte(getLast(), carte)) {
			couleur = carte.getCouleur();
			return super.add(carte);
		} else return false;
	}
	
	public int getCouleur() {
		return couleur;
	}
	
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	
	public String afficherTalon() {
		return getLast().afficherCarteAvecDeterminant();
	}
	
	public void transformerEnPioche() throws NoSuchElementException {
		Pioche pioche = Partie.getInstance().getPioche();
		Iterator<Carte> iterator = iterator();
		while (iterator.hasNext()) pioche.add(iterator.next());
		pioche.remove(getLast());
		removeAll(pioche);
	}
	
}
