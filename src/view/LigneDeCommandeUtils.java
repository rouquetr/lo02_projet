package view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import model.cartes.Carte;
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
	
	public String listerCartes(LinkedList<Carte> cartes) {
		Iterator<Carte> iterator = cartes.iterator();
		int numeroCarte = 1;
		String message = "";
		while(iterator.hasNext()) message += (numeroCarte++ + ": " + iterator.next().afficherCarte() + "\n");
		return message;
	}
	
	public void afficherTalon() {
		System.out.println("La carte visible du talon est " + Partie.getInstance().getTalon().afficherTalon());
	}

}
