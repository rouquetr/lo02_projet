package view.graphic;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Partie;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Représente la vue graphique de la partie
 * implémente les interfaces Observer et runnable
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class VuePartie implements Observer, Runnable {

	/**
	 * Le controller de la partie
	 */
	private PartieController controller;
	
	/**
	 * La partie en cours
	 */
	private Partie partie = Partie.getInstance();
	
	/**
	 * La vue de l'initialisation de la partie
	 */
	private VueInitialisation vueInitialisation;
	
	/** 
	 * la vue de la partie en cours
	 */
	private VuePartieEnCours vuePartieEnCours;

	/**
	 * La fenêtre de l'interface graphique, elle est publique pour pouvoir y accéder depuis les actions des cartes
	 */
	public static JFrame frame;

	/**
	 * Constructeur
	 * @param controller le controlleur de la partie.
	 */
	public VuePartie(PartieController controller) {
		this.controller = controller;
		vueInitialisation = new VueInitialisation(controller);
		partie.addObserver(this);		// on observe la partie en cours
		Thread t = new Thread(this);		// on créée le thread de la vue graphique
		t.start();						// puis on le démarre
	}

	/**
	 * action du thread
	 */
	@Override
	public void run() {
		initialize();					// on initialise la vue graphique
		frame.setVisible(true);			// on affiche la fenêtre
	}

	/**
	 * On initialise la fenêtre
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(768, 432);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(vueInitialisation);		// la première vue nécessaire est la vue d'initialisation
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Partie) {
			switch ((String) arg1) {
			case "commencerNouvellePartie":			// si on commence une nouvelle partie, on affiche la vue de la partie en cours
				vuePartieEnCours = new VuePartieEnCours(controller);
				frame.setContentPane(vuePartieEnCours);
				frame.revalidate();
				Iterator<Joueur> iterator = partie.getJoueurs().iterator();
				while (iterator.hasNext()) iterator.next().addObserver(this); 	// On observe les joueurs que comporte la partie
				break;
			case "setJoueurEnCours":
				vuePartieEnCours.refresh();			// a chaque changement de joueur en cours, on raffraichit la vue de la partie en cours
				break;
			case "mettreAJourScores":				// quand la partie se termine	
				vuePartieEnCours.refresh();			// on raffraichit la partie en cours
				JDialog finDePartie = new JDialog(frame, true);		// puis on ouvre un dialog
				finDePartie.add(new VueFinDePartie(controller));		// qui contient la vue de la fin de partie
				finDePartie.pack();
				finDePartie.setLocation(200, 200);
				finDePartie.setSize(450, 300);
				finDePartie.setResizable(false);
				finDePartie.setVisible(true);
				break;
			}
		} else if (observable instanceof Joueur) {	// a chaque action d'un joueur, on raffraichit la vue de la partie en cours
			switch ((String) arg1) {
			case "piocher":
				vuePartieEnCours.refresh();
				break;
			case "jouerCarte":
				vuePartieEnCours.refresh();
				break;
			}
		}
	}
}
