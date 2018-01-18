package main;
import controller.PartieController;
import view.cli.VueLigneDeCommande;
import view.graphic.VuePartie;

/** 
 * La classe permettant de lancer le jeu
 * @author Rouquet Raphael - Mannan Ismail
 *   
 */
public class Main {

	/** 
	 * méthode principale qui lance une partie en vue graphique et en ligne de commande
	 */
	public static void main(String[] args) {

		PartieController controller = new PartieController();
		
		VuePartie vuePartie = new VuePartie(controller);
		VueLigneDeCommande vueLigneDeCommande = new VueLigneDeCommande(controller);
	}

}
