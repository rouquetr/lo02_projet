package model.joueurs;

import java.util.Iterator;

import model.cartes.Carte;
import model.cartes.CarteNonCompatibleException;
import model.cartes.Talon;

public class Ordinateur extends Joueur {

	public Ordinateur(String nom, int position) {
		super(nom, position);
	}

	public int jouerCarte() throws CarteNonCompatibleException {
		Carte carteAJouer = null;
		Iterator<Carte> it = this.getMain().iterator();
		while(it.hasNext()) {
			Carte carteATester = it.next();
			if(Carte.ComparerCarte(carteATester, Talon.getInstance().getLast()))
				carteAJouer = carteATester;
		}
				
		if(carteAJouer == null) {
			piocher();
			return 0;
		}
		else {
			super.jouerCarte(carteAJouer);
			return 1;
		}
	}
}
