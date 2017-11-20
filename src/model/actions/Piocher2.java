package model.actions;

import model.cartes.Carte;
import model.cartes.Pioche;

public class Piocher2 extends Action {

	private static Pioche pioche;
	
	public static void action() {
		System.out.println("Piocher deux cartes");
		for (int i=0;i<=2;i++) {
			pioche.piocher();
		}
	}
	
}
