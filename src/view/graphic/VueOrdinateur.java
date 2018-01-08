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

public class VueOrdinateur extends JPanel {
	
	Joueur ordinateur;
	PartieController controller;
	
	JLabel nom;
	JLabel nbCartes;
	JButton contrer;

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
		nbCartes.setBounds(16, 34, 107, 16);
		add(nbCartes);
		
		contrer = new JButton("Contrer");
		contrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contrer.setBounds(16, 61, 117, 29);
		add(contrer);
	}
	
	public void refresh() {
		nbCartes.setText(ordinateur.getMain().size() + " cartes");
	}

}
