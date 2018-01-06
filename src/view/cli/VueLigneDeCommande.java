package view.cli;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

public class VueLigneDeCommande implements Observer, Runnable {

	private Partie partie = Partie.getInstance();

	private PartieController controller;

	private Joueur joueurEnCours;
	private boolean nouveauTour = false;

	private LigneDeCommandeUtils utils = new LigneDeCommandeUtils();
	Thread initialisation = null;
	Thread tourDeJeu = null;
	Thread tourDeJeuPrecedent = null;

	public VueLigneDeCommande(PartieController controller) {
		this.controller = controller;
		partie.addObserver(this);
		initialisation = new Thread(this);
		initialisation.start();
	}

	public void run() {
		initialiserPartie();
	}

	public void initialiserPartie() {
		String nomJoueur = utils.demanderString("Saisissez votre nom de joueur");
		int nombreJoueurs = utils.demanderInt("Combien de joueurs doit comporter la partie?", Partie.MINJOUEUR,
				Partie.MAXJOUEUR);

		controller.initialiserPartie(nombreJoueurs, nomJoueur);
		lancerPartie();
	}

	public void lancerPartie() {
		int numeroVariante = utils.demanderInt("Quelle variante choisissez-vous?\n" + utils.listerVariantes(), 1, 2);
		controller.lancerPartie(numeroVariante);
	}

	public void effectuerTourDeJeu() {
		tourDeJeuPrecedent = tourDeJeu;
		tourDeJeu = new Thread(() -> {
			while (true) {
				joueurEnCours = partie.getJoueurEnCours();
				joueurEnCours.addObserver(this);
				if (controller.authoriserAJouer(joueurEnCours)) {
					try {
						if (joueurEnCours.getClass() != Ordinateur.class) {
							int action = faireJouerJoueur();
							controller.faireJouer(joueurEnCours, action);
						} else
							controller.faireJouer((Ordinateur) joueurEnCours);
					} catch (NoSuchElementException e) {
						System.out.println(
								"Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
					}
				} else
					System.out.println(joueurEnCours.getNom() + " passe son tour.");
			}
		});
		tourDeJeu.start();
		if(initialisation.isAlive()) initialisation.stop();
		if(tourDeJeuPrecedent != null) tourDeJeuPrecedent.stop();
	}

	public int faireJouerJoueur() {
		System.out.println(partie.afficherPartie());
		String message = "Indiquez le numéro de la carte que vous voulez jouer:\n" + "0: Piocher\n";
		message += utils.listerCartes(joueurEnCours.getMain(), 1);
		message += "Ou le numéro d'une action a effectuer:\n";
		message += joueurEnCours.getMain().size() + 1 + ": Annoncer carte\n";
		message += utils.listerJoueursOrdinateurs(joueurEnCours.getMain().size() + 2, "Contrer ");
		int max = joueurEnCours.getMain().size() + 1 + (partie.getJoueurs().size() - 1);

		return utils.demanderInt(message, 0, max);
	}

	public void afficherFinDePartie() {
		System.out.println("Les scores sont: ");
		Iterator<Joueur> iterator = partie.getJoueursByScore().iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			System.out.println(joueur.getNom() + ": " + joueur.getPoints() + " points");
		}
		String message = "Voulez-vous: \n" + "1: relancer une partie?\n"
				+ "2: relancer une partie en changeant les paramètres (votre nom ainsi que le nombre de joueurs?\n"
				+ "3: Arréter de jouer?";
		switch (utils.demanderInt(message, 1, 3)) {
		case 1:
			lancerPartie();
			break;
		case 2:
			initialiserPartie();
			break;
		case 3:
			System.exit(0);
			break;
		}
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Partie) {
			switch ((String) arg1) {
			case "commencerNouvellePartie":
				System.out.println("Une nouvelle partie commence: ");
				effectuerTourDeJeu();
				break;
			case "setJoueurEnCours":
				effectuerTourDeJeu();
				break;
			}
		} else if (observable instanceof Joueur) {
			switch ((String) arg1) {
			case "piocher":
				System.out.println(joueurEnCours.getNom() + " a pioché "
						+ joueurEnCours.getMain().getLast().afficherCarteAvecDeterminant());
				break;
			case "jouerCarte":
				System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
				if (partie.getTalon().getLast().getActionMessage() != "")
					System.out.println(partie.getTalon().getLast().getActionMessage());
				break;
			case "jouerCarteErreur":
				System.out.println(joueurEnCours.getNom() + " ne peut pas jouer cette carte");
				break;
			case "aAnnonceCarteErreur":
				System.out.println(joueurEnCours.getNom() + " ne peut pas annoncer Carte");
				break;
			case "aAnnonceCarte":
				System.out.println(joueurEnCours.getNom() + " a annoncé Carte");
				break;
			case "partieTerminee":
				System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
				controller.terminerPartie();
				afficherFinDePartie();
				break;
			}
		}
	}
}
