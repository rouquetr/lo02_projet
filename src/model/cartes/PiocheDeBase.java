package model.cartes;

import model.actions.ActionNeutre;

public class PiocheDeBase extends Pioche {
	
	public PiocheDeBase() {
		super();
				
		for (int i = 0; i < 13; i++)
			for (int k = 0; k < 4; k++) 
				this.add(new Carte(i, k, i, new ActionNeutre()));
	}

}
