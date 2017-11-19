package model.joueurs;

import java.util.TreeSet;

import model.cartes.Pioche;
import model.cartes.Talon;

public class Partie {
	
	private boolean sens = true;
	private int numeroTour = 0;
	
	private Talon talon = Talon.getInstance();
	private Pioche pioche;
	
	private TreeSet<Joueur> joueurs;
	
	public Partie(Pioche pioche, TreeSet<Joueur> joueurs) {
		this.pioche = pioche;
		this.joueurs = joueurs;
	}
	
	public boolean getSens() {
		return sens;
	}
	public void setSens(boolean sens) {
		this.sens = sens;
	}
	public int getNumeroTour() {
		return numeroTour;
	}
	public void setNumeroTour(int numeroTour) {
		this.numeroTour = numeroTour;
	}
	
	public Talon getTalon() {
		return talon;
	}
	public void setTalon(Talon talon) {
		this.talon = talon;
	}
	public Pioche getPioche() {
		return pioche;
	}
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}
	public TreeSet<Joueur> getJoueurs() {
		return joueurs;
	}
	public void setJoueurs(TreeSet<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
}
