package model.joueurs;

import java.util.Collections;
import java.util.LinkedList;

import model.cartes.Pioche;
import model.cartes.Talon;

public class Partie {
	
	private static Partie uniqueInstance;

	private int numeroTour = 0;
	
	private Talon talon = Talon.getInstance();
	private Pioche pioche;
	
	private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	private Joueur joueurEnCours = null;
	
    public final static int MINJOUEUR = 1;
    public final static int MAXJOUEUR = 5;
	
	private Partie() {	}
	
	public static Partie getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Partie();
		return uniqueInstance;
	}
	
	public void changerSens() {
		Collections.reverse(joueurs);
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
	
	public LinkedList<Joueur> getJoueurs() {
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
	
	public String afficherPartie() {
		return "La partie est composée de: " + joueurs.size() + " joueurs, la pioche comporte " + pioche.size() + " cartes";
	}
	
}
