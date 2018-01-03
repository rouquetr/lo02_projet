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

public class VueJoueur extends JPanel {
	
	private JLabel lblPartieEnCours;

	/**
	 * Create the panel.
	 */
	public VueJoueur(PartieController controller) {
		setLayout(null);
		setSize(768, 432);
		
		lblPartieEnCours = new JLabel("PARTIE EN COURS");
		lblPartieEnCours.setBounds(148, 67, 111, 16);
		add(lblPartieEnCours);

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(Partie.MINJOUEUR, Partie.MINJOUEUR,
				Partie.MAXJOUEUR, 1);
	}

}
