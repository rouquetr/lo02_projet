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

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class VuePartieEnCours extends JPanel {
	
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
		initialize();
		refresh();
	}
	
	public void initialize() {
		setLayout(null);
		setSize(768, 432);

		vueJoueur = new VueJoueur(controller);
		vueJoueur.setBounds(181, 266, 400, 160);
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
			vueOrdinateur.setBounds( i == 0 ? 10 : i * 150 + 10, 10, 150, 100 );
			add(vueOrdinateur);
			i++;
		}
		
		talon = new JLabel(utils.getResizedIcon(utils.getPath(-1, -1), 60, 90));
		talon.setHorizontalAlignment(SwingConstants.CENTER);
		talon.setBounds(420, 162, 60, 90);
		add(talon);
		
		imagePioche = new JLabel("Nombre cartes");
		imagePioche.setForeground(new Color(255, 255, 255));
		imagePioche.setHorizontalAlignment(SwingConstants.CENTER);
		imagePioche.setIcon(utils.getResizedIcon(utils.getPath(-1, -1), 60, 90));
		imagePioche.setBounds(224, 162, 184, 90);
		add(imagePioche);
		
		couleur = new JLabel("(Couleur)");
		couleur.setForeground(new Color(255, 255, 255));
		couleur.setBounds(492, 199, 61, 16);
		couleur.setVisible(false);
		add(couleur);
	}
	
	public void refresh() {
		Carte carteTalon = partie.getTalon().getLast();
		
		vueJoueur.refresh();
		Iterator<VueOrdinateur> iterator = vueOrdinateurs.iterator();
		while (iterator.hasNext()) iterator.next().refresh();
		
		talon.setIcon(utils.getResizedIcon(utils.getPath(carteTalon.getValeur(), carteTalon.getCouleur()), 60, 90));
		
		if(partie.getTalon().getCouleur() != carteTalon.getCouleur()) {
			couleur.setText(Carte.COULEURS[partie.getTalon().getCouleur()]);
			couleur.setVisible(true);
		} else couleur.setVisible(false);
		
		imagePioche.setText("52 cartes restantes");
	}

}
