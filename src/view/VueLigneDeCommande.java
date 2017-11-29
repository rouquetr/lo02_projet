package view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
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
	
	private Joueur joueurEnCours;
	
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
		while(true) {
			joueurEnCours = partie.getJoueurEnCours();
			tourNormal();
		}
	}
	
	public void tourNormal() {
		if(joueurEnCours.peutJouer())
			try {
				afficherActionEffectuee(
						(joueurEnCours.getClass() != Ordinateur.class)
							? controller.faireJouer(joueurEnCours, faireJouerJoueur())
							: controller.faireJouer((Ordinateur) joueurEnCours)
						);		
			} catch (NoSuchElementException e) {
				System.out.println("Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
			}
		else {
			System.out.println(joueurEnCours.getNom() + " passe son tour.");
			joueurEnCours.setPeutJouer(true);
		}
		if (controller.verifierSiPartieTerminee()) afficherFinDePartie();
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
		afficherTalon();
		String message = "Indiquez le numéro de la carte que vous voulez jouer:\n" + "0: Piocher\n";
		message += listerCartes(joueurEnCours.getMain());
		
		return demanderInt(message, 0, joueurEnCours.getMain().size());
	}
	
	public void afficherActionEffectuee(int action) {
		switch (action) {
		case 0:
			System.out.println(joueurEnCours.getNom() + " a pioché " + joueurEnCours.getMain().getLast().afficherCarteAvecDeterminant());
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			break;
		case 1:
			System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
			if(partie.getTalon().getLast().getActionMessage() != "") System.out.println(partie.getTalon().getLast().getActionMessage());
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			break;
		case 2:
			System.out.println(joueurEnCours.getNom() + " ne peut pas jouer cette carte");
			break;
		default:
			break;
		}
	}
	
	public String listerCartes(LinkedList<Carte> cartes) {
		Iterator<Carte> iterator = cartes.iterator();
		int numeroCarte = 1;
		String message = "";
		while(iterator.hasNext()) message += (numeroCarte++ + ": " + iterator.next().afficherCarte() + "\n");
		return message;
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