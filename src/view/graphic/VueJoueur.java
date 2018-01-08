package view.graphic;

import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.PartieController;
import model.cartes.Carte;
import model.cartes.Main;
import model.joueurs.Joueur;
import model.joueurs.Partie;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VueJoueur extends JPanel {
	
	GraphicUtils utils = new GraphicUtils();
	
	private PartieController controller;
	private Partie partie = Partie.getInstance();
	private Joueur joueurHumain = partie.getJoueurs().get(0);
	
	private JScrollPane scrollPane;
	private JList<ImageIcon> listCartes;
	private JButton jouerCarte;
	private JButton piocher;
	private JLabel nomJoueur;
	private JLabel nbCartes;

	/**
	 * Create the panel.
	 */
	public VueJoueur(PartieController controller) {
		setBackground(new Color(0, 102, 0));
		this.controller = controller;
		initialize();
		refresh();
	}
	
	private void initialize() {
		setLayout(null);
		setSize(400, 160);
		
		scrollPane =  new JScrollPane();
		scrollPane.setBounds(23, 34, 194, 109);
		add(scrollPane);
		listCartes = new JList<ImageIcon>();
		listCartes.setLayoutOrientation(JList.VERTICAL_WRAP);
		listCartes.setVisibleRowCount(-1);
		scrollPane.setViewportView(listCartes);
		
		jouerCarte = new JButton("Jouer cette carte");
		jouerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.boutonJouer(joueurHumain,listCartes.getSelectedIndex() + 1);
			}
		});
		jouerCarte.setBounds(229, 34, 146, 29);
		add(jouerCarte);
		
		piocher = new JButton("Piocher");
		piocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.boutonPiocher(joueurHumain);
			}
		});
		piocher.setBounds(229, 75, 146, 29);
		add(piocher);
		
		nomJoueur = new JLabel(joueurHumain.getNom());
		nomJoueur.setForeground(new Color(255, 255, 255));
		nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		nomJoueur.setBounds(23, 6, 352, 16);
		add(nomJoueur);
		
		nbCartes = new JLabel();
		nbCartes.setForeground(new Color(255, 255, 255));
		nbCartes.setHorizontalAlignment(SwingConstants.CENTER);
		nbCartes.setBounds(219, 116, 175, 16);
		add(nbCartes);
	}
	
	private void updateMain() {
		DefaultListModel<ImageIcon> model = new DefaultListModel<ImageIcon>();
		Main main = joueurHumain.getMain();
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) {
			Carte carte = iterator.next();
			model.addElement(utils.getResizedIcon(utils.getPath(carte.getValeur(), carte.getCouleur()), 60, 90));
		}
		listCartes.setModel(model);
		nbCartes.setText(main.size() + " cartes");
	}
	
	public void refresh() {
		updateMain();
	}
}
