package model.cartes;

import model.actions.ActionNeutre;
import model.actions.ChangerSens;
import model.actions.PasserTour;
import model.actions.ChangerForme;
import model.actions.Piocher3;
import model.actions.Rejouer;

/**
 * Représente la variante Pioche Monclar
 * Hérite de la classe Paquet
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class PiocheMonclar extends Pioche {
	
	/** 
	 * Constructeur de la pioche Monclar
	 */
	public PiocheMonclar() {
		super("Jeu de Monclar");
				
		for (int i = 1; i < 14; i++)
			for (int k = 0; k < 4; k++) {
				if(i == Carte.AS) this.add(new Carte(i, k, 50, new Piocher3()));
				else if(i == Carte.ROI) this.add(new Carte(i, k, 10, new ActionNeutre()));
				else if(i == Carte.DAME) this.add(new Carte(i, k, 10, new ActionNeutre()));
				else if(i == Carte.VALET) this.add(new Carte(i, k, 20, new ChangerSens()));
				else if(i == Carte.DIX) this.add(new Carte(i, k, 20, new Rejouer()));
				else if(i == Carte.NEUF) this.add(new Carte(i, k, i, new ActionNeutre()));
				else if(i == Carte.HUIT) this.add(new Carte(i, k, 50, new ChangerForme()));
				else if(i == Carte.SEPT) this.add(new Carte(i, k, i, new PasserTour()));
				else if(i == Carte.SIX) this.add(new Carte(i, k, i, new ActionNeutre()));
				else if(i == Carte.CINQ) this.add(new Carte(i, k, i, new ActionNeutre()));
				else if(i == Carte.QUATRE) this.add(new Carte(i, k, i, new ActionNeutre()));
				else if(i == Carte.TROIS) this.add(new Carte(i, k, i, new ActionNeutre()));
				else if(i == Carte.DEUX) this.add(new Carte(i, k, i, new ActionNeutre()));
				else this.add(new Carte(i, k, i, new ActionNeutre()));
			}
	}

}
