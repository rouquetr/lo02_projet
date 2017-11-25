package view;


import java.util.Iterator;
import java.util.NoSuchElementException;
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
		System.out.println(partie.afficherPartie());
		effectuerTourDeJeu();
	}
	
	public void effectuerTourDeJeu() {
		Iterator<Joueur> iteratorJoueurs = partie.getJoueurs().iterator();
		while (iteratorJoueurs.hasNext()) {
			partie.setJoueurEnCours(iteratorJoueurs.next());
			
			int action = (partie.getJoueurEnCours().getClass() != Ordinateur.class) ? faireJouerJoueur() : 5;
			try {
				afficherActionEffectuee(controller.faireJouer(partie.getJoueurEnCours(), action));		
			} catch (NoSuchElementException e) {
				System.out.println("Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
			}
			verifierSiPartieTerminee();
		}
	}
	
	public void verifierSiPartieTerminee() {
		if(partie.getJoueurEnCours().getMain().size() == 0) System.out.println("Fin de la partie");
		else if (partie.getJoueurEnCours().getPosition() == (partie.getJoueurs().size() - 1)) {
			this.partie.incrementerNumeroTour();
			effectuerTourDeJeu();
		}
	}
	
	public int faireJouerJoueur() {
		Iterator<Carte> iteratorCartes = partie.getJoueurEnCours().getMain().iterator();
		int numeroCarte = 1;
		afficherTalon();
		System.out.println("Indiquez le numéro de la carte que vous voulez jouer:");
		System.out.println("0: Piocher");
		while (iteratorCartes.hasNext()) System.out.println(numeroCarte++ + ": " + iteratorCartes.next().afficherCarte());
		return scanner.nextInt();
	}
	
	public void afficherActionEffectuee(int action) {
		switch (action) {
		case 0:
			System.out.println("Vous avez pioché " + partie.getJoueurEnCours().getMain().getLast().afficherCarteAvecDeterminant());
			break;
		case 1:
			System.out.println(partie.getJoueurEnCours().getNom() + " a joué " + partie.getTalon().afficherTalon());
		case 2:
			System.out.println("Vous ne pouvez pas jouer cette carte");
		default:
			break;
		}
	}
	
	public void afficherTalon() {
		System.out.println("La carte visible du talon est " + partie.getTalon().afficherTalon());
	}
}