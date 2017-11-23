package model.joueurs;

import java.util.Iterator;

import model.cartes.Carte;
import model.cartes.Main;

public class Joueur {
	
	private String nom;
	private int position;
	private int points;
	
	private Main main;
	
	public Joueur(String nom, int position) {
		this.nom = nom;
		this.position = position;
		this.points = 0;
		this.main = new Main();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getPoints() {
		return points;
	}
	public void addPoints(int points) {
		this.points += points;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", position=" + position + ", points=" + points + ", main=" + main + "]";
	}
	
	public void piocher() {
		this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
	}
	
	public void jouerCarte(Carte carte) {
		this.main.remove(carte);
		Partie.getInstance().getTalon().add(carte);
		carte.effectuerAction();
	}

}
