package view.graphic;

import javax.swing.JPanel;

import controller.PartieController;
import model.joueurs.Joueur;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VueOrdinateur extends JPanel {
	
	private GraphicUtils utils = new GraphicUtils();
	
	private Joueur ordinateur;
	private PartieController controller;
	
	private JLabel nom;
	private JLabel nbCartes;
	private JButton contrer;
	private JLabel main;

	/**
	 * Create the panel.
	 */
	public VueOrdinateur(PartieController controller, Joueur ordinateur) {
		setBackground(new Color(0, 102, 0));
		this.controller = controller;
		this.ordinateur = ordinateur;
		initialize();
		refresh();
	}
	
	public void initialize() {
		setLayout(null);
		
		nom = new JLabel(ordinateur.getNom());
		nom.setForeground(new Color(255, 255, 255));
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setBounds(16, 6, 107, 16);
		add(nom);
		
		nbCartes = new JLabel("0 cartes");
		nbCartes.setForeground(new Color(255, 255, 255));
		nbCartes.setHorizontalAlignment(SwingConstants.CENTER);
		nbCartes.setBounds(16, 101, 107, 16);
		add(nbCartes);
		
		contrer = new JButton("Contrer");
		contrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contrer.setBounds(6, 122, 138, 29);
		add(contrer);
		
		main = new JLabel(new ImageIcon(VueOrdinateur.class.getResource("/icones/main.png")));
		main.setBounds(16, 27, 117, 73);
		add(main);
	}
	
	public void refresh() {
		nbCartes.setText(ordinateur.getMain().size() + " cartes");
	}

}
