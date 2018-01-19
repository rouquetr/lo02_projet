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
	 * Lancement de la vue de la ligne de commande
	 * @param	controller
	 */
	public VueLigneDeCommande(PartieController controller) {
		this.controller = controller;
		partie.addObserver(this);				// on observe la partie en cours
		initialisation = new Thread(this);		// on créée un nouveau thread pour l'initialisation de la partie
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
	 * Le joueur choisit la variante qu'il souhaite et on démarre la partie
	 */
	public void lancerPartie() {
		int numeroVariante = utils.demanderInt("Quelle variante choisissez-vous?\n" + utils.listerVariantes(), 1, 2);
		controller.lancerPartie(numeroVariante);
	}

	/**
	 * lancement d'un tour de jeu
	 */
	public void effectuerTourDeJeu() {
		tourDeJeuPrecedent = tourDeJeu;		// on passe le tour de jeu en tour de jeu précédent
		tourDeJeu = new Thread(() -> {		// on créée un nouveau thread pour le nouveau tour de jeu
			joueurEnCours = partie.getJoueurEnCours();		// on récupère le joueur en cours
			joueurEnCours.addObserver(this);					// et on l'observe
			if (controller.authoriserAJouer(joueurEnCours)) {		// si le joueur peut jouer
				try {
					if (joueurEnCours.getClass() != Ordinateur.class) {		// on fait jouer le joueur humain s'il ne s'agit pas d'un ordinateur
						int action = faireJouerJoueur();
						controller.faireJouer(joueurEnCours, action);
					} else {													// sinon, on fait jouer l'ordinateur
						tourDeJeu.setName("Ordinateur");						// on indique que le thread est un thread de jeu de l'ordinateur
						controller.faireJouer((Ordinateur) joueurEnCours);
					}
				} catch (NoSuchElementException e) {			// dans le cas où l'on ne peut pas piocher et qu'il n'y a plus de carte dans le talon, on indique qu'il faut jouer une carte
					System.out.println(
							"Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
				}
			} else System.out.println(joueurEnCours.getNom() + " passe son tour.");		// si le joueur ne peut pas jouer, il passe son tour
		});
		tourDeJeu.start();		// on lance ce tour de jeu
		tourDeJeu.setName("cli");	// on indique qu'il s'agit d'un thread de la vue ligne de commande
		if(initialisation.isAlive()) initialisation.stop();		// on tue tous les autres threads de la vue ligne de commande, puisqu'ils ne sont plus nécessaires
		if(finDePartie != null && finDePartie.isAlive()) finDePartie.stop();
		if(tourDeJeuPrecedent != null && tourDeJeuPrecedent.getName() != "Ordinateur") tourDeJeuPrecedent.stop();
	}

	/**
	 * le joueur choisit le numéro de l'action à affectuer
	 * @return le numéro de l'action à effectuer
	 */
	public int faireJouerJoueur() {
		System.out.println(partie.afficherPartie());
		String message = "Indiquez le numéro de la carte que vous voulez jouer:\n" + "0: Piocher\n"; // 0 équivaut à piocher
		message += utils.listerCartes(joueurEnCours.getMain(), 1);		// les nombres de 1 à la taille de la main du joueur sont les cartes
		message += "Ou le numéro d'une action a effectuer:\n";
		message += joueurEnCours.getMain().size() + 1 + ": Annoncer carte\n";		// la taille de la main du joueur +1 est l'action d'annoncer
		message += utils.listerJoueursOrdinateurs(joueurEnCours.getMain().size() + 2, "Contrer ");	// la taille de la main du joueur + 2 jusqu'à la taille des cartes +2 +le nombre d'ordinateurs équivaut à contrer un ordinateur
		int max = joueurEnCours.getMain().size() + 1 + (partie.getJoueurs().size() - 1);

		return utils.demanderInt(message, 0, max);		// on demande au joueur de saisir un int
	}

	/**
	 * Affichage des scores de chaque joueur
	 * Le joueur peut choisir de rejouer
	 */
	public void afficherFinDePartie() {
		finDePartie = new Thread(() -> {		// on démarre un nouveau thread de fin de la partie
			System.out.println("Les scores sont: ");
			Iterator<Joueur> iterator = partie.getJoueursByScore().iterator();		// on affiche les scores
			while (iterator.hasNext()) {
				Joueur joueur = iterator.next();
				System.out.println(joueur.getNom() + ": " + joueur.getPoints() + " points");
			}
			String message = "Voulez-vous: \n" + "1: relancer une partie?\n"
					+ "2: relancer une partie en changeant les paramètres (votre nom ainsi que le nombre de joueurs?\n"
					+ "3: Arréter de jouer?";
			switch (utils.demanderInt(message, 1, 3)) {		// on donne plusieurs options au joueur
			case 1:
				lancerPartie();			// relancer une partie avec la même configuration
				break;
			case 2:
				initialiserPartie();		// relancer une partie avec une nouvelle configuration
				break;
			case 3:
				System.exit(0);			// quitter le jeu
				break;
			}
		});
		finDePartie.start();				// on démarre le thread
		if(initialisation.isAlive()) initialisation.stop();			// et on tue les autres threads de la vue ligne de commande
		if(tourDeJeu != null && tourDeJeu.getName() != "Ordinateur") tourDeJeu.stop();
		if(tourDeJeuPrecedent != null && tourDeJeuPrecedent.getName() != "Ordinateur") tourDeJeuPrecedent.stop();
	}

	/**
	 * Indique les actions des objets observables
	 * @param	observable
	 * @param	arg1
	 */
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Partie) {
			switch ((String) arg1) {
			case "commencerNouvellePartie":		// si une nouvelle partie commence, on lance un tour de jeu
				System.out.println("Une nouvelle partie commence: ");
				effectuerTourDeJeu();
				break;
			case "setJoueurEnCours":				// si on change de joueur en cours, on lance le tour de jeu du joueur suivant
				effectuerTourDeJeu();
				break;
			case "mettreAJourScores":			// si la partie se termine, on affiche les scores
				System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
				afficherFinDePartie();
				break;
			}
		} else if (observable instanceof Joueur) {		// on affiche les différents messages suite aux action d'un joueur
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
