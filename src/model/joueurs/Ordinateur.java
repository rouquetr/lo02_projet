package model.joueurs;

import java.util.Iterator;

import model.cartes.Carte;
import model.cartes.Talon;

/**
 * Représente un joueur artificiel
 * Hérite de la classe Joueur
 * @author Rouquet Raphael - Mannan Ismail
 */

public class Ordinateur extends Joueur {
	
	/**
	 * probabilité qu'a un ordinateur de contrer un autre joueur quand il joue
	 */
	private double probaContrer = 0.75;
	
	/**
	 * probabilité qu'a un ordinateur d'annoncer Carte quand il joue
	 */
	private double probaAnnoncer = 0.60;

	/**
	 * Constructeur de la classe Ordinateur
	 * @param	nom de l'ordinateur
	 * @param	position de l'ordinateur par rapport aux autres joueurs
	 */
	public Ordinateur(String nom, int position) {
		super(nom, position);
	}

	/**
	 * permet à un ordinateur d'effectuer une action (piocher ou jouer une carte)
	 */
	public void jouerCarte() {
		contrerOuAnnoncer();			// l'ordinateur commence son tour par contrer et/ou annoncer carte
		Carte carteAJouer = null;
		Iterator<Carte> it = this.getMain().iterator();
		while(it.hasNext()) {		// on parcours les cartes de la main de l'ordinateur
			Carte carteATester = it.next();
			if(verifierSiJouable(carteATester)) carteAJouer = carteATester;		// on teste si la carte est posable ou pas, si elle l'est, on la définit comme carte jouable
		}
				
		if(carteAJouer == null) piocher();		// si aucune carte jouable n'a été trouvée, l'ordinateur pioche
		else super.jouerCarte(carteAJouer);		// sinon, il joue la dernière définie comme jouable
	}
	
	/**
	 * Méthode privée
	 * permet de vérifier si l'ordinateur peut jouer une carte ou non
	 * @param	la carte à comparer avec celle du talon
	 * @return true si la carte est jouable, false sinon
	 */
	private boolean verifierSiJouable(Carte carte) {
		Carte carteTalon = Talon.getInstance().getLast();		// on récupère la dernière carte du talon
		if(carteTalon.getCouleur() != Talon.getInstance().getCouleur()) {		// si cette carte est différente de la couleur du talon, celà signifie que son action était ChangerForme
			if(carte.getCouleur() == Talon.getInstance().getCouleur() || carte.getValeur() == carteTalon.getValeur()) return true;	// on vérifie donc si la carte est compatible
			else return false;
		} else return Carte.ComparerCarte(carte, carteTalon);		// sinon, on compare juste les deux cartes entre elles
	}
	
	/**
	 * permet à l'ordinateur de contrer ou annoncer carte en fonction des probabilités établies
	 */
	private void contrerOuAnnoncer() {
		Partie partie = Partie.getInstance();
		Iterator<Joueur> iterator = partie.getJoueurs().iterator();
		while (iterator.hasNext()) {		// on parcours les joueurs
			Joueur joueur = iterator.next();
			if(Math.random() > this.probaContrer && joueur.getMain().size() == 1 && joueur != this) this.contrerJoueur(joueur);	// si le joueur n'a qu'une carte dans sa main, il y'a une probabilité de le contrer
		}
		if(Math.random() > this.probaAnnoncer && this.getMain().size() <= 2) this.setaAnnonceCarte(true);	// si l'ordinateur a deux cartes ou moins dans sa main, il a une probabilité d'annoncer carte
	}
}
