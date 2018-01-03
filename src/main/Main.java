package main;
import controller.PartieController;
import view.cli.VueLigneDeCommande;
import view.graphic.VuePartie;

public class Main {

	public static void main(String[] args) {

		PartieController controller = new PartieController();
		
		VuePartie vuePartie = new VuePartie(controller);
		VueLigneDeCommande vueLigneDeCommande = new VueLigneDeCommande(controller);
	}

}
