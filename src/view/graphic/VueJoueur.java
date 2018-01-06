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

public class VueJoueur extends JPanel {
	
	private Partie partie = Partie.getInstance();
	private Joueur joueurHumain = partie.getJoueurs().get(0);
	private JScrollPane scrollPane;
	private JList<String> listCartes;
	private JButton jouerCarte;
	private JLabel talon;
	private JButton piocher;

	/**
	 * Create the panel.
	 */
	public VueJoueur(PartieController controller) {
		setLayout(null);
		setSize(768, 432);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 310, 194, 90);
		add(scrollPane);
		listCartes = new JList<String>();
		listCartes.setBounds(314, 204, 137, 50);
		scrollPane.setViewportView(listCartes);	
		
		jouerCarte = new JButton("Jouer cette carte");
		jouerCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.faireJouer(joueurHumain,listCartes.getSelectedIndex() + 1);
			}
		});
		jouerCarte.setBounds(435, 307, 146, 29);
		add(jouerCarte);
		
		talon = new JLabel("Talon");
		talon.setBounds(260, 209, 184, 16);
		add(talon);
		
		piocher = new JButton("Piocher");
		piocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.fairePiocher(joueurHumain);
			}
		});
		piocher.setBounds(435, 337, 146, 29);
		add(piocher);
		refresh();
	}
	
	private void updateMain() {
		DefaultListModel<String> model = new DefaultListModel();
		Main main = joueurHumain.getMain();
		Iterator<Carte> iterator = main.iterator();
		while (iterator.hasNext()) model.addElement(iterator.next().afficherCarte());
		listCartes.setModel(model);
	}
	
	public void refresh() {
		updateMain();
		talon.setText(partie.getTalon().getLast().afficherCarte());
	}
}
