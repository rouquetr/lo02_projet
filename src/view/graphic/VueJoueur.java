package view.graphic;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

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

/**
 * Vue du joueur humain
 * implémente l'interface observer
 * hérite de JPanel
 * @author Rouquet Raphael - Mannan Ismail
 */
public class VueJoueur extends JPanel implements Observer {
	
	/**
	 * Les utilitaires graphiques
	 */
	private GraphicUtils utils = new GraphicUtils();
	
	/**
	 * Le controller de la partie
	 */
	private PartieController controller;
	
	/**
	 * l'instance de la partie
	 */
	private Partie partie = Partie.getInstance();
	
	/**
	 * Le joueur humain, toujours en position 0
	 */
	private Joueur joueurHumain = partie.getJoueurs().get(0);
	
	/**
	 * les différents éléments graphiques
	 */
	private JScrollPane scrollPane;
	private JList<ImageIcon> listCartes;
	private JButton jouerCarte;
	private JButton piocher;
	private JLabel nomJoueur;
	private JLabel nbCartes;
	private JButton btnAnnoncerCarte;

	/**
	 * Creation du panel
	 * @param controller le controlleur de la partie
	 */
	public VueJoueur(PartieController controller) {
		partie.addObserver(this);	// on observe le joueur humain
		setBackground(new Color(0, 102, 0));
		this.controller = controller;
		initialize();		// on initialise
		refresh();			// et raffraichit aussitôt
	}
	
	/**
	 * Méthode privée
	 * Initialisation de la vue
	 */
	private void initialize() {
		setLayout(null);
		setSize(768, 160);
		
		scrollPane =  new JScrollPane();
		scrollPane.setBounds(23, 34, 456, 109);
		add(scrollPane);
		listCartes = new JList<ImageIcon>();		// on créée une nouvelle liste d'images
		listCartes.setLayoutOrientation(JList.VERTICAL_WRAP);
		listCartes.setVisibleRowCount(-1);
		scrollPane.setViewportView(listCartes);
		
		jouerCarte = new JButton("Jouer cette carte");
		jouerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		// bouton pour jouer la carte sélectionnée dans la liste
				controller.boutonJouer(joueurHumain,listCartes.getSelectedIndex() + 1);
			}
		});
		jouerCarte.setBounds(491, 45, 146, 29);
		add(jouerCarte);
		
		piocher = new JButton("Piocher");
		piocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		// bouton pour piocher une carte
				controller.boutonPiocher(joueurHumain);
			}
		});
		piocher.setBounds(491, 86, 146, 29);
		add(piocher);
		
		nomJoueur = new JLabel(joueurHumain.getNom());
		nomJoueur.setForeground(new Color(255, 255, 255));
		nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		nomJoueur.setBounds(225, 6, 352, 16);
		add(nomJoueur);
		
		nbCartes = new JLabel();
		nbCartes.setForeground(new Color(255, 255, 255));
		nbCartes.setHorizontalAlignment(SwingConstants.CENTER);
		nbCartes.setBounds(481, 127, 175, 16);
		add(nbCartes);
		
		btnAnnoncerCarte = new JButton("Annoncer");
		btnAnnoncerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//bouton pour annoncer une carte
				controller.boutonAnnoncer(joueurHumain);
			}
		});
		btnAnnoncerCarte.setBounds(643, 45, 119, 29);
		add(btnAnnoncerCarte);
	}
	
	/**
	 * Méthode privée
	 * Permet de mettre à jour la liste des cartes du joueur
	 */
	private void updateMain() {
		DefaultListModel<ImageIcon> model = new DefaultListModel<ImageIcon>(); // on créée un modèle de liste d'images
		Main main = joueurHumain.getMain();
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) {		// on parcourt la main du joueur pour ajouter chacune de ses cartes à la liste
			Carte carte = iterator.next();
			model.addElement(utils.getResizedIcon(utils.getPath(carte.getValeur(), carte.getCouleur()), 60, 90));
		}
		listCartes.setModel(model);
		nbCartes.setText(main.size() + " cartes");
	}
	
	/**
	 * Permet de raffraichir la vue du joueur
	 */
	public void refresh() {
		updateMain();		// met à jouer la main
		if(partie.getJoueurEnCours().getNom() == joueurHumain.getNom()) { // active les actions du joueur si c'est son tour
			piocher.setEnabled(true);
			jouerCarte.setEnabled(true);
		} else {															// les bloque sinon
			piocher.setEnabled(false);
			jouerCarte.setEnabled(false);
		}
	}

	/**
	 * Indique les actions des objets observables
	 * @param	observable
	 * @param	arg1
	 */
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Partie) {
			switch ((String) arg1) {		// a chaque changement de joueur en cours, raffraichit la vue
			case "setJoueurEnCours":
				refresh();
				break;
			}
		}
	}
}
