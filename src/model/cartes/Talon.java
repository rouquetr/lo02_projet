package model.cartes;

public class Talon extends Paquet {
	
	private static Talon uniqueInstance;
	
	private Talon() {
		super();
	}
	
	public static Talon getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Talon();
		return uniqueInstance;
	}

}
