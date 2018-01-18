package model.cartes;

import model.actions.Action;

/**
 * La classe de la carte avec sa valeur, sa couleur et ses points 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class Carte {
	
    public final static int COEUR = 0;
    public final static int CARREAU = 1;
    public final static int PIQUE = 2;
    public final static int TREFLE = 3;

    public final static int JOKER = 0;
    public final static int AS = 1;
    public final static int DEUX = 2;
    public final static int TROIS = 3;
    public final static int QUATRE = 4;
    public final static int CINQ = 5;
    public final static int SIX = 6;
    public final static int SEPT = 7;
    public final static int HUIT = 8;
    public final static int NEUF = 9;
    public final static int DIX = 10;
    public final static int VALET = 11;
    public final static int DAME = 12;
    public final static int ROI = 13;

	private int valeur;
	private int couleur;
	private int points;
	
	private Action action;
	
	public final static String[] VALEURS = {"Joker", "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi" };
	public final static String[] COULEURS = {"Coeur", "Carreau", "Pique", "Trèfle"};
	
	/**
	 *  Constructeur de la carte
	 *  @param	valeur	Roi, Dame, Valet, AS, Chiffre
	 *  @param	couleur	Trèfle, Coeur, Carreau, Pique
	 *  @param	points	points de la carte
	 *  @param	action	effet de la carte sur le jeu
	 *  
	**/
	public Carte(int valeur, int couleur, int points, Action action) {
		this.valeur = valeur;
		this.couleur = couleur;
		this.points = points;
		this.action = action;
	}
	
	/** 
	 * retourne la valeur de la carte
	 **/
	public int getValeur() {
		return valeur;
	}
	
	/** 
	 * modification de la valeur de la carte
	 * @param	valeur
	 **/
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	/** 
	 * retourne la couleur de la carte 
	**/
	public int getCouleur() {
		return couleur;
	}
	
	/** 
	 * modification de la valeur de la carte
	 * @param	couleur
	 */
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	
	/** 
	 * retourne les points de la carte
	 * 
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 *  modification des points de la carte
	 *  @param	points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 *  appel des méthodes d'actions graphiques et en ligne de commande
	 */
	public void effectuerAction() {
		if (Thread.currentThread().getName() == "cli") action.actionCli();
		else action.actionGui();
	}
	
	/** 
	 * Comparaison de deux cartes pour décider si une cate pouvait être posée sur le talon
	 * @param	carte1	carte à comparer avec celle du talon
	 * @param	carte2	carte du talon
	 */
	public static boolean ComparerCarte(Carte carte1, Carte carte2) { 
		if(carte1.getCouleur() == carte2.getCouleur() || carte1.getValeur() == carte2.getValeur()) return true;
		else return false;
	}
	
	/** 
	 * Affichage de la valeur et la couleur de la carte
	 */
	public String afficherCarte() {
		return VALEURS[valeur] + " de " + COULEURS[couleur];
	}
	
	/** 
	 * Permet d'afficher la valeur et la couleur de la carte de manière formatée
	 *
	 */
	public String afficherCarteAvecDeterminant() {
		switch (this.valeur) {
		case DAME:
			return "la " +	VALEURS[valeur] + " de " + COULEURS[couleur];
		case AS:
			return "l'" +	VALEURS[valeur] + " de " + COULEURS[couleur];
		default:
			return "le " +	VALEURS[valeur] + " de " + COULEURS[couleur];
		}
	}
	
	/**
	 * récupère le message de l'action effectuée
	 */
	public String getActionMessage() {
		return action.message();
	}
	
	/**
	 * récupère l'action en cours
	 */
	public Action getAction() {
		return action;
	}
	
}
