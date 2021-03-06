package model.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.cartes.Carte;
import model.cartes.Main;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;
import view.cli.LigneDeCommandeUtils;
import view.graphic.GraphicUtils;
import view.graphic.VuePartie;

/**
 * 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class DonnerCarte implements Action {
	
	/**
	 * message indiquant le résultat de l'action
	 */
	private String message = "";
	
	/** 
	 * Le joueur donne la carte de son choix au joueur choisi (version cli)
	 **/
	public void actionCli() {
		Partie partie = Partie.getInstance();
		if(partie.getJoueurEnCours().getClass() == Ordinateur.class) actionOrdinateur();
		else {
			LigneDeCommandeUtils utils = new LigneDeCommandeUtils();
			
			String message = "A qui allez vous donner une carte?\n";
			message += utils.listerJoueursOrdinateurs(1, "");
			
			Joueur joueurChoisi = partie.getJoueurs().get(utils.demanderInt(message, 1, partie.getJoueurs().size()));		//On demande au joueur humain de choisir un de ses adversaires
			
			message = "Choisissez la carte à donner à " + joueurChoisi.getNom() + ": \n" + utils.listerCartes(partie.getJoueurEnCours().getMain(), 1);
			Carte carteChoisie = partie.getJoueurEnCours().getMain().get(utils.demanderInt(message, 1, partie.getJoueurEnCours().getMain().size()) - 1);	// Puis de choisir une carte
			
			joueurChoisi.getMain().add(carteChoisie);						// On ajoute la carte a la main de l'adversaire
			partie.getJoueurEnCours().getMain().remove(carteChoisie);			// On la retire de celle du joueur en cours
			this.message = partie.getJoueurEnCours().getNom() + " a donné une carte à " + joueurChoisi.getNom();
		}
	}
	
	/** 
	 * Le joueur donne la carte de son choix au joueur choisi (version graphique)
	 **/
	public void actionGui() {
		GraphicUtils utils = new GraphicUtils();
		if(Partie.getInstance().getJoueurEnCours().getClass() == Ordinateur.class) actionOrdinateur();
		else {														// On ouvre un dialog avec la liste des cartes et un bouton par joueur à qui donner une carte
			JDialog dialog = new JDialog(VuePartie.frame, true);
			dialog.setLocation(200, 200);
			dialog.setSize(450, 300);
			dialog.setResizable(false);
			
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setSize(450, 300);
			panel.setBackground(new Color(0, 102, 0));
			dialog.add(panel);
			
			JLabel instructions = new JLabel("Choisissez une carte et donnez la à un adversaire");
			instructions.setBounds(20, 20, 450, 16);
			instructions.setForeground(new Color(255, 255, 255));
			panel.add(instructions);
			
			JScrollPane scrollPane =  new JScrollPane();			// On affiche une liste avec les différentes cartes du joueur Humain
			scrollPane.setBounds(20, 50, 400, 100);
			JList<ImageIcon> listCartes = new JList<ImageIcon>();
			listCartes.setLayoutOrientation(JList.VERTICAL_WRAP);
			listCartes.setVisibleRowCount(-1);
			scrollPane.setViewportView(listCartes);
			panel.add(scrollPane);
			
			DefaultListModel<ImageIcon> model = new DefaultListModel<ImageIcon>();
			Main main = Partie.getInstance().getJoueurEnCours().getMain();
			Iterator<Carte> iterator = main.iterator();
			while (iterator.hasNext()) {
				Carte carte = iterator.next();
				model.addElement(utils.getResizedIcon(utils.getPath(carte.getValeur(), carte.getCouleur()), 60, 90));
			}
			listCartes.setModel(model);
			
			Iterator<Joueur> joueurs = Partie.getInstance().getJoueurs().iterator();		// On affiche un bouton par ordinateur
			joueurs.next();
			int i = 0;
			while (joueurs.hasNext()) {
				Joueur joueur = joueurs.next();
				JButton donnerCarte = new JButton(joueur.getNom());
				donnerCarte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Carte carteChoisie = Partie.getInstance().getJoueurEnCours().getMain().get(listCartes.getSelectedIndex());	 // On recupere la carte choisie par le joueur
						joueur.getMain().add(carteChoisie);				// Puis on l'ajoute à la main de l'adversaire choisi
						Partie.getInstance().getJoueurEnCours().getMain().remove(carteChoisie);		// Et on la retire de celle du joueur humain
						message = Partie.getInstance().getJoueurEnCours().getNom() + " a donné une carte à " + joueur.getNom();
						dialog.dispose();			// Enfin, on ferme le dialog
					}
				});
				switch (i) {
				case 0:
					donnerCarte.setBounds(20, 170, 170, 29);
					break;
				case 1:
					donnerCarte.setBounds(250, 170, 170, 29);
					break;
				case 2:
					donnerCarte.setBounds(20, 220, 170, 29);
					break;
				case 3:
					donnerCarte.setBounds(250, 220, 170, 29);
					break;
				}
				panel.add(donnerCarte);
				i++;
			}
			
			dialog.setVisible(true);
		}
	}
	
	/**
	 * permet à l'ordinateur de donner une carte à un autre joueur
	 */
	public void actionOrdinateur() {
		Partie partie = Partie.getInstance();
		Carte carteChoisie = partie.getJoueurEnCours().getMain().get(0);		// l'ordinateur prend toujours la premiere carte de sa main
		partie.getJoueurs().get(0).getMain().add(carteChoisie);				// Et la donne toujours au joueur humain
		partie.getJoueurEnCours().getMain().remove(carteChoisie);
		this.message = partie.getJoueurEnCours().getNom() + " a donné une carte à " + partie.getJoueurs().get(0).getNom();
	}
	
	/**
	 * Affichage du message indiquant le joueur choisi pour donner une carte
	 */
	public String message() {
		return message;
	}

}
