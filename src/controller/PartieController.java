package controller;

import java.util.Iterator;
import java.util.NoSuchElementException;

import model.cartes.Carte;
import model.cartes.CarteNonCompatibleException;
import model.cartes.PiocheDeBase;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

public class PartieController {
	
	private Partie partie = Partie.getInstance();
	
	private String[] nomsOrdinateur = {"James Bond", "Roger Rabbit", "George Abitbol", "Dark Vador"};
	
	public Partie getPartie(){
		return this.partie ; 
	}
	
	public void initialiserPartie(int nombreDeJoueurs, String nomJoueur) {
		
		partie.ajouterJoueur(new Joueur(nomJoueur, 0));
		
		for (int i = 0; i < nombreDeJoueurs - 1; i++) 
			partie.ajouterJoueur(new Ordinateur(nomsOrdinateur[i], i + 1));
			
		partie.setPioche(new PiocheDeBase());
	}
	
	public void lancerPartie() {
		partie.getPioche().melanger();
		partie.getPioche().distribuerCarte(partie.getJoueurs());
		
		partie.getTalon().add(partie.getPioche().tirerUneCarte());
	}
	
	public int faireJouer(Joueur joueur, int numeroCarte) {
		if(numeroCarte == 0) {
			joueur.piocher();
			if(partie.getPioche().isEmpty()) partie.getTalon().transformerEnPioche();
			return 0;
		}
		else {
			Carte carteVoulue = null;
			Iterator<Carte> iterator = joueur.getMain().iterator();
			for (int i = 0; i < numeroCarte && iterator.hasNext(); i++) carteVoulue = iterator.next();
			try {
				joueur.jouerCarte(carteVoulue);
				return 1;
			} catch (CarteNonCompatibleException e) {
				return 2;
			}
		}
	}
	
}
