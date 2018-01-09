package view.graphic;

import javax.swing.JPanel;

import controller.PartieController;
import model.cartes.Carte;
import model.cartes.Talon;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VuePartieEnCours extends JPanel implements Observer {
	
	private GraphicUtils utils = new GraphicUtils();
	
	private PartieController controller;
	private VueJoueur vueJoueur;
	private ArrayList<VueOrdinateur> vueOrdinateurs = new ArrayList<VueOrdinateur>();
	private Partie partie = Partie.getInstance();
	
	private JLabel talon;
	private JLabel imagePioche;
	private JLabel couleur;

	/**
	 * Create the panel.
	 */
	public VuePartieEnCours(PartieController controller) {
		setBackground(new Color(0, 102, 0));
		this.controller = controller;
		Iterator<Joueur> joueurs = partie.getJoueurs().iterator();
		while (joueurs.hasNext()) joueurs.next().addObserver(this);
		initialize();
		refresh();
	}
	
	public void initialize() {
		setLayout(null);
		setSize(768, 432);

		vueJoueur = new VueJoueur(controller);
		vueJoueur.setBounds(0, 258, 768, 168);
		add(vueJoueur);
		
		Iterator<Joueur> ordinateurs = partie.getJoueurs().iterator();
		while (ordinateurs.hasNext()) {
			Joueur ordinateur = ordinateurs.next();
			if(ordinateur instanceof Ordinateur)	vueOrdinateurs.add(new VueOrdinateur(controller, ordinateur));
		}
		
		int i = 0;
		Iterator<VueOrdinateur> iterator = vueOrdinateurs.iterator();
		while (iterator.hasNext()) {
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
		
		couleur = new JLabel("(Couleur)");
		couleur.setForeground(new Color(255, 255, 255));
		couleur.setBounds(492, 199, 258, 16);
		couleur.setVisible(false);
		add(couleur);
	}
	
	public void refresh() {
		Carte carteTalon = partie.getTalon().getLast();
		
		vueJoueur.refresh();
		Iterator<VueOrdinateur> iterator = vueOrdinateurs.iterator();
		while (iterator.hasNext()) iterator.next().refresh();
		
		talon.setIcon(utils.getResizedIcon(utils.getPath(carteTalon.getValeur(), carteTalon.getCouleur()), 60, 90));
		
		couleur.setText(partie.getTalon().getLast().getActionMessage());
		couleur.setVisible(true);
		
		imagePioche.setText(partie.getPioche().size() + " cartes restantes");
	}
	
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Joueur) {
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
