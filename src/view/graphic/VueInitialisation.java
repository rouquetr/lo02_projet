package view.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.PartieController;
import model.joueurs.Partie;
import java.awt.Color;
import java.awt.Font;

public class VueInitialisation extends JPanel {
	
	private JLabel labelNom;
	private JLabel labelNombreJoueur;
	private JLabel labelVariantes;
	private JTextField fieldNom;
	private JSpinner spinnerNombreJoueur;
	private JList<String> listVariantes;
	private JButton buttonCommencerPartie;
	private JScrollPane scrollPane;
	private JLabel logo;
	private JLabel titre;

	/**
	 * Create the panel.
	 */
	public VueInitialisation(PartieController controller) {
		setBackground(new Color(0, 102, 0));
		setLayout(null);
		setSize(768, 432);
		
		labelNom = new JLabel("Quel est votre nom?");
		labelNom.setForeground(new Color(255, 255, 255));
		labelNom.setBounds(19, 164, 249, 26);
		add(labelNom);
		
		fieldNom = new JTextField();
		fieldNom.setForeground(new Color(255, 255, 255));
		fieldNom.setBackground(new Color(0, 102, 51));
		fieldNom.setBounds(314, 169, 194, 26);
		add(fieldNom);
		fieldNom.setColumns(10);
		
		labelNombreJoueur = new JLabel("A combien voulez-vous jouer?");
		labelNombreJoueur.setForeground(new Color(255, 255, 255));
		labelNombreJoueur.setBounds(19, 212, 191, 16);
		add(labelNombreJoueur);

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(Partie.MINJOUEUR, Partie.MINJOUEUR, Partie.MAXJOUEUR, 1);
		spinnerNombreJoueur = new JSpinner(spinnerNumberModel);
		spinnerNombreJoueur.setForeground(new Color(255, 255, 255));
		spinnerNombreJoueur.setBackground(new Color(0, 102, 51));
		spinnerNombreJoueur.setBounds(314, 207, 37, 26);
		add(spinnerNombreJoueur);
		
		labelVariantes = new JLabel("Quelle variante souhaitez-vous choisir?");
		labelVariantes.setForeground(new Color(255, 255, 255));
		labelVariantes.setBounds(19, 260, 249, 16);
		add(labelVariantes);
		
		buttonCommencerPartie = new JButton("Commencer la partie !");
		buttonCommencerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.initialiserPartie((int)spinnerNombreJoueur.getValue(), fieldNom.getText());
				controller.lancerPartie(listVariantes.getSelectedIndex() + 1);
			}
		});
		buttonCommencerPartie.setBounds(288, 360, 182, 29);
		add(buttonCommencerPartie);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(314, 260, 194, 57);
		add(scrollPane);
		
		listVariantes = new JList<String>(new AbstractListModel<String>() {
			String[] values = new String[] {"Jeu de base", "Variante de Monclar"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		listVariantes.setForeground(new Color(255, 255, 255));
		listVariantes.setBackground(new Color(0, 102, 51));
		listVariantes.setBounds(314, 204, 137, 50);
		scrollPane.setViewportView(listVariantes);
		
		logo = new JLabel(new ImageIcon(VueInitialisation.class.getResource("/icones/logo.png")));
		logo.setBounds(304, 6, 167, 100);
		add(logo);
		
		titre = new JLabel("8 Américain");
		titre.setFont(new Font("Marker Felt", Font.PLAIN, 20));
		titre.setForeground(new Color(255, 255, 255));
		titre.setBounds(344, 118, 93, 23);
		add(titre);
	}
}
