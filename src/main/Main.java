package main;

import controller.PartieController;
import view.VueTexte;

public class Main {

	public static void main(String[] args) {

		PartieController controller = new PartieController();
		
		VueTexte vueTexte = new VueTexte(controller);
	}

}
