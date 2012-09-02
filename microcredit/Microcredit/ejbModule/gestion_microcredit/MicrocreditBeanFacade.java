package gestion_microcredit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public interface MicrocreditBeanFacade {
	
	 //CRUD participations
    public Couple<Projet, Credit> participation(Projet p, Utilisateur u, double m);
    public Credit updateCredit(long idStartup, long idInv, double m);
    public Credit updateParticipation(Credit c);
    public List<Credit> findCredit(Utilisateur utili);
    public Credit findCredit(long idCredit, long idInv);
    
    //CRUD projet
    public Couple<Projet, Entrepreneur> startup(String nom, String activite, Entrepreneur e);
    public Projet updateStartup(Projet p, String nom, String a);
    public Projet updateStartup(Projet p);
    public List<Projet> findProjetByName(String n);
    public Projet findProjetById(long id);
    public List<Projet> findStartupByActivity(String a);
    
    public List<Projet> findAllProjet();
    /* 
	 * Gestion des entrepreneurs
	 */
	public Entrepreneur creerEntrepreneur (String nom);
	public Entrepreneur rechercherEntrepreneurParId (long id);
	public ArrayList<Entrepreneur> rechercherEntrepreneur (String nom);
	public Entrepreneur updateFondateur(Entrepreneur e, String nom);
	public Entrepreneur updateEntrepreneur(Entrepreneur e);
	
	public Couple<Entrepreneur,Projet> ajouterFondateurStartup (Entrepreneur e, Projet p);
	/*
	 * Gestion des investisseurs
	 */
	public Preteur creerPreteur (String nom, String login, String mdp);
	public Preteur updatePreteur (Preteur preteur, String nom, String login, String mdp);
	public Preteur updatePreteur (Preteur preteur);
	public Preteur rechercherPreteurParId (long id);
	public List<Preteur> rechercherPreteurParNom (String nom);
	/*
	 * Gestion des accounts
	 */
	public HashMap<String, String> listeAccountsEntrepreneur();
	public HashMap<String, String> listeAccountsPreteur();
	
	public void closeEM();

}
