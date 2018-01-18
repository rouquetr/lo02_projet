package model.actions;

/**
 * Une interface pour implémenter le design pattern des différents effets que les cartes peuvent avoir
 * @author Rouquet Raphael - Mannan Ismail
 */

public interface Action {
	/**
	 * Action en ligne de commande
	 */
	public void actionCli();
	/**
	 * Action graphique
	 */
	public void actionGui();
	/**
	 * message retourné par le système
	 */
	public String message();
}
