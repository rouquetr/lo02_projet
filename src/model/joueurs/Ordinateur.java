package model.joueurs;

import java.util.Iterator;

import model.cartes.Carte;
import model.cartes.Talon;

public class Ordinateur extends Joueur {
	
	private double proba = 0.75;

	public Ordinateur(String nom, int position) {
		super(nom, position);
	}

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
	
	
	private boolean verifierSiJouable(Carte carte) {
		Carte carteTalon = Talon.getInstance().getLast();
		if(carteTalon.getCouleur() != Talon.getInstance().getCouleur()) {
			if(carte.getCouleur() == Talon.getInstance().getCouleur() || carte.getValeur() == carteTalon.getValeur()) return true;
			else return false;
		} else return Carte.ComparerCarte(carte, carteTalon);
	}
	
	private void contrerOuAnnoncer() {
		Partie partie = Partie.getInstance();
		Iterator<Joueur> iterator = partie.getJoueurs().iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			if(Math.random() > this.proba && joueur.getMain().size() == 1 && joueur != this) this.contrerJoueur(joueur);
		}
		if(Math.random() > this.proba && this.getMain().size() <= 2) {
			this.setaAnnonceCarte(true);
		}
	}
}
