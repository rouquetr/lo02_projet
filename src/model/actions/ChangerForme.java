package model.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.cartes.Carte;
import model.cartes.Talon;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;
import view.cli.LigneDeCommandeUtils;
import view.graphic.GraphicUtils;
import view.graphic.VuePartie;

/**
 * Représente une classe permet à une carte de changer de couleur
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class ChangerForme implements Action {

	public void actionCli() {
		
		if (Partie.getInstance().getJoueurEnCours().getClass() == Ordinateur.class) Talon.getInstance().setCouleur(1); //choix automatique si le joueur est un ordinateur
		else {
			LigneDeCommandeUtils utils = new LigneDeCommandeUtils();

			String message = "Choisissez une couleur\n" + "1: Coeur\n" + "2: Carreau\n" + "3: Pique\n" + "4: Trèfle"; // Choix de la carte à donner 

			Talon.getInstance().setCouleur(utils.demanderInt(message, 1, 4) - 1);
		}
	}

	/**
	 * Vue Graphique de l'action
	 */
	public void actionGui() {
		GraphicUtils utils = new GraphicUtils();
		if (Partie.getInstance().getJoueurEnCours().getClass() == Ordinateur.class) Talon.getInstance().setCouleur(1);
		else {
			JDialog dialog = new JDialog(VuePartie.frame, true); 	
			dialog.setLocation(200, 200);
			dialog.setSize(450, 300);
			dialog.setResizable(false);

			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setSize(450, 300);
			panel.setBackground(new Color(0, 102, 0));
			dialog.add(panel);

			JLabel instructions = new JLabel("Choisissez une couleur");
			instructions.setBounds(20, 20, 450, 16);
			instructions.setForeground(new Color(255, 255, 255));
			panel.add(instructions);

			JButton coeur = new JButton("Coeur");
			coeur.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Talon.getInstance().setCouleur(Carte.COEUR);
					System.out.println(Talon.getInstance().getCouleur());
					dialog.dispose();
				}
			});
			coeur.setBounds(20, 100, 170, 29);
			panel.add(coeur);
			
			JButton pique = new JButton("Pique");
			pique.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Talon.getInstance().setCouleur(Carte.PIQUE);
					dialog.dispose();
				}
			});
			pique.setBounds(250, 100, 170, 29);
			panel.add(pique);
			
			JButton carreau = new JButton("Carreau");
			carreau.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Talon.getInstance().setCouleur(Carte.CARREAU);
					dialog.dispose();
				}
			});
			carreau.setBounds(20, 170, 170, 29);
			panel.add(carreau);
			
			JButton trefle = new JButton("Trèfle");
			trefle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Talon.getInstance().setCouleur(Carte.TREFLE);
					dialog.dispose();
				}
			});
			trefle.setBounds(250, 170, 170, 29);
			panel.add(trefle);

			dialog.setVisible(true);
		}
	}

	public String message() {
		return "La couleur devient " + Carte.COULEURS[Talon.getInstance().getCouleur()];
	}

}
