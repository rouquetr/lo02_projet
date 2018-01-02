package main;

import controller.PartieController;
import view.VueLigneDeCommande;

public class Main {

	public static void main(String[] args) {

		PartieController controller = new PartieController();
		
		VueLigneDeCommande vueLigneDeCommande = new VueLigneDeCommande(controller);
	}

}
