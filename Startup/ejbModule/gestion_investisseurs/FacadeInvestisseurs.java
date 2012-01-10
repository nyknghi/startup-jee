package gestion_investisseurs;

import gestion_events.Startup;

import java.util.ArrayList;
import java.util.List;

public interface FacadeInvestisseurs {
	
	/* 
	 * Gestion des fondateurs
	 */
	public Fondateur creerFondateur (String nom, String mail, String mdp);
	public Fondateur rechercherFondateurParId (long id);
	public ArrayList<Fondateur> rechercherFondateur (String nom);
	public Fondateur updateFondateur(Fondateur f, String nom, String mail, String mdp);
	
	public Fondateur ajouterFondateurStartup (Fondateur f, Startup s, boolean isMandataire);
	
	/*
	 * Gestion des business Angel et club ami
	 */
	public BusinessAngel creerBA (String nom, String mail, String mdp);
	public BusinessAngel updateBA (BusinessAngel ba, String nom, String mail, String mdp);
	public BusinessAngel rechercherBAParId (long id);
	public List<BusinessAngel> rechercherBAParNom (String nom);
	
	public ClubAmi monterClubAmi (BusinessAngel ba, String nomClub);
	public ClubAmi updateClubAmi (ClubAmi ca, String nomClub);
	public ClubAmi rechercherClubParId (long id);
	
	public void ajouterMembre (BusinessAngel ba, long idClub, boolean mandataire);
	public void supprimerMembre (BusinessAngel ba, ClubAmi ca);
	public Membre rechercherMembreParId (long idBA, long idClub);
	
	/*
	 * Gestion des groupes d'investisseurs
	 */
	public GroupeInvestisseurs monterGroupe(Investisseur inv, String nomGroupe);
	public GroupeInvestisseurs updateGroupeInvestisseurs (GroupeInvestisseurs groupe, String nomGroupe);
	public GroupeInvestisseurs rechercherGroupeParId (long id);
	public List<GroupeInvestisseurs> rechercherGroupeParNom (String nomGroupe);
	
	public void adhererGroupe (GroupeInvestisseurs groupe, Investisseur inv);
	public void quitterGroupe (GroupeInvestisseurs groupe, Investisseur inv);
	
	public void closeEM();
	public String afficherText(String t);
}
