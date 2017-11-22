package model.joueurs;

import java.util.LinkedHashSet;

import model.cartes.Pioche;
import model.cartes.Talon;

public class Partie {
	
	private static Partie uniqueInstance;
	
	private boolean sens = true;
	private int numeroTour = 0;
	
	private Talon talon = Talon.getInstance();
	private Pioche pioche;
	
	private LinkedHashSet<Joueur> joueurs = new LinkedHashSet<Joueur>();
	private Joueur joueurEnCours = null;
	
	private Partie() {	}
	
	public static Partie getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Partie();
		return uniqueInstance;
	}
	
	public boolean getSens() {
		return sens;
	}
	
	public void changerSens() {
		sens = !sens;
	}
	
	public int getNumeroTour() {
		return numeroTour;
	}
	
	public Talon getTalon() {
		return talon;
	}
	
	public Pioche getPioche() {
		return pioche;
	}
	
	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}
	
	public LinkedHashSet<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public void ajouterJoueur(Joueur joueur) {
		joueurs.add(joueur);
	}
	
	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}
	
	public void setJoueurEnCours(Joueur joueurEnCours) {
		this.joueurEnCours = joueurEnCours;
	}
	
	public void incrementerNumeroTour() {
		numeroTour++;
	}
	
	@Override
	public String toString() {
		return "Partie [sens=" + sens + ", numeroTour=" + numeroTour + ", talon=" + talon + ", pioche=" + pioche
				+ ", joueurs=" + joueurs + "]";
	}
	
}
