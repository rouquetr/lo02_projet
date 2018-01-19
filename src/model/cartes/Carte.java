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
	 *  @param	points	points que la carte donnera en fin de partie
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
	 * @return la valeur de la carte
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
	 * @return la couleur de la carte 
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
	 * @return les points de la carte
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
		if (Thread.currentThread().getName() == "cli") action.actionCli();		// si le thread depuis lequel est joué la carte est actionCli, alors l'effet est en CLI
		else action.actionGui();													// sinon, en vue graphique
	}
	
	/** 
	 * Méthode statique
	 * Comparaison de deux cartes pour décider si une cate peut être posée sur le talon
	 * @param	carte1	carte à comparer avec celle du talon
	 * @param	carte2	carte du talon
	 */
	public static boolean ComparerCarte(Carte carte1, Carte carte2) { 
		if(carte1.getCouleur() == carte2.getCouleur() || carte1.getValeur() == carte2.getValeur()) return true;
		else return false;
	}
	
	/** 
	 * Permet de récupérer la valeur et la couleur de la carte dans une chaîne de caractères
	 * @return la chaîne de caractères
	 */
	public String afficherCarte() {
		return VALEURS[valeur] + " de " + COULEURS[couleur];
	}
	
	/** 
	 * Permet de récupérer la valeur et la couleur de la carte de manière formatée
	 * @return la chaine de caractères
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
	 * Permet de récupérer le message de l'action effectuée
	 * @return la chaîne de caractères
	 */
	public String getActionMessage() {
		return action.message();
	}
	
	/**
	 * Permet de récupérer l'action liée à la carte
	 * @return une action
	 */
	public Action getAction() {
		return action;
	}
	
}
