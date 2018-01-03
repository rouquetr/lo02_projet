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
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VuePartie implements Observer {

	private PartieController controller;
	
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public VuePartie(PartieController controller) {
		this.controller = controller;
		initialize();
		this.frame.setVisible(true);
		Partie.getInstance().addObserver(this);
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
		frame.getContentPane().add(new VueInitialisation(controller));
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if(observable instanceof Partie && (String)arg1 == "commencerNouvellePartie") {
			frame.setContentPane(new VueJoueur(controller));
			frame.revalidate();
		}
	}
}
