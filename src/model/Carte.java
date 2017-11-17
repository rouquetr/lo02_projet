package model;

public class Carte {
	
	private int valeur;
	private int couleur;
	private int points;
	
	public Carte(int valeur, int couleur, int points) {
		super();
		this.valeur = valeur;
		this.couleur = couleur;
		this.points = points;
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
	
}
