package model.actions;

import java.util.Iterator;

import model.cartes.Carte;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;
import view.LigneDeCommandeUtils;

public class DonnerCarte implements Action {
	
	public void action() {
		Partie partie = Partie.getInstance();
		if(partie.getJoueurEnCours().getClass() == Ordinateur.class) {
			partie.getJoueurs().get(0).getMain().add(partie.getJoueurEnCours().getMain().get(0));
		} else {
			LigneDeCommandeUtils utils = new LigneDeCommandeUtils();
			
			String message = "A qui allez vous donner une carte?\n";
			Iterator<Joueur> iteratorJoueurs = partie.getJoueurs().iterator();
			int i = 1;
			while(iteratorJoueurs.hasNext()) {
				Joueur joueur = iteratorJoueurs.next();
				if (joueur != partie.getJoueurEnCours()) message += i + ": " + joueur.getNom() + "\n";
				i++;
			}
			Joueur joueurChoisi = partie.getJoueurs().get(utils.demanderInt(message, 1, i) - 1);
			
			message = "Choisissez la carte à donner à " + joueurChoisi.getNom() + ": \n" + utils.listerCartes(partie.getJoueurEnCours().getMain());
			Carte carteChoisie = partie.getJoueurEnCours().getMain().get(utils.demanderInt(message, 1, i) - 1);
			
			joueurChoisi.getMain().add(carteChoisie);
			partie.getJoueurEnCours().getMain().remove(carteChoisie);
		}
	}
	
	public String message() {
		return "";
	}

}
