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
 * Représente les différents utilitaires nécessaires pour la vue de la ligne de commande
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class LigneDeCommandeUtils {
	
	/**
	 * Affichage d'un message pour demander une chaine de caractère à l'utilisateur (nom du joueur, ..) et demande de saisie
	 * @param Le message à afficher
	 * @return la saisie de l'utilisateur
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
	 * Affichage d'un message pour demander un entier à l'utilisateur (nombre de joueurs, carte à jouer) et demande de saisie de l'entier entre deux bornes
	 * @param min la valeur minimale de l'entier
	 * @param max la valeur maximale de l'entier
	 * @return l'entier saisi
	 */
	public int demanderInt(String message, int min, int max) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int saisie = -1;		// on initialise la saisie à -1 qui est une valeur qui ne sera jamais valide
		while (saisie < min || saisie > max) {		// tant que la saisie n'est pas dans les bornes, on redemande une valeur valide
			System.out.println(message);
			try {
				saisie = Integer.parseInt(br.readLine());		// on parse la saisie pour vérifier que c'est bien un entier
				if (saisie < min || saisie > max) System.out.println("Veuillez entrer une valeur valide");
			} catch (NumberFormatException e) {		// si la saisie n'est pas un entier, on en redemande une
				System.out.println("Veuillez entrer une valeur valide");
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		return saisie;
	}
	
	/**
	 * permet de lister les cartes avec leur numéro
	 * @param	cartes			liste de cartes 
	 * @param	numeroDepart		numéro de départ de la liste
	 * @return la liste des cartes avec le numéro qui leur est lié
	 */
	public String listerCartes(LinkedList<Carte> cartes, int numeroDepart) {
		Iterator<Carte> iterator = cartes.iterator();
		String message = "";
		while(iterator.hasNext()) message += (numeroDepart++ + ": " + iterator.next().afficherCarte() + "\n");
		return message;
	}
	
	/**
	 * permet de lister les ordinateurs
	 * @param	numeroDepart		numéro de l'ordinateur dans la liste des joueurs
	 * @param	subMessage		message annexe
	 * @return 	message			la liste des ordinateurs
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
	 * @return message	la liste des variantes
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
