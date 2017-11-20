package main;

import java.util.Map;

import controller.PartieController;
import view.VueLigneDeCommande;

public class Main {

	public static void main(String[] args) {

		PartieController controller = new PartieController();
		
		VueLigneDeCommande vueLigneDeCommande = new VueLigneDeCommande();
		
		Map<String, String> initialisation = vueLigneDeCommande.initialiserPartie();
		
		controller.initialiserPartie(Integer.parseInt(initialisation.get("nombreJoueurs")), initialisation.get("nomJoueur"));
		
	}

}
