package view;

import java.util.Iterator;
import java.util.NoSuchElementException;

import controller.PartieController;
import model.joueurs.Joueur;
import model.joueurs.Ordinateur;
import model.joueurs.Partie;


public class VueLigneDeCommande {
	
	private Partie partie = Partie.getInstance();
	
	private PartieController controller;
	
	private Joueur joueurEnCours;
	
	private LigneDeCommandeUtils utils = new LigneDeCommandeUtils();
	
	public VueLigneDeCommande(PartieController controller) {
		this.controller = controller;
	}
	
	public void initialiserPartie() {
		String nomJoueur = utils.demanderString("Saisissez votre nom de joueur");
		int nombreJoueurs = utils.demanderInt("Combien de joueurs doit comporter la partie?", Partie.MINJOUEUR, Partie.MAXJOUEUR);
		
		controller.initialiserPartie(nombreJoueurs, nomJoueur);
	}
	
	public void lancerPartie() {
		int numeroVariante = utils.demanderInt("Quelle variante choisissez-vous?\n" + utils.listerVariantes(), 1, 2);
		controller.lancerPartie(numeroVariante);
		effectuerTourDeJeu();
	}
	
	public void effectuerTourDeJeu() {
		while(true) {
			joueurEnCours = partie.getJoueurEnCours();
			tourNormal();
		}
	}
	
	public void tourNormal() {
		if(joueurEnCours.peutJouer())
			try {
				afficherActionEffectuee(
						(joueurEnCours.getClass() != Ordinateur.class)
							? controller.faireJouer(joueurEnCours, faireJouerJoueur())
							: controller.faireJouer((Ordinateur) joueurEnCours)
						);		
			} catch (NoSuchElementException e) {
				System.out.println("Il n'y a plus de carte dans le paquet et une seule carte dans le talon, vous ne pouvez donc pas piocher");
			}
		else {
			System.out.println(joueurEnCours.getNom() + " passe son tour.");
			joueurEnCours.setPeutJouer(true);
		}
		if (controller.verifierSiPartieTerminee()) afficherFinDePartie();
	}
	
	public void afficherFinDePartie() {
		afficherScores();
		String message = "Voulez-vous: \n" 
						 + "1: relancer une partie?\n"
						 + "2: relancer une partie en changeant les param�tres (votre nom ainsi que le nombre de joueurs?\n"
						 + "3: Arr�ter de jouer?";
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
	
	public void afficherScores() {
		System.out.println("Les scores sont: ");
		Iterator<Joueur> iterator = partie.getJoueursByScore().iterator();
		while (iterator.hasNext()) {
			Joueur joueur = iterator.next();
			System.out.println(joueur.getNom() + ": " + joueur.getPoints() + " points");
		}
	}
	
	public int faireJouerJoueur() {
		System.out.println(partie.afficherPartie());
		String message = "Indiquez le num�ro de la carte que vous voulez jouer:\n" + "0: Piocher\n";
		message += utils.listerCartes(joueurEnCours.getMain(), 1);
		message += "Ou le num�ro d'une action a effectuer:\n";
		message += joueurEnCours.getMain().size() + 1 + ": Annoncer carte\n";
		message += utils.listerJoueursOrdinateurs(joueurEnCours.getMain().size() + 2, "Contrer ");
		int max = joueurEnCours.getMain().size() + 1 + (partie.getJoueurs().size() - 1);
		
		return utils.demanderInt(message, 0, max);
	}
	
	public void afficherActionEffectuee(int action) {
		switch (action) {
		case 0:
			System.out.println(joueurEnCours.getNom() + " a pioché " + joueurEnCours.getMain().getLast().afficherCarteAvecDeterminant());
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			break;
		case 1:
			System.out.println(joueurEnCours.getNom() + " a joué " + partie.getTalon().afficherTalon());
			if(partie.getTalon().getLast().getActionMessage() != "") System.out.println(partie.getTalon().getLast().getActionMessage());
			partie.setJoueurEnCours(partie.findJoueurSuivant());
			break;
		case 2:
			System.out.println(joueurEnCours.getNom() + " ne peut pas jouer cette carte");
			break;
		case 3:
			System.out.println(joueurEnCours.getNom() + " ne peut pas annoncer Carte");
			break;
		case 4:
			System.out.println(joueurEnCours.getNom() + " a annoncé Carte");
			break;
		default:
			break;
		}
	}
}