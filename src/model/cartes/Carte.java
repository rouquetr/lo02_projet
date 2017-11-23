package model.cartes;

import model.actions.Action;

public class Carte {

	private int valeur;
	private int couleur;
	private int points;
	
	private Action action;
	
	private String[] valeurs = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi" };
	private String[] couleurs = {"Coeur", "Carreau", "Pique", "Trèfle"};
	
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
	
	public String afficherCarte() {
		return valeurs[valeur] + " de " + couleurs[couleur];
	}
	
}
