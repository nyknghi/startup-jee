package startup.ejb.session;

import java.util.ArrayList;

import startup.ejb.entity.*;

public interface InvestisseursFacade {
	public Startup creerStartup(String nom, String activite, float capital, Fondateur f);
	public ArrayList<Fondateur> rechercherFondateur (String nom);
	public Fondateur ajouterFondateur (Fondateur f, Startup s);
}
