package view.graphic;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Partie;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class VuePartie implements Observer, Runnable {

	private PartieController controller;
	private Partie partie = Partie.getInstance();
	private VueInitialisation vueInitialisation;
	private VuePartieEnCours vuePartieEnCours;

	public static JFrame frame;

	/**
	 * Create the application.
	 */
	public VuePartie(PartieController controller) {
		this.controller = controller;
		vueInitialisation = new VueInitialisation(controller);
		partie.addObserver(this);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(768, 432);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(vueInitialisation);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Partie) {
			switch ((String) arg1) {
			case "commencerNouvellePartie":
				vuePartieEnCours = new VuePartieEnCours(controller);
				frame.setContentPane(vuePartieEnCours);
				frame.revalidate();
				Iterator<Joueur> iterator = partie.getJoueurs().iterator();
				while (iterator.hasNext()) iterator.next().addObserver(this);
				break;
			case "setJoueurEnCours":
				vuePartieEnCours.refresh();
				break;
			case "mettreAJourScores":				
				vuePartieEnCours.refresh();
				JDialog finDePartie = new JDialog(frame, true);
				finDePartie.add(new VueFinDePartie(controller));
				finDePartie.pack();
				finDePartie.setLocation(200, 200);
				finDePartie.setSize(450, 300);
				finDePartie.setResizable(false);
				finDePartie.setVisible(true);
				break;
			}
		} else if (observable instanceof Joueur) {
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
