package model.cartes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.joueurs.Partie;

/**
 * Représente le talon de la partie
 * @author Rouquet Rafael - Mannan Ismail
 *
 */

public class Talon extends Paquet {
	
	/** 
	 * Singleton du talon
	 */
	private static Talon uniqueInstance;
	
	/** couleur de la carte posée sur le talon
	 * 
	 */
	private int couleur = 5;
	
	/** Constructeur du Talon
	 * 
	 */
	private Talon() {
		super();
	}
	
	/** retourne le talon de la partie en cours
	 * 
	 */
	public static Talon getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Talon();
		return uniqueInstance;
	}

	/** permet d'ajouter des cartes sur le talon 
	 * @param carte la carte à poser sur le talon
	 * @return boolean
	 */
	@Override
	public boolean add(Carte carte) {
		if(size() == 0 || carte.getCouleur() == couleur || carte.getValeur() == getLast().getValeur()) {
			couleur = carte.getCouleur();
			return super.add(carte);
		} else return false;
	}
	
	/** retourne la couleur de la carte au dessus du talon
	 * 
	 */
	public int getCouleur() {
		return couleur;
	}
	
	/**
	 * modifie la couleur de la carte du talon
	 * @param couleur 
	 */
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * affichage console de la carte posée sur le dessus de talon
	 */
	public String afficherTalon() {
		return getLast().afficherCarteAvecDeterminant();
	}
	
	
	/**
	 * Permet de transformer le talon en pioche lorsque toutes les cartes sont utilisées
	 */
	public void transformerEnPioche() throws NoSuchElementException {
		Pioche pioche = Partie.getInstance().getPioche();
		Iterator<Carte> iterator = iterator();
		while (iterator.hasNext()) pioche.add(iterator.next());
		pioche.remove(getLast());
		removeAll(pioche);
	}
	
}
