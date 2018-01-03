package main;
import controller.PartieController;
import view.cli.VueLigneDeCommande;
import view.graphic.VueInitialisation;

public class Main {

	public static void main(String[] args) {

		PartieController controller = new PartieController();
		
		VueInitialisation window = new VueInitialisation(controller);
		VueLigneDeCommande vueLigneDeCommande = new VueLigneDeCommande(controller);
	}

}
