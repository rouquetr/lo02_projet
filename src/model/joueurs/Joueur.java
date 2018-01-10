package model.joueurs;

import java.util.Iterator;
import java.util.Observable;

import model.cartes.Carte;
import model.cartes.Main;

public class Joueur extends Observable {
	
	private String nom;
	private int position;
	private int points;
	
	private boolean peutJouer = true;
	private boolean doitRejouer = false;
	private boolean aAnnonceCarte = false;
	
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
	public int getPosition() {
		return position;
	}
	public int getPoints() {
		return points;
	}

	public Main getMain() {
		return main;
	}
	public boolean peutJouer() {
		return peutJouer;
	}
	
	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}
	
	public void setDoitRejouer(boolean doitRejouer) {
		this.doitRejouer = doitRejouer;
	}
	
	public boolean aAnnonceCarte() {
		return aAnnonceCarte;
	}

	public boolean setaAnnonceCarte(boolean aAnnonceCarte) {
		if(this.main.size() > 2) {
			System.out.println(this.getNom() + " ne peut pas annoncer Carte");
			return false; 
		}
		this.aAnnonceCarte = aAnnonceCarte;
		System.out.println(this.getNom() + " a annoncé Carte");	
		return true;
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", position=" + position + ", points=" + points + ", main=" + main + "]";
	}
	
	public void piocher() {
		aAnnonceCarte = false;
		this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
		this.setChanged();
		this.notifyObservers("piocher");
		Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurSuivant());
	}
	
	public void piocher(int nombreCartes) {
		aAnnonceCarte = false;
		for (int i = 0; i < nombreCartes; i++) this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
	}
	
	public void jouerCarte(Carte carte) {
		this.setChanged();
		if(Partie.getInstance().getTalon().add(carte)) {
			this.main.remove(carte);
			carte.effectuerAction();
			if(main.size() > 1) aAnnonceCarte = false;
			if(main.size() == 0) Partie.getInstance().mettreAJourScores();
			else {
				this.notifyObservers("jouerCarte");
				if(this.doitRejouer) {
					Partie.getInstance().setJoueurEnCours(Partie.getInstance().getJoueurEnCours());
					this.doitRejouer = false;
				}
				else Partie.getInstance().setJoueurEnCours(Partie.getInstance().findJoueurSuivant());
			}
		} else {
			this.notifyObservers("jouerCarteErreur");
		}
	}
	
	public void compterPoints() {
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) {
			points += iterator.next().getPoints();
		}
	}
	
	
	public void contrerJoueur(Joueur joueur) {
		if(joueur.getMain().size() == 1 && joueur.aAnnonceCarte() == false) {
			joueur.piocher(2);
			System.out.println(this.getNom() + " a contré " + joueur.getNom());
		}
	}

}
