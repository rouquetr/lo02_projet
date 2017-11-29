package model.cartes;

import model.actions.Action;

public class Carte {
	
    public final static int PIC = 0;
    public final static int COEUR = 1;
    public final static int CARREAU = 2;
    public final static int TREFLE = 3;

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
	
	public final static String[] VALEURS = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi" };
	public final static String[] COULEURS = {"Coeur", "Carreau", "Pique", "Trèfle"};
	
	public Carte(int valeur, int couleur, int points, Action action) {
		this.valeur = valeur;
		this.couleur = couleur;
		this.points = points;
		this.action = action;
	}
	
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public int getCouleur() {
		return couleur;
	}
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public void effectuerAction() {
		action.action();
	}
	
	@Override
	public String toString() {
		return "Carte [valeur=" + valeur + ", couleur=" + couleur + ", points=" + points + ", action=" + action + "]";
	}
	
	public static boolean ComparerCarte(Carte carte1, Carte carte2) { 
		if(carte1.getCouleur() == carte2.getCouleur() || carte1.getValeur() == carte2.getValeur()) return true;
		else return false;
	}
	
	public String afficherCarte() {
		return VALEURS[valeur - 1] + " de " + COULEURS[couleur];
	}
	
	public String afficherCarteAvecDeterminant() {
		switch (this.valeur) {
		case DAME:
			return "la " +	VALEURS[valeur - 1] + " de " + COULEURS[couleur];
		case AS:
			return "l'" +	VALEURS[valeur - 1] + " de " + COULEURS[couleur];
		default:
			return "le " +	VALEURS[valeur - 1] + " de " + COULEURS[couleur];
		}
	}
	
	public String getActionMessage() {
		return action.message();
	}
	
}
