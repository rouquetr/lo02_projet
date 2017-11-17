package model.cartes;

import model.actions.Action;

public class Carte {
	
	private int valeur;
	private int couleur;
	private int points;
	
	private Action action;
	
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
}
