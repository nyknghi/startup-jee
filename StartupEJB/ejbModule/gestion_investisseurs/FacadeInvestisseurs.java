package gestion_investisseurs;

import gestion_events.Etape;
import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_events.Startup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.Couple;

public interface FacadeInvestisseurs {
	
	/* 
	 * Gestion des fondateurs
	 */
	public Fondateur creerFondateur (String nom, String mail, String mdp);
	public Fondateur rechercherFondateurParId (long id);
	public ArrayList<Fondateur> rechercherFondateur (String nom);
	public Fondateur updateFondateur(Fondateur f, String nom, String mail, String mdp);
	public Fondateur updateFondateur(Fondateur f);
	
	public Fondateur findFondateurByEmail(String mail);	
	public List<Fondateur> findAllFondateur();
	
	public Couple<Fondateur,Startup> ajouterFondateurStartup (Fondateur f, Startup s, boolean isMandataire);

	/*
	 * Gestion des business Angel et club ami
	 */
	public BusinessAngel creerBA (String nom, String mail, String mdp);
	public BusinessAngel updateBA (BusinessAngel ba, String nom, String mail, String mdp);
	public BusinessAngel updateBA(BusinessAngel ba);
	public BusinessAngel rechercherBAParId (long id);
	public List<BusinessAngel> rechercherBAParNom (String nom);
	public BusinessAngel rechercherBAParMail(String mail);
	public List<BusinessAngel> findAllBusinessAngel();
	
	public ClubAmi monterClubAmi (BusinessAngel ba, String nomClub);
	public ClubAmi updateClubAmi (ClubAmi ca, String nomClub);
	public ClubAmi updateClubAmi (ClubAmi ca);
	public ClubAmi rechercherClubParId (long id);
	public List<ClubAmi> rechercherClubParNom (String nom);
	public List<ClubAmi> findAllClub();
	
	public Couple<ClubAmi, Startup> mettreEnPartenaire (ClubAmi ca, Startup s);
	
	public Couple<ClubAmi, BusinessAngel> ajouterMembre (BusinessAngel ba, long idClub, boolean mandataire);
	public Couple<ClubAmi, BusinessAngel> supprimerMembre (BusinessAngel ba, ClubAmi ca);
	public Membre rechercherMembreParId (long idBA, long idClub);
	public List<BusinessAngel> listerMembres (ClubAmi ca);
	
	/*
	 * Gestion des investisseurs
	 */
	public Investisseur creerInvestisseur (String nom, String mail, String mdp);
	public Investisseur updateInvestisseur (Investisseur inv, String nom, String mail, String mdp);
	public Investisseur updateInvestisseur (Investisseur inv);
	public Investisseur rechercherInvestisseurParId (long id);
	public List<Investisseur> rechercherInvestisseurParNom (String nom);
	public Investisseur rechercherInvestisseurParMail (String mail);
	public List<Investisseur> findAllInvestisseur();
	//public List<Investisseur> rechercherInvestisseurParGroupe (GroupeInvestisseurs groupe);
	
	/*
	 * Gestion des groupes d'investisseurs
	 */
	public Couple<GroupeInvestisseurs, Investisseur> monterGroupe(Investisseur inv, String nomGroupe);
	public GroupeInvestisseurs updateGroupeInvestisseurs (GroupeInvestisseurs groupe, String nomGroupe);
	public GroupeInvestisseurs updateGroupeInvestisseurs (GroupeInvestisseurs groupe);
	public GroupeInvestisseurs rechercherGroupeParId (long id);
	public List<GroupeInvestisseurs> rechercherGroupeParNom (String nomGroupe);
	public List<GroupeInvestisseurs> findAllGroupe();
	
	public Couple<GroupeInvestisseurs, Investisseur> adhererGroupe (GroupeInvestisseurs groupe, Investisseur inv, boolean isLeader);
	public Couple<GroupeInvestisseurs, Investisseur> quitterGroupe (GroupeInvestisseurs groupe, Investisseur inv);
	
	public List<AbstraitInvestisseur> findAllInvestisseurs();
	
	/*
	 * Gestion des levees de fonds
	 */
	public Couple<AbstraitInvestisseur,LeveeDeFonds> organiserLeveeFonds (Startup s, AbstraitInvestisseur ainv, double cible); 
	public LeveeDeFonds modifierEtape (LeveeDeFonds levee, Etape etape);
	public LeveeDeFonds annulerLeveeFonds (LeveeDeFonds levee);
	public Couple<LeveeDeFonds, Inscription> inscrireLevee (AbstraitInvestisseur ainv, LeveeDeFonds levee);
	
	/*
	 * Gestion des accounts
	 */
	public HashMap<String, String> listeAccountsFondateurs();
	public HashMap<String, String> listeAccountsBA();
	public HashMap<String, String> listeAccountsInvestisseurs();
	
	public void closeEM();

}
