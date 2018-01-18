package view.cli;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

/**
 * Représente la vue en ligne de commande dans la console
 * Implémente les interfaces Observer et Runnable
 * @author Rouquet Raphael - Mannan Ismail
 *
 */

public class VueLigneDeCommande implements Observer, Runnable {

	/**
	 * instance de la partie en cours
	 */
	private Partie partie = Partie.getInstance();

	/**
	 * controlleur de la partie en cours
	 */
	private PartieController controller;

	/** 
	 * joueur en cours
	 */
	private Joueur joueurEnCours;
	
	private LigneDeCommandeUtils utils = new LigneDeCommandeUtils();
	/**
	 * thread d'initialisation de la partie
	 */
	Thread initialisation = null;
	/**
	 * thread d'un tour de jeu
	 */
	Thread tourDeJeu = null;
	/**
	 * thread du tour de jeu précédent
	 */
	Thread tourDeJeuPrecedent = null;
	/**
	 * thread de fin de partie
	 */
	Thread finDePartie = null;

	/**
	 * initialisation du thread de la partie et initialisation de la vue en ligne de commande
	 * @param	controller
	 */
	public VueLigneDeCommande(PartieController controller) {
		this.controller = controller;
		partie.addObserver(this);
		initialisation = new Thread(this);
		initialisation.start();
	}

	/**
	 * Initialisation du thread
	 */
	public void run() {
		initialiserPartie();
	}

	/**
	 * Le joueur saisie son nom et choisit le nombre de joueurs dans la partie
	 */
	public void initialiserPartie() {
		String nomJoueur = utils.demanderString("Saisissez votre nom de joueur");
		int nombreJoueurs = utils.demanderInt("Combien de joueurs doit comporter la partie?", Partie.MINJOUEUR,
				Partie.MAXJOUEUR);

		controller.initialiserPartie(nombreJoueurs, nomJoueur);
		lancerPartie();
	}

	/**
	 * Le joueur choisit la variante qu'il souhaite
	 */
	public void lancerPartie() {
		int numeroVariante = utils.demanderInt("Quelle variante choisissez-vous?\n" + utils.listerVariantes(), 1, 2);
		controller.lancerPartie(numeroVariante);
	}

	/**
	 * lancement d'un tour de jeu
	 */
	public void effectuerTourDeJeu() {
		tourDeJeuPrecedent = tourDeJeu;
		tourDeJeu = new Thread(() -> {
			joueurEnCours = partie.getJoueurEnCours();
			joueurEnCours.addObserver(this);
			if (controller.authoriserAJouer(joueurEnCours)) {
				try {
					if (joueurEnCours.getClass() != Ordinateur.class) {
						int action = faireJouerJoueur();
						controller.faireJouer(joueurEnCours, action);
					} else {
						tourDeJeu.setName("Ordinateur");
						controller.faireJouer((Ordinateur) joueurEnCours);
					}
				} catch (NoSuchElementException e) {
					System.out.println(
							"Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
				}
			} else
				System.out.println(joueurEnCours.getNom() + " passe son tour.");
		});
		tourDeJeu.start();
		tourDeJeu.setName("cli");
		if(initialisation.isAlive()) initialisation.stop();
		if(finDePartie != null && finDePartie.isAlive()) finDePartie.stop();
		if(tourDeJeuPrecedent != null && tourDeJeuPrecedent.getName() != "Ordinateur") tourDeJeuPrecedent.stop();
	}

	/**
	 * le joueur choisit le numéro de la carte à jouer ou le numéro de l'action à affectuer
	 */
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

	/**
	 * Affichage des scores de chaque joueur
	 * Le joueur peut choisir de rejouer
	 */
	public void afficherFinDePartie() {
		finDePartie = new Thread(() -> {
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
		});
		finDePartie.start();
		if(initialisation.isAlive()) initialisation.stop();
		if(tourDeJeu != null && tourDeJeu.getName() != "Ordinateur") tourDeJeu.stop();
		if(tourDeJeuPrecedent != null && tourDeJeuPrecedent.getName() != "Ordinateur") tourDeJeuPrecedent.stop();
	}

	/**
	 * indique les changements au cours de la partie
	 * @param	observable
	 * @param	arg1
	 */
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
			case "mettreAJourScores":
				System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
				afficherFinDePartie();
				break;
			}
		} else if (observable instanceof Joueur) {
			switch ((String) arg1) {
			case "piocher":
				if(!(joueurEnCours instanceof Ordinateur)) System.out.println(joueurEnCours.getNom() + " a pioché "
						+ joueurEnCours.getMain().getLast().afficherCarteAvecDeterminant());
				else System.out.println(joueurEnCours.getNom() + " a pioché une carte");
				break;
			case "jouerCarte":
				System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
				if (partie.getTalon().getLast().getActionMessage() != "")
					System.out.println(partie.getTalon().getLast().getActionMessage());
				break;
			case "jouerCarteErreur":
				System.out.println(joueurEnCours.getNom() + " ne peut pas jouer cette carte");
				break;
			}
		}
	}
}
