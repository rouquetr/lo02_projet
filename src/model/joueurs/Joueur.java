package model.joueurs;

import java.util.Iterator;
import java.util.Observable;

import model.cartes.Carte;
import model.cartes.Main;

/**
 * Représente un joueur réel
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class Joueur extends Observable {
	
	/**
	 * nom du joueur
	 */
	private String nom;
	/**
	 * position du joueur réel par rapport aux autres
	 */
	private int position;
	/**
	 * nombre de points du joueur
	 */
	private int points;
	
	/**
	 * permet de savoir si le joueur peut poser une carte sur le talon ou non
	 */
	private boolean peutJouer = true;
	/**
	 * permet de faire rejouer le joueur
	 */
	private boolean doitRejouer = false;
	/**
	 * permet de savoir si oui ou non le joueur a annoncé Carte
	 */
	private boolean aAnnonceCarte = false;
	
	/**
	 * Main du joueur
	 */
	private Main main;
	
	/**
	 * Constructeur de la classe Joueur
	 * @param	nom			
	 * @param	position	
	 */
	public Joueur(String nom, int position) {
		this.nom = nom;
		this.position = position;
		this.points = 0;
		this.main = new Main();
	}
	
	/**
	 * retourne le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * retourne la position du joueur
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * retourne le nombre de points du joueur
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * retourne la main du joueur
	 */
	public Main getMain() {
		return main;
	}
	/**
	 * retourne si le joueur peut jouer ou non
	 */
	public boolean peutJouer() {
		return peutJouer;
	}
	
	/**
	 * modifie la valeur indiquant que le joueur peut jouer ou non
	 * @param	peutJouer
	 */
	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}
	
	/**
	 * modifie le fait que le joueur dit rejouer ou non
	 * @param	doitRejouer
	 */
	public void setDoitRejouer(boolean doitRejouer) {
		this.doitRejouer = doitRejouer;
	}
	
	/**
	 * retourne si le joueur a annoncé carte ou non
	 */
	
	public boolean aAnnonceCarte() {
		return aAnnonceCarte;
	}

	/**
	 * modifie le fait d'annoncer Carte
	 * @param aAnnonceCarte
	 */
	public boolean setaAnnonceCarte(boolean aAnnonceCarte) {
		if(this.main.size() > 2) {
			System.out.println(this.getNom() + " ne peut pas annoncer Carte");
			return false; 
		}
		this.aAnnonceCarte = aAnnonceCarte;
		System.out.println(this.getNom() + " a annoncé Carte");	
		return true;
	}

	/**
	 * Affichage du nom, de la position, des points et de la main du joueur
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", position=" + position + ", points=" + points + ", main=" + main + "]";
	}
	
	/**
	 * permet au joueur de piocher une carte
	 */
	public void piocher() {
		aAnnonceCarte = false;
		this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
		this.setChanged();
		this.notifyObservers("piocher");
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurSuivant());
	}
	
	/**
	 * permet au joueur de piocher un nombre donné de carte
	 * @param	nombreCartes	le nombre de cartes a piocher
	 */
	public void piocher(int nombreCartes) {
		aAnnonceCarte = false;
		for (int i = 0; i < nombreCartes; i++) this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
	}
	
	/**
	 * permet au joueur de poser une carte sur le talon
	 * @param	carte	La carte posée sur le talon
	 */
	public void jouerCarte(Carte carte) {
		this.setChanged();
		if(Partie.getInstance().getTalon().add(carte)) {
			this.main.remove(carte);
			carte.effectuerAction();
			if(main.size() > 1) aAnnonceCarte = false;
			if(main.size() == 0) Partie.getInstance().mettreAJourScores();
			else {
				this.notifyObservers("jouerCarte");
				if(this.doitRejouer) {
					Partie.getInstance().setJoueurEnCours(Partie.getInstance().getJoueurEnCours());
					this.doitRejouer = false;
				}
				else Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurSuivant());
			}
		} else {
			this.notifyObservers("jouerCarteErreur");
		}
	}
	
	/**
	 * permet de compter les points du joueur
	 */
	public void compterPoints() {
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) {
			points += iterator.next().getPoints();
		}
	}
	
	/**
	 * permet de contrer un autre joueur
	 * @param	joueur	le joueur à contrer
	 */
	public void contrerJoueur(Joueur joueur) {
		if(joueur.getMain().size() == 1 && joueur.aAnnonceCarte() == false) {
			joueur.piocher(2);
			System.out.println(this.getNom() + " a contr� " + joueur.getNom());
		}
	}

}
