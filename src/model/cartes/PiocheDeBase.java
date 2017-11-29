package model.cartes;

import model.actions.ActionNeutre;
import model.actions.ChangerSens;
import model.actions.Piocher4;
import model.actions.Rejouer;

public class PiocheDeBase extends Pioche {
	
	public PiocheDeBase() {
		super();
				
		for (int i = 1; i < 14; i++)
			for (int k = 0; k < 4; k++) {
				if(i == Carte.AS) this.add(new Carte(i, k, i, new Piocher4()));
				else if(i == Carte.VALET) this.add(new Carte(i, k, i, new ChangerSens()));
				else if(i == Carte.DIX) this.add(new Carte(i, k, i, new Rejouer()));
				else this.add(new Carte(i, k, i, new ActionNeutre()));
			}
	}

}
