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
			int partieTerminee = controller.verifierSiPartieTerminee();
			if (partieTerminee == 1) afficherFinDePartie();
			else if (partieTerminee == 0) effectuerTourDeJeu();
		}
	}
	
	public void afficherFinDePartie() {
		afficherScores();
		String message = "Voulez-vous: \n" 
						 + "1: relancer une partie?\n"
						 + "2: relancer une partie en changeant les paramètres (votre nom ainsi que le nombre de joueurs?\n"
						 + "3: Arrêter de jouer?";
		switch (demanderInt(message, 1, 3)) {
		case 1:
			lancerPartie();
			break;
		case 2:
			initialiserPartie();
			break;
		case 3:
			System.exit(0);
			break;
		}
	}
	
	public void afficherScores() {
		System.out.println("Les scores sont: ");
		Iterator<Joueur> iterator = partie.getJoueursByScore().iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			System.out.println(joueur.getNom() + ": " + joueur.getPoints() + " points");
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