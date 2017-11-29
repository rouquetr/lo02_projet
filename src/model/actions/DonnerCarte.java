package model.actions;

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
			message += utils.listerJoueursOrdinateurs(1, "");
			
			Joueur joueurChoisi = partie.getJoueurs().get(utils.demanderInt(message, 1, partie.getJoueurs().size()));
			
			message = "Choisissez la carte à donner à " + joueurChoisi.getNom() + ": \n" + utils.listerCartes(partie.getJoueurEnCours().getMain(), 1);
			Carte carteChoisie = partie.getJoueurEnCours().getMain().get(utils.demanderInt(message, 1, partie.getJoueurEnCours().getMain().size()) - 1);
			
			joueurChoisi.getMain().add(carteChoisie);
			partie.getJoueurEnCours().getMain().remove(carteChoisie);
			System.out.println(partie.getJoueurEnCours().getNom() + " a donné une carte à" + joueurChoisi.getNom());
		}
	}
	
	public String message() {
		return "";
	}

}
