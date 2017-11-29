package model.cartes;

import model.actions.ActionNeutre;
import model.actions.ChangerSens;
import model.actions.DonnerCarte;
import model.actions.ChangerForme;
import model.actions.Piocher4;
import model.actions.Rejouer;

public class PiocheDeBase extends Pioche {
	
	public PiocheDeBase() {
		super();
				
		for (int i = 1; i < 14; i++)
			for (int k = 0; k < 4; k++) {
				if(i == Carte.AS) this.add(new Carte(i, k, 50, new Piocher4()));
				else if(i == Carte.ROI) this.add(new Carte(i, k, 10, new ActionNeutre()));
				else if(i == Carte.DAME) this.add(new Carte(i, k, 10, new ActionNeutre()));
				else if(i == Carte.VALET) this.add(new Carte(i, k, 20, new ChangerSens()));
				else if(i == Carte.DIX) this.add(new Carte(i, k, 20, new Rejouer()));
				else if(i == Carte.HUIT) this.add(new Carte(i, k, 50, new ChangerForme()));
				else if(i == Carte.CINQ) this.add(new Carte(i, k, i, new DonnerCarte()));
				else this.add(new Carte(i, k, i, new ActionNeutre()));
			}
	}

}
