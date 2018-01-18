package model.actions;

/**
 * Représente une carte n'ayant pas d'effet sur la partie 
 * @author Rouquet Raphael - Mannan Ismail
 *
 */
public class ActionNeutre implements Action{
	
	
	private String message = "";
	
	
	public void actionCli() {
	}
	
	public void actionGui() {
	}
	
	public String message() {
		return message;
	}
	
}
