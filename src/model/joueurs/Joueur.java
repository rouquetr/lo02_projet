package model.joueurs;

import java.util.Iterator;

import model.cartes.Carte;
import model.cartes.CarteNonCompatibleException;
import model.cartes.Main;

public class Joueur {
	
	private String nom;
	private int position;
	private int points;
	
	private boolean peutJouer = true;
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
	
	public boolean aAnnonceCarte() {
		return aAnnonceCarte;
	}

	public void setaAnnonceCarte(boolean aAnnonceCarte) {
		this.aAnnonceCarte = aAnnonceCarte;
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", position=" + position + ", points=" + points + ", main=" + main + "]";
	}
	
	public void piocher() {
		aAnnonceCarte = false;
		this.main.add(Partie.getInstance().getPioche().tirerUneCarte());
	}
	
	public void piocher(int nombreCartes) {
		for (int i = 0; i < nombreCartes; i++) piocher();
	}
	
	public void jouerCarte(Carte carte) throws CarteNonCompatibleException {
		if(Partie.getInstance().getTalon().add(carte)) {
			this.main.remove(carte);
			carte.effectuerAction();
			if(main.size() > 1) aAnnonceCarte = false;
		} else throw new CarteNonCompatibleException("La carte jouée n'est pas compatible avec le talon.");
	}
	
	public void compterPoints() {
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) {
			points += iterator.next().getPoints();
		}
	}
	
	
	public void contrerJoueur(Joueur joueur) {
		if(joueur.getMain().size() == 1 && joueur.aAnnonceCarte() == false) joueur.piocher(2);
	}

}
