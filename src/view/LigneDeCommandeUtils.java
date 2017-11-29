package view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import model.cartes.Carte;
import model.joueurs.Joueur;
import model.joueurs.Partie;

public class LigneDeCommandeUtils {
	
	private Scanner scanner = new Scanner(System.in);
	
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
	
	public String listerCartes(LinkedList<Carte> cartes, int numeroDepart) {
		Iterator<Carte> iterator = cartes.iterator();
		String message = "";
		while(iterator.hasNext()) message += (numeroDepart++ + ": " + iterator.next().afficherCarte() + "\n");
		return message;
	}
	
	public String listerJoueursOrdinateurs(int numeroDepart, String subMessage) {
		Iterator<Joueur> iteratorJoueurs = Partie.getInstance().getJoueurs().iterator();
		String message = "";
		while(iteratorJoueurs.hasNext()) {
			Joueur joueur = iteratorJoueurs.next();
			if (joueur != Partie.getInstance().getJoueurEnCours()) message += numeroDepart++ + ": " + subMessage + joueur.getNom() + "\n";
		}
		return message;
	}
	
	public void afficherTalon() {
		System.out.println("La carte visible du talon est " + Partie.getInstance().getTalon().afficherTalon());
	}

}
