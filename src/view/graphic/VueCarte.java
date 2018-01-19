package view.graphic;

import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Permet d'afficher une carte
 * hérite de JPanel
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class VueCarte extends JPanel {
	
	/**
	 * Utilitaires graphiques
	 */
	private GraphicUtils utils = new GraphicUtils();
	
	/**
	 * Le Jlabel de la carte
	 */
	private JLabel carte;

	/**
	 * creation du panel
	 */
	public VueCarte(int valeur, int couleur) {
		setLayout(null);
		setSize(60, 90);
		
		ImageIcon imageCarte = utils.getResizedIcon(utils.getPath(valeur, couleur), 60, 90); // on créée l'icone de la carte
		carte = new JLabel(imageCarte);
		carte.setBounds(0, 0, 60, 90);
		add(carte);
	}

}
