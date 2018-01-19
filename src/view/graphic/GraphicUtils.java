package view.graphic;

import java.awt.Image;
import java.text.Normalizer;

import javax.swing.ImageIcon;

import model.cartes.Carte;

/**
 * Représente les différents utilitaires nécessaires pour la vue graphique
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class GraphicUtils {
	
	/**
	 * permet de redimensionner une image
	 * @param path	le chemin vers l'image
	 * @param width	la largeur voulue
	 * @param height	la hauteur voulue
	 * @return	l'imageIcon redimensionnée
	 */
	public ImageIcon getResizedIcon(String path, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(VueCarte.class.getResource(path));
		Image image = imageIcon.getImage().getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	/**
	 * Permet de récupérer le chemin vers une image de carte grace à sa valeur et sa couleur
	 * @param valeur
	 * @param couleur
	 * @return le chemin vers l'image de la carte
	 */
	public String getPath(int valeur, int couleur) {
		String path = "";
		if(valeur == -1 || couleur == -1) path = "/cartes/carte_dos.png";
		else path = "/cartes/" + Carte.VALEURS[valeur] + "_" + Carte.COULEURS[couleur] + ".png";
		return normalizeString(path);
	}
	
	/**
	 * Permet de normaliser une chaîne de caractères, le nom des cartes comportant des accents, et pas le nom de leurs fichiers
	 * @param s la chaîne à normaliser
	 * @return la chaîne normalisée
	 */
	private String normalizeString(String s) {
		s = s.toLowerCase();
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}

}
