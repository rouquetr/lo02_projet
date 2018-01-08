package view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String resultat = null;
		try {
			resultat = br.readLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return resultat;
	}
	
	public int demanderInt(String message, int min, int max) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int saisie = -1;
		while (saisie < min || saisie > max) {
			System.out.println(message);
			try {
				saisie = Integer.parseInt(br.readLine());
				if (saisie < min || saisie > max) System.out.println("Veuillez entrer une valeur valide");
			} catch (NumberFormatException e) {
				System.out.println("Veuillez entrer une valeur valide");
				scanner.nextLine();
			} catch (IOException e) {
				System.err.println(e.getMessage());
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
	
	public String listerVariantes() {
		String message = "1: Jeu de base\n";
		message += "2: Variante de Monclar";
		
		return message;
	}
	
	public void afficherTalon() {
		System.out.println("La carte visible du talon est " + Partie.getInstance().getTalon().afficherTalon());
	}
	
	private String lireChaine() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String resultat = null;
		try {
			System.out.println(">");
			resultat = br.readLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return resultat;
	}

}