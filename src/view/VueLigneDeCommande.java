package view;


import java.util.InputMismatchException;
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
		String nomJoueur = demanderString("Saisissez votre nom de joueur");
		int nombreJoueurs = demanderInt("Combien de joueurs doit comporter la partie?", Partie.MINJOUEUR, Partie.MAXJOUEUR);
		
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
			
			try {
				afficherActionEffectuee(
						(partie.getJoueurEnCours().getClass() != Ordinateur.class) 
							? controller.faireJouer(partie.getJoueurEnCours(), faireJouerJoueur())
							: controller.faireJouer((Ordinateur) partie.getJoueurEnCours())
						);		
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
		String message = "Indiquez le numéro de la carte que vous voulez jouer:\n" + "0: Piocher\n";
		while (iteratorCartes.hasNext()) message += (numeroCarte++ + ": " + iteratorCartes.next().afficherCarte() + "\n");
		
		return demanderInt(message, 0, partie.getJoueurEnCours().getMain().size());
	}
	
	public void afficherActionEffectuee(int action) {
		String nomJoueur = partie.getJoueurEnCours().getNom();
		switch (action) {
		case 0:
			System.out.println(nomJoueur + " a pioché " + partie.getJoueurEnCours().getMain().getLast().afficherCarteAvecDeterminant());
			break;
		case 1:
			System.out.println(nomJoueur + " a joué " + partie.getTalon().afficherTalon());
			break;
		case 2:
			System.out.println(nomJoueur + " ne peut pas jouer cette carte");
			break;
		default:
			break;
		}
	}
	
	public void afficherTalon() {
		System.out.println("La carte visible du talon est " + partie.getTalon().afficherTalon());
	}
	
	public String demanderString(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}
	
	public int demanderInt(String message, int min, int max) {
		int saisie = -1;
		while (saisie < min || saisie > max) {
			System.out.println(message);
			try {
				saisie = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Veuillez entrer une valeur valide");
				scanner.nextLine();
			}
		}
		return saisie;
	}
}