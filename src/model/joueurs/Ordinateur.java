package model.joueurs;

import java.util.Iterator;

import model.cartes.Carte;
import model.cartes.Talon;

/**
 * Représente un joueur artificiel
 * Hérite de la classe Joueur
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class Ordinateur extends Joueur {
	
	/**
	 * probabilité qu'a un joueur de contrer un autre joueur
	 */
	private double probaContrer = 0.75;
	/**
	 * probabilité qu'a un joueur d'annoncer Carte sans se faire contrer
	 */
	private double probaAnnoncer = 0.60;

	/**
	 * Constructeur de la classe Ordinateur
	 * @param	nom		nom de l'ordinateur
	 * @param	position	position de l'ordinateur par rapport aux autres joueur
	 */
	public Ordinateur(String nom, int position) {
		super(nom, position);
	}

	/**
	 * permet à un ordinateur de poser une carte sur le talon
	 */
	public int jouerCarte() {
		contrerOuAnnoncer();
		Carte carteAJouer = null;
		Iterator<Carte> it = this.getMain().iterator();
		while(it.hasNext()) {
			Carte carteATester = it.next();
			if(verifierSiJouable(carteATester)) carteAJouer = carteATester;
		}
				
		if(carteAJouer == null) {
			piocher();
			return 0;
		}
		else {
			super.jouerCarte(carteAJouer);
			return 1;
		}
	}
	
	/**
	 * permet de vérifier si l'ordinateur peut jouer une carte ou non
	 * @param	carte	la carte à comparer avec celle du talon
	 */
	private boolean verifierSiJouable(Carte carte) {
		Carte carteTalon = Talon.getInstance().getLast();
		if(carteTalon.getCouleur() != Talon.getInstance().getCouleur()) {
			if(carte.getCouleur() == Talon.getInstance().getCouleur() || carte.getValeur() == carteTalon.getValeur()) return true;
			else return false;
		} else return Carte.ComparerCarte(carte, carteTalon);
	}
	
	/**
	 * permet à l'ordinateur de contrer ou annoncer carte en fonction des probabilités établies
	 */
	private void contrerOuAnnoncer() {
		Partie partie = Partie.getInstance();
		Iterator<Joueur> iterator = partie.getJoueurs().iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			if(Math.random() > this.probaContrer && joueur.getMain().size() == 1 && joueur != this) this.contrerJoueur(joueur);
		}
		if(Math.random() > this.probaAnnoncer && this.getMain().size() <= 2) this.setaAnnonceCarte(true);
	}
}
