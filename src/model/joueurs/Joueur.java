package model.joueurs;

import java.util.Iterator;
import java.util.Observable;

import model.cartes.Carte;
import model.cartes.Main;

/**
 * Représente un joueur réel
 * Hérite la classe Observable
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
	 * Permer de récupérer le nom du joueur
	 * @return la chaine de caractères
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Permer de récupérer la position du joueur
	 * @return le numéro
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Permer de récupérer le nombre de points du joueur
	 * @return le nombre de points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Permer de récupérer la main du joueur
	 * @return la main du joueur
	 */
	public Main getMain() {
		return main;
	}
	
	/**
	 * Permer de savoir si le joueur peut jouer ou non
	 * @return true si le joueur peut jouer, false sinon
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
	 * modifie le fait que le joueur doit rejouer ou non
	 * @param	doitRejouer
	 */
	public void setDoitRejouer(boolean doitRejouer) {
		this.doitRejouer = doitRejouer;
	}
	
	/**
	 * Permer de savoir si le joueur a annoncé carte ou non
	 * @return true si le jouer a bien annoncé carte
	 */
	public boolean aAnnonceCarte() {
		return aAnnonceCarte;
	}

	/**
	 * Permet d'annoncer Carte
	 * @param aAnnonceCarte
	 * @return true si le joueur peut annoncer carte, false sinon
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
	 * permet au joueur de piocher une carte
	 */
	public void piocher() {
		aAnnonceCarte = false;
		this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
		this.setChanged();		
		this.notifyObservers("piocher");		// on notifie les observers que le joueur a pioché
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurSuivant());
	}
	
	/**
	 * permet au joueur de piocher un nombre donné de carte sans notifier les observer
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
		if(Partie.getInstance().getTalon().add(carte)) {		// si on peut bien ajouter la carte au talon
			this.main.remove(carte);							// on retire la carte de la main du joueur
			carte.effectuerAction();							// puis on effectue son action
			if(main.size() > 1) aAnnonceCarte = false;
			if(main.size() == 0) Partie.getInstance().mettreAJourScores();		// si c'était la dernière carte du joueur, la partie se termine
			else {
				this.notifyObservers("jouerCarte");		// on notifie les observers que le joueur a joué une carte
				if(this.doitRejouer) {					// si le joueur doit rejouer, on le replace comme joueur en cours
					Partie.getInstance().setJoueurEnCours(Partie.getInstance().getJoueurEnCours());
					this.doitRejouer = false;
				}
				else Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurSuivant());		// sinon, on indique que c'est au joueur suivant de jouer
			}
		} else {
			this.notifyObservers("jouerCarteErreur"); 	// on notifie les observers que le joueur ne peut pas jouer cette carte
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
			System.out.println(this.getNom() + " a contré " + joueur.getNom());
		}
	}

}
