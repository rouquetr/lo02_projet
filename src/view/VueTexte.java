package view;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;

public class VueTexte implements Observer, Runnable {
	
	private Partie partie = Partie.getInstance();
	
	private PartieController controller;
	
	private Joueur joueurEnCours;
	
	private LigneDeCommandeUtils utils = new LigneDeCommandeUtils();

	public VueTexte(PartieController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		partie.addObserver(this);
		t.start();
	}
	
	public void run() {
		initialiserPartie();
	}
	
	public void initialiserPartie() {
		String nomJoueur = utils.demanderString("Saisissez votre nom de joueur");
		int nombreJoueurs = utils.demanderInt("Combien de joueurs doit comporter la partie?", Partie.MINJOUEUR, Partie.MAXJOUEUR);
		
		controller.initialiserPartie(nombreJoueurs, nomJoueur);
	}
	
	public void lancerPartie() {
		int numeroVariante = utils.demanderInt("Quelle variante choisissez-vous?\n" + utils.listerVariantes(), 1, 2);
		controller.lancerPartie(numeroVariante);
	}
	
	public void effectuerTourDeJeu() {
		while(true) {
			joueurEnCours = partie.getJoueurEnCours();
			joueurEnCours.addObserver(this);
			if(joueurEnCours.peutJouer()) {
				try {
						if (joueurEnCours.getClass() != Ordinateur.class)
							controller.faireJouer(joueurEnCours, faireJouerJoueur());
						else
							controller.faireJouer((Ordinateur) joueurEnCours);
				} catch (NoSuchElementException e) {
					System.out.println("Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
				}
			} 	else {
				System.out.println(joueurEnCours.getNom() + " passe son tour.");
				joueurEnCours.setPeutJouer(true);
			}
		}
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
		String message = "Voulez-vous: \n" 
						 + "1: relancer une partie?\n"
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
		if(observable instanceof Partie && (String)arg1 == "ajouterJoueurs")
			lancerPartie();
		if(observable instanceof Partie && (String)arg1 == "setJoueurEnCours")
			effectuerTourDeJeu();
		if(observable instanceof Joueur && (String) arg1 == "piocher") {
			System.out.println(joueurEnCours.getNom() + " a pioché " + joueurEnCours.getMain().getLast().afficherCarteAvecDeterminant());
			partie.setJoueurEnCours(partie.findJoueurSuivant());
		}
		if(observable instanceof Joueur && (String) arg1 == "jouerCarte") {
			System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
			if(partie.getTalon().getLast().getActionMessage() != "") System.out.println(partie.getTalon().getLast().getActionMessage());
			partie.setJoueurEnCours(partie.findJoueurSuivant());
		}
		if(observable instanceof Joueur && (String) arg1 == "jouerCarteErreur")
			System.out.println(joueurEnCours.getNom() + " ne peut pas jouer cette carte");
		if(observable instanceof Joueur && (String) arg1 == "aAnnonceCarteErreur")
			System.out.println(joueurEnCours.getNom() + " ne peut pas annoncer Carte");
		if(observable instanceof Joueur && (String) arg1 == "aAnnonceCarte")
			System.out.println(joueurEnCours.getNom() + " a annoncé Carte");
		if(observable instanceof Joueur && (String) arg1 == "partieTerminee") {
			controller.terminerPartie();
			afficherFinDePartie();
		}
	}
}
