package model.joueurs;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.omg.CosNaming.NamingContextPackage.NotFound;

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
	
	public LinkedList<Joueur> getJoueursByScore() {
		LinkedList<Joueur> classement = joueurs;
		classement.sort(new Comparator<Joueur>() {
			@Override
			public int compare(Joueur o1, Joueur o2) {
				return Integer.compare(o1.getPoints(), o2.getPoints());
			}
		});
		return classement;
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
	
	public Joueur findJoueurSuivant() {
		Iterator<Joueur> iterator = joueurs.iterator();
		while(iterator.hasNext()) 
			if(iterator.next().equals(joueurEnCours)) {
				if (!iterator.hasNext()) return joueurs.get(0);
				return iterator.next();
			}
		return null;
	}
	
	public void incrementerNumeroTour() {
		numeroTour++;
	}
	
	public String afficherPartie() {
		return "La partie est composée de: " + joueurs.size() + " joueurs, la pioche comporte " + pioche.size() + " cartes";
	}
	
	public void mettreAJourScores() {
		Iterator<Joueur> iterator = joueurs.iterator();
		while (iterator.hasNext()) 	iterator.next().compterPoints();
	}
	
}
