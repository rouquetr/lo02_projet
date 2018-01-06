package view.graphic;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Partie;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VuePartie implements Observer, Runnable {

	private PartieController controller;
	private Partie partie = Partie.getInstance();
	private VueInitialisation vueInitialisation;
	private VueJoueur vueJoueur;

	private JFrame frame;

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
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(768, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(vueInitialisation);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Partie) {
			switch ((String) arg1) {
			case "commencerNouvellePartie":
				vueJoueur = new VueJoueur(controller);
				frame.setContentPane(vueJoueur);
				frame.revalidate();
				Iterator<Joueur> iterator = partie.getJoueurs().iterator();
				while (iterator.hasNext()) iterator.next().addObserver(this);
				break;
			case "setJoueurEnCours":
				vueJoueur.refresh();
				break;
			}
		} else if (observable instanceof Joueur) {
			switch ((String) arg1) {
			case "piocher":
				vueJoueur.refresh();
				break;
			case "jouerCarte":
				vueJoueur.refresh();
				break;
			}
		}
	}
}
