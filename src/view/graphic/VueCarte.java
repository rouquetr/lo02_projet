package view.graphic;

import java.awt.Image;
import javax.swing.JPanel;

import model.cartes.Carte;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VueCarte extends JPanel {
	
	private GraphicUtils utils = new GraphicUtils();
	private JLabel carte;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public VueCarte(int valeur, int couleur) {
		setLayout(null);
		setSize(60, 90);
		
		ImageIcon imageCarte = utils.getResizedIcon(utils.getPath(valeur, couleur), 60, 90);
		carte = new JLabel(imageCarte);
		carte.setBounds(0, 0, 60, 90);
		add(carte);
	}
	
	public VueCarte(int valeur, int couleur, int width, int height) {
		setLayout(null);
		setSize(width, height);
		
		ImageIcon imageCarte = utils.getResizedIcon(utils.getPath(valeur, couleur), width, height);
		carte = new JLabel(imageCarte);
		carte.setBounds(0, 0, width, height);
		add(carte);
	}

}
