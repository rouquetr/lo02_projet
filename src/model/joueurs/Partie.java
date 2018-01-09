package model.joueurs;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Set;

import model.cartes.Pioche;
import model.cartes.PiocheDeBase;
import model.cartes.PiocheMonclar;
import model.cartes.Talon;

public class Partie extends Observable {
	
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
	
	public void commencerNouvellePartie(Pioche variante) {
		this.pioche = variante;
		this.pioche.melanger();
		this.pioche.distribuerCarte(this.joueurs);
		
		this.talon.clear();
		this.talon.add(this.pioche.tirerUneCarte());
		
		this.joueurEnCours = this.joueurs.getFirst();
		this.setChanged();
		this.notifyObservers("commencerNouvellePartie");
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
		LinkedList<Joueur> classement = new LinkedList(joueurs);
		classement.sort(new Comparator<Joueur>() {
			@Override
			public int compare(Joueur o1, Joueur o2) {
				return Integer.compare(o1.getPoints(), o2.getPoints());
			}
		});
		return classement;
	}
	
	public void ajouterJoueurs(Set<Joueur> joueurs) {
		this.joueurs.addAll(joueurs);
		this.setChanged();
		this.notifyObservers("ajouterJoueurs");
	}
	
	public void retirerTousLesJoueurs() {
		this.joueurs.removeAll(this.joueurs);
	}
	
	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}
	
	public void setJoueurEnCours(Joueur joueurEnCours) {
		this.joueurEnCours = joueurEnCours;
		this.setChanged();
		this.notifyObservers("setJoueurEnCours");	
	}
	
	public Joueur findJoueurPrecedent() {
		Iterator<Joueur> iterator = joueurs.descendingIterator();
		while(iterator.hasNext()) 
			if(iterator.next().equals(joueurEnCours)) {
				if (!iterator.hasNext()) return joueurs.get(joueurs.size() - 1);
				return iterator.next();
			}
		return null;
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
		String message = "La partie (variante: " + pioche.getNom() +") est composée de:\n";
		Iterator<Joueur> iterator = joueurs.iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			message += joueur.getNom() + " avec " + joueur.getMain().size() + " cartes\n";
		}
		message += "La carte visible du talon est " + talon.afficherTalon();
		return message;
	}
	
	public void mettreAJourScores() {
		Iterator<Joueur> iterator = joueurs.iterator();
		while (iterator.hasNext()) 	{
			Joueur joueur = iterator.next();
			joueur.compterPoints();
		}
		this.setChanged();
		this.notifyObservers("mettreAJourScores");	
	}
	
}
