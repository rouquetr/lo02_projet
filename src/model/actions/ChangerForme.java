package model.actions;

import model.cartes.Talon;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;
import view.cli.LigneDeCommandeUtils;

public class ChangerForme implements Action {

	public void actionCli() {
		if(Partie.getInstance().getJoueurEnCours().getClass() == Ordinateur.class) Talon.getInstance().setCouleur(1);
		else {
			LigneDeCommandeUtils utils = new LigneDeCommandeUtils();
			
			String message = "Choisissez une couleur\n" + "1: Coeur\n" + "2: Carreau\n" + "3: Pique\n" + "4: Trèfle" ;
			
			Talon.getInstance().setCouleur(utils.demanderInt(message, 1, 4) - 1);
		}
	}
	public void actionGui() {
	}
	
	public String message() {
		return "";
	}
	
}
