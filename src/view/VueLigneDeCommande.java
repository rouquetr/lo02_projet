package view;


import java.util.Iterator;
import java.util.Scanner;

import controller.PartieController;
import model.cartes.Carte;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;


public class VueLigneDeCommande {
	
	private Partie partie = Partie.getInstance();
	
	private Scanner scanner = new Scanner(System.in);
	
	private PartieController controller;
	
	public VueLigneDeCommande(PartieController controller) {
		this.controller = controller;
	}
	
	public void initialiserPartie() {
				
		System.out.println("Saisissez votre nom de joueur");
		String nomJoueur = scanner.nextLine();
		
		System.out.println("Combien de joueurs doit comporter la partie?");
		int nombreJoueurs = scanner.nextInt();
		
		controller.initialiserPartie(nombreJoueurs, nomJoueur);
	}
	
	public void lancerPartie() {
		controller.lancerPartie();
		effectuerTourDeJeu();
	}
	
	public void effectuerTourDeJeu() {
		Iterator<Joueur> iteratorJoueurs = partie.getJoueurs().iterator();
		while (iteratorJoueurs.hasNext()) {
			partie.setJoueurEnCours(iteratorJoueurs.next());

			int carteAJouer = partie.getJoueurEnCours().getClass() != Ordinateur.class ? faireJouerJoueur() : 5;
			System.out.println(carteAJouer);
			controller.faireJouer(partie.getJoueurEnCours(), carteAJouer);
		}
		this.partie.incrementerNumeroTour();
	}
	
	public int faireJouerJoueur() {
		Iterator<Carte> iteratorCartes = partie.getJoueurEnCours().getMain().iterator();
		int numeroCarte = 1;
		
		System.out.println("Indiquez le numéro de la carte que vous voulez jouer:");
		while (iteratorCartes.hasNext()) System.out.println(numeroCarte++ + ": " + iteratorCartes.next().toString());
		return scanner.nextInt();
	}
}