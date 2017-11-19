package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.PartieController;

public class VueLigneDeCommande {
	
	private PartieController controller;
	private Scanner scanner = new Scanner(System.in);

	public VueLigneDeCommande(PartieController controller) {
		this.controller = controller;
	}
	
	public Map<String, String> initialiserPartie() {
		
		Map<String, String> resultat = new HashMap<String, String>();
		
		System.out.println("Saisissez votre nom de joueur");
		resultat.put("nomJoueur", scanner.nextLine());
		
		System.out.println("Combien de joueurs doit comporter la partie?");
		resultat.put("nombreJoueurs", scanner.nextLine());
		
		return resultat;		
	}
}
