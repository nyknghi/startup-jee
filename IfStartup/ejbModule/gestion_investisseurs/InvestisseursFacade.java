package gestion_investisseurs;

import gestion_events.Startup;

import java.util.ArrayList;

public interface InvestisseursFacade {
	public Startup creerStartup(String nom, String activite, float capital, Fondateur f);
	public ArrayList<Fondateur> rechercherFondateur (String nom);
	public Fondateur ajouterFondateur (Fondateur f, Startup s);
}
