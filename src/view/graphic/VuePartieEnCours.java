package view.graphic;

import javax.swing.JPanel;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VuePartieEnCours extends JPanel {
	
	private PartieController controller;
	private VueJoueur vueJoueur;
	private ArrayList<VueOrdinateur> vueOrdinateurs = new ArrayList<VueOrdinateur>();
	private Partie partie = Partie.getInstance();
	
	private JLabel talon;

	/**
	 * Create the panel.
	 */
	public VuePartieEnCours(PartieController controller) {
		this.controller = controller;
		initialize();
		refresh();
	}
	
	public void initialize() {
		setLayout(null);
		setSize(768, 432);

		vueJoueur = new VueJoueur(controller);
		vueJoueur.setBounds(188, 281, 395, 145);
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
		
		talon = new JLabel("talon");
		talon.setHorizontalAlignment(SwingConstants.CENTER);
		talon.setBounds(188, 155, 395, 16);
		add(talon);
	}
	
	public void refresh() {
		vueJoueur.refresh();
		Iterator<VueOrdinateur> iterator = vueOrdinateurs.iterator();
		while (iterator.hasNext()) iterator.next().refresh();
		talon.setText(partie.getTalon().afficherTalon());
	}

}
