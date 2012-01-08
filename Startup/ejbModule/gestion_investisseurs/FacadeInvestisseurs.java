package gestion_investisseurs;

import gestion_events.Startup;

import java.util.ArrayList;

public interface FacadeInvestisseurs {
	
	/* 
	 * Gestion des fondateurs
	 */
	public ArrayList<Fondateur> rechercherFondateur (String nom);
	public Fondateur rechercherFondateurParId (long id);
	public Fondateur ajouterFondateur (Fondateur f);
	public Fondateur ajouterFondateurStartup (Fondateur f, Startup s);
	
	/*
	 * Gestion des startups
	 */
	public Startup creerStartup(String nom, String activite, double capital, Fondateur f);
	public Startup rechercherStartupParId (long idStartup);
	
	/*
	 * Gestion des business Angel et club ami
	 */
	public ClubAmi monterClubAmi (BusinessAngel ba, String nomClub);
	public ClubAmi rechercherClubParId (long id);
	
	public void ajouterMembre (BusinessAngel ba, long idClub, boolean mandataire);
	public void supprimerMembre (BusinessAngel ba, ClubAmi ca);
	public Membre rechercherMembreParId (long idBA, long idClub);
	
	
}
