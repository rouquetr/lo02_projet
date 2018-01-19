package view.graphic;

import javax.swing.JPanel;

import controller.PartieController;
import model.cartes.Carte;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Vue de la partie en cours
 * Implémente l'interface Observer
 * hérite de JPanel
 * @author Rouquet Raphael - Mannan Ismail
 */
public class VuePartieEnCours extends JPanel implements Observer {
	
	/**
	 * Les utilitaires graphiques
	 */
	private GraphicUtils utils = new GraphicUtils();
	
	/**
	 * Le controleur de la partie
	 */
	private PartieController controller;
	
	/**
	 * La vue du joueur humain
	 */
	private VueJoueur vueJoueur;
	
	/**
	 * La liste des vues des ordinateurs
	 */
	private ArrayList<VueOrdinateur> vueOrdinateurs = new ArrayList<VueOrdinateur>();
	
	/**
	 * L'instance de la partie en cours
	 */
	private Partie partie = Partie.getInstance();
	
	/*
	 * Les composants graphiques
	 */
	private JLabel talon;
	private JLabel imagePioche;
	private JLabel couleur;

	/**
	 * Creation du panel
	 */
	public VuePartieEnCours(PartieController controller) {
		setBackground(new Color(0, 102, 0));
		this.controller = controller;
		Iterator<Joueur> joueurs = partie.getJoueurs().iterator();	// on observe les joueurs de la partie
		while (joueurs.hasNext()) joueurs.next().addObserver(this);
		initialize();
		refresh();
	}
	
	/**
	 * Méthode privée
	 * Initialisation
	 */
	private void initialize() {
		setLayout(null);
		setSize(768, 432);

		vueJoueur = new VueJoueur(controller);
		vueJoueur.setBounds(0, 258, 768, 168);
		add(vueJoueur);		// on ajoute la vue du joueur
		
		Iterator<Joueur> ordinateurs = partie.getJoueurs().iterator();
		while (ordinateurs.hasNext()) {			// on créée et ajoute les différentes vues ordinateur à la collection
			Joueur ordinateur = ordinateurs.next();
			if(ordinateur instanceof Ordinateur)	vueOrdinateurs.add(new VueOrdinateur(controller, ordinateur));
		}
		
		int i = 0;
		Iterator<VueOrdinateur> iterator = vueOrdinateurs.iterator();
		while (iterator.hasNext()) {		// on place les vues ordinateurs
			VueOrdinateur vueOrdinateur = iterator.next();
			vueOrdinateur.setBounds( i == 0 ? 10 : i * 200 + 10, 10, 150, 150 );
			add(vueOrdinateur);
			i++;
		}
		
		talon = new JLabel(utils.getResizedIcon(utils.getPath(-1, -1), 60, 90));
		talon.setHorizontalAlignment(SwingConstants.CENTER);
		talon.setBounds(420, 162, 60, 90);
		add(talon);
		
		imagePioche = new JLabel("52 cartes restantes");
		imagePioche.setForeground(new Color(255, 255, 255));
		imagePioche.setHorizontalAlignment(SwingConstants.CENTER);
		imagePioche.setIcon(utils.getResizedIcon(utils.getPath(-1, -1), 60, 90));
		imagePioche.setBounds(224, 162, 184, 90);
		add(imagePioche);
		
		couleur = new JLabel();
		couleur.setForeground(new Color(255, 255, 255));
		couleur.setBounds(492, 199, 258, 16);
		couleur.setVisible(false);
		add(couleur);
	}
	
	/**
	 * Permet de raffraichir la vue
	 */
	public void refresh() {
		Carte carteTalon = partie.getTalon().getLast();
		
		vueJoueur.refresh();	// raffraichit la vue du joueur
		Iterator<VueOrdinateur> iterator = vueOrdinateurs.iterator();
		while (iterator.hasNext()) iterator.next().refresh();		// raffraichit la vue de chaque ordinateur
			
		talon.setIcon(utils.getResizedIcon(utils.getPath(carteTalon.getValeur(), carteTalon.getCouleur()), 60, 90));		// met à jour l'image du talon
		
		if(partie.getTalon().size() > 1) {		//affiche le message de l'action effectuée par la dernière carte jouée
			couleur.setText(partie.getTalon().getLast().getActionMessage());
			couleur.setVisible(true);
		}
		
		imagePioche.setText(partie.getPioche().size() + " cartes restantes");
	}
	
	/**
	 * Indique les actions des objets observables
	 * @param	observable
	 * @param	arg1
	 */
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Joueur) {	// on raffraichit la vue à chaque action d'un joueur
			switch ((String) arg1) {
			case "piocher":
				refresh();
				break;
			case "jouerCarte":
				refresh();
				break;
			}
		}
	}

}
