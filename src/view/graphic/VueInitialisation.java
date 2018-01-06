package view.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
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

public class VueInitialisation extends JPanel {
	
	private JLabel labelNom;
	private JLabel labelNombreJoueur;
	private JLabel labelVariantes;
	private JTextField fieldNom;
	private JSpinner spinnerNombreJoueur;
	private JList<String> listVariantes;
	private JButton buttonCommencerPartie;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public VueInitialisation(PartieController controller) {
		setLayout(null);
		setSize(768, 432);
		
		labelNom = new JLabel("Quel est votre nom?");
		labelNom.setBounds(20, 108, 249, 26);
		add(labelNom);
		
		fieldNom = new JTextField();
		fieldNom.setBounds(315, 113, 194, 26);
		add(fieldNom);
		fieldNom.setColumns(10);
		
		labelNombreJoueur = new JLabel("A combien voulez-vous jouer?");
		labelNombreJoueur.setBounds(20, 156, 191, 16);
		add(labelNombreJoueur);

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(Partie.MINJOUEUR, Partie.MINJOUEUR, Partie.MAXJOUEUR, 1);
		spinnerNombreJoueur = new JSpinner(spinnerNumberModel);
		spinnerNombreJoueur.setBounds(315, 151, 37, 26);
		add(spinnerNombreJoueur);
		
		labelVariantes = new JLabel("Quelle variante souhaitez-vous choisir?");
		labelVariantes.setBounds(20, 204, 249, 16);
		add(labelVariantes);
		
		buttonCommencerPartie = new JButton("Commencer la partie !");
		buttonCommencerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.initialiserPartie((int)spinnerNombreJoueur.getValue(), fieldNom.getText());
				controller.lancerPartie(listVariantes.getSelectedIndex() + 1);
			}
		});
		buttonCommencerPartie.setBounds(289, 304, 182, 29);
		add(buttonCommencerPartie);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 204, 194, 57);
		add(scrollPane);
		
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
		scrollPane.setViewportView(listVariantes);	
	}
}
