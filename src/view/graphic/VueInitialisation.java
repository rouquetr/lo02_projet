package view.graphic;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.PartieController;
import model.joueurs.Partie;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VueInitialisation {

	private PartieController controller;
	
	private JFrame frame;
	private JLabel labelNom;
	private JLabel labelNombreJoueur;
	private JLabel labelVariantes;
	private JTextField fieldNom;
	private JSpinner spinnerNombreJoueur;
	private JList<String> listVariantes;
	private JButton buttonCommencerPartie;

	/**
	 * Create the application.
	 */
	public VueInitialisation(PartieController controller) {
		this.controller = controller;
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
		
		labelNom = new JLabel("Quel est votre nom?");
		labelNom.setBounds(20, 108, 249, 26);
		frame.getContentPane().add(labelNom);
		
		fieldNom = new JTextField();
		fieldNom.setBounds(315, 113, 194, 26);
		frame.getContentPane().add(fieldNom);
		fieldNom.setColumns(10);
		
		labelNombreJoueur = new JLabel("A combien voulez-vous jouer?");
		labelNombreJoueur.setBounds(20, 156, 191, 16);
		frame.getContentPane().add(labelNombreJoueur);

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(Partie.MINJOUEUR, Partie.MINJOUEUR, Partie.MAXJOUEUR, 1);
		spinnerNombreJoueur = new JSpinner(spinnerNumberModel);
		spinnerNombreJoueur.setBounds(315, 151, 37, 26);
		frame.getContentPane().add(spinnerNombreJoueur);
		
		labelVariantes = new JLabel("Quelle variante souhaitez-vous choisir?");
		labelVariantes.setBounds(20, 204, 249, 16);
		frame.getContentPane().add(labelVariantes);
		
		listVariantes = new JList<String>(new AbstractListModel() {
			String[] values = new String[] {"Jeu de base", "Variante de Monclar"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listVariantes.setBounds(314, 204, 137, 50);
		frame.getContentPane().add(listVariantes);
		
		buttonCommencerPartie = new JButton("Commencer la partie !");
		buttonCommencerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.initialiserPartie((int)spinnerNombreJoueur.getValue(), fieldNom.getText());
				controller.lancerPartie(listVariantes.getSelectedIndex() + 1);
			}
		});
		buttonCommencerPartie.setBounds(289, 304, 182, 29);
		frame.getContentPane().add(buttonCommencerPartie);
	}
}
