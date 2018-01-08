package view.graphic;

import java.awt.Image;
import java.text.Normalizer;

import javax.swing.ImageIcon;

import model.cartes.Carte;

public class GraphicUtils {
	
	public ImageIcon getResizedIcon(String path, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(VueCarte.class.getResource(path));
		Image image = imageIcon.getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	public String getPath(int valeur, int couleur) {
		String path = "";
		if(valeur == -1 || couleur == -1) path = "/cartes/carte_dos.png";
		else path = "/cartes/" + Carte.VALEURS[valeur] + "_" + Carte.COULEURS[couleur] + ".png";
		return normalizeString(path);
	}
	
	private String normalizeString(String s) {
		s = s.toLowerCase();
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}

}
