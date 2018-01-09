package view.graphic;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Partie;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Window;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VueFinDePartie extends JPanel {
	
	Partie partie = Partie.getInstance();
	PartieController controller;
	
	private JButton relancer;
	private JButton nouvellePartie;
	private JButton quitter;
	private JLabel lblRsultats;
	private JLabel premier;
	private JLabel second;
	private JLabel troisieme;
	private JLabel quatrieme;
	private JLabel cinquieme;

	/**
	 * Create the panel.
	 */
	public VueFinDePartie(PartieController controller) {
		this.controller = controller;
		initialize();
		montrerClassement();
	}
	
	private void initialize() {
		setBackground(new Color(0, 102, 0));
		setLayout(null);
		
		relancer = new JButton("Relancer");
		relancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choisirVariante();
			}
		});
		relancer.setBounds(148, 181, 138, 29);
		add(relancer);
		
		nouvellePartie = new JButton("Nouvelle Partie");
		nouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(nouvellePartie);
				JFrame frame = (JFrame) dialog.getParent();
				frame.setContentPane(new VueInitialisation(controller));
				frame.revalidate();
				dialog.dispose();
			}
		});
		nouvellePartie.setBounds(148, 211, 138, 29);
		add(nouvellePartie);
		
		quitter = new JButton("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitter.setBounds(148, 240, 138, 29);
		add(quitter);
		
		lblRsultats = new JLabel("Résultats:");
		lblRsultats.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblRsultats.setForeground(new Color(255, 255, 255));
		lblRsultats.setBounds(62, 27, 71, 19);
		add(lblRsultats);
		
		premier = new JLabel("Premier");
		premier.setForeground(new Color(255, 255, 255));
		premier.setBounds(148, 29, 260, 16);
		premier.setVisible(false);
		add(premier);
		
		second = new JLabel("Second");
		second.setForeground(new Color(255, 255, 255));
		second.setBounds(148, 57, 260, 16);
		second.setVisible(false);
		add(second);
		
		troisieme = new JLabel("Troisième");
		troisieme.setForeground(new Color(255, 255, 255));
		troisieme.setBounds(148, 85, 260, 16);
		troisieme.setVisible(false);
		add(troisieme);
		
		quatrieme = new JLabel("Quatrième");
		quatrieme.setForeground(new Color(255, 255, 255));
		quatrieme.setBounds(148, 113, 260, 16);
		quatrieme.setVisible(false);
		add(quatrieme);
		
		cinquieme = new JLabel("Cinquième");
		cinquieme.setForeground(new Color(255, 255, 255));
		cinquieme.setBounds(148, 141, 260, 16);
		cinquieme.setVisible(false);
		add(cinquieme);
		
		
	}
	
	private void choisirVariante() {
		removeAll();
		repaint();
		setBackground(new Color(0, 102, 0));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 100, 193, 70);
		add(scrollPane);
		
		JList<String> listVariantes = new JList<String>(new AbstractListModel() {
			String[] values = new String[] {"Jeu de base", "Variante de Monclar"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listVariantes.setForeground(new Color(255, 255, 255));
		listVariantes.setBackground(new Color(0, 102, 51));
		listVariantes.setBounds(314, 204, 137, 50);
		scrollPane.setViewportView(listVariantes);
		
		JButton choisirVariante = new JButton("Choisir cette Variante");
		choisirVariante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.lancerPartie(listVariantes.getSelectedIndex() + 1);
				SwingUtilities.getWindowAncestor(listVariantes).dispose();
			}
		});
		choisirVariante.setBounds(148, 181, 138, 29);
		add(choisirVariante);
	}
	
	private void montrerClassement() {
		Iterator<Joueur> classementJoueurs = partie.getJoueursByScore().iterator();repaint();
		int i = 1;
		while (classementJoueurs.hasNext()) {
			Joueur joueur = classementJoueurs.next();
			switch (i) {
			case 1:
				premier.setText(joueur.getNom() + ": " + joueur.getPoints() + " points");
				premier.setVisible(true);
				break;
			case 2:
				second.setText(joueur.getNom() + ": " + joueur.getPoints() + " points");
				second.setVisible(true);
				break;
			case 3:
				troisieme.setText(joueur.getNom() + ": " + joueur.getPoints() + " points");
				troisieme.setVisible(true);
				break;
			case 4:
				quatrieme.setText(joueur.getNom() + ": " + joueur.getPoints() + " points");
				quatrieme.setVisible(true);
				break;
			case 5:
				cinquieme.setText(joueur.getNom() + ": " + joueur.getPoints() + " points");
				cinquieme.setVisible(true);
				break;
			}
			i++;
		}
	}

}
