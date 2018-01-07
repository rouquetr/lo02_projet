package view.graphic;

import java.util.Iterator;
import javax.swing.DefaultListModel;
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

public class VueJoueur extends JPanel {
	
	private PartieController controller;
	private Partie partie = Partie.getInstance();
	private Joueur joueurHumain = partie.getJoueurs().get(0);
	private JScrollPane scrollPane;
	private JList<String> listCartes;
	private JButton jouerCarte;
	private JButton piocher;
	private JLabel nomJoueur;
	private JLabel nbCartes;

	/**
	 * Create the panel.
	 */
	public VueJoueur(PartieController controller) {
		this.controller = controller;
		initialize();
		refresh();
	}
	
	private void initialize() {
		setLayout(null);
		setSize(395, 145);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 34, 194, 90);
		add(scrollPane);
		
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
		nomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		nomJoueur.setBounds(23, 6, 352, 16);
		add(nomJoueur);
		listCartes = new JList<String>();
		listCartes.setBounds(23, 34, 190, 86);
		add(listCartes);
		
		nbCartes = new JLabel();
		nbCartes.setHorizontalAlignment(SwingConstants.CENTER);
		nbCartes.setBounds(23, 123, 194, 16);
		add(nbCartes);
	}
	
	private void updateMain() {
		DefaultListModel<String> model = new DefaultListModel();
		Main main = joueurHumain.getMain();
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) model.addElement(iterator.next().afficherCarte());
		listCartes.setModel(model);
		nbCartes.setText(main.size() + " cartes");
	}
	
	public void refresh() {
		updateMain();
	}
}
