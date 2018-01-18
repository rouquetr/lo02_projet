package view.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

import model.cartes.Carte;
import model.joueurs.Joueur;
import model.joueurs.Partie;

/**
 * Représente les commandes dans la console pour communiquer avec le joueur
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class LigneDeCommandeUtils {
	
	/**
	 * Affichage d'un message pour demander une chaine de caractère à l'utilisateur (nom du joueur, ..)
	 */
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
	
	/**
	 * Affichage d'un message pour demander un entier à l'utilisateur (nombre de joueurs, carte à jouer)
	 */
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
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return saisie;
	}
	
	/**
	 * permet de lister les cartes
	 * @param	cartes			liste de cartes 
	 * @param	numeroDepart	affiche le numero de la carte dans la liste
	 * @return message
	 */
	public String listerCartes(LinkedList<Carte> cartes, int numeroDepart) {
		Iterator<Carte> iterator = cartes.iterator();
		String message = "";
		while(iterator.hasNext()) message += (numeroDepart++ + ": " + iterator.next().afficherCarte() + "\n");
		return message;
	}
	
	/**
	 * permet de lister les joueurs artificiels
	 * @param	numeroDepart	numéro de l'ordinateur dans la liste
	 * @param	subMessage		
	 * @return message
	 */
	public String listerJoueursOrdinateurs(int numeroDepart, String subMessage) {
		Iterator<Joueur> iteratorJoueurs = Partie.getInstance().getJoueurs().iterator();
		String message = "";
		while(iteratorJoueurs.hasNext()) {
			Joueur joueur = iteratorJoueurs.next();
			if (joueur != Partie.getInstance().getJoueurEnCours()) message += numeroDepart++ + ": " + subMessage + joueur.getNom() + "\n";
		}
		return message;
	}
	
	/**
	 * permet de lister les variantes du jeu
	 * @return message
	 */
	public String listerVariantes() {
		String message = "1: Jeu de base\n";
		message += "2: Variante de Monclar";
		
		return message;
	}

	/**
	 * affiche la carte visible du talon
	 */
	public void afficherTalon() {
		System.out.println("La carte visible du talon est " + Partie.getInstance().getTalon().afficherTalon());
	}

}
