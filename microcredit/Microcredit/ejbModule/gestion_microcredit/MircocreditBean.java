package gestion_microcredit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class MircocreditBean
 */
@Stateless
@TransactionManagement (TransactionManagementType.CONTAINER)
public class MircocreditBean implements MircocreditBeanRemote, MircocreditBeanLocal {
	@PersistenceContext
    EntityManager em;
    
    
    @PostConstruct
	public void init(){
		System.out.println("Calling init method");
		try{
			Context ctx = new InitialContext();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}    
	}
    
    //CRUD Credit
    public Couple<Projet, Credit> Credit(Projet p, Utilisateur u, double m) {
    	Credit c = new Credit(p, u, m);
        if(u instanceof Preteur){
            p.addPreteur((Preteur)u);
        }
        em.persist(c);
        p.addCredit(c);
        u.addCredit(c);
        em.merge(u);
        return new Couple<Projet, Credit>(em.merge(p), em.merge(c));
    }
    
	
    
    public Credit updateCredit(long idProjet, long idUtili, double m) {
        Credit p = findCredit(idProjet, idUtili);
        p.setMontant(m);
        return em.merge(p);
    }
    
    public Credit updateCredit(Credit c){
    	return em.merge(c);
    }
    
    @SuppressWarnings("unchecked")
	public List<Credit> findCredit(Utilisateur utili) {
        Query query = null;
        if(utili instanceof Preteur){
            query = em.createQuery("select p.credit from Preteur as p where p.nom= :nom");
        
        query.setParameter("nom", utili.getNom());
        }
        return (List<Credit>) query.getResultList();
        
    }
    
    public Credit findCredit(long idStartup, long idUtili) {
        Query query = em.createQuery("select p from Credit as p where p.startup.idStartup= :startup and p.investisseur.idInvestisseur= :inv");
        query.setParameter("startup", idStartup);
        query.setParameter("inv", idUtili);
        return (Credit) query.getSingleResult();
    }

    
    //CRUD Projet
    public Couple<Projet, Entrepreneur> projet(String nom, String butProjet, Entrepreneur e) {
    	
    	Projet p = new Projet(nom,butProjet, e);
        em.persist(p);
        e.setProjet(p);
        return new Couple<Projet, Entrepreneur>(em.merge(p), em.merge(e));
    }
    
    public Projet updateProjet(Projet p, String nom, String a) {
        p = findProjetById(p.getIdProjet());
        if(nom!=null){
            p.setNomProjet(nom);
            if(a!=null){
                p.setbutProjet(a);
            }
        }
       return em.merge(p);
    }
    
    public Projet updateProjet(Projet p){
    	return em.merge(p);
    }

    @SuppressWarnings("unchecked")
	public List<Projet> findProjetByName(String n) {
        Query query = em.createQuery("SELECT p FROM Projet as p WHERE p.nomProjet= :nom");
        query.setParameter("nom", n);
        return (List<Projet>) query.getResultList();
    }

    public Projet findProjetById(long id){
    	return em.find(Projet.class, id);
    }
    
    @SuppressWarnings("unchecked")
	public List<Projet> findSProjetByActivity(String a) {
        Query query = em.createQuery("select p from Projet as p where p.butProjet= :butProjet");
        query.setParameter("activite", a);
        return (List<Projet>) query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Projet> findAllProjet(){
    	Query query = em.createQuery("select p from Projet as p");
        return (List<Projet>) query.getResultList();
    }
    

    
    //Business methods
  
	public double calculCapital(Projet p) {
    	p = this.findProjetById(p.getIdProjet());
    	/*System.out.println(s.getIdStartup());
    	Query query = em.createQuery("SELECT p FROM Participation AS p WHERE p.startup = :idStartup");
    	query.setParameter("idStartup", s.getIdStartup());
    	List<Participation> parts = (List<Participation>)query.getResultList();
    	*/
        Iterator<Credit> it = p.getCredit().iterator();
        double res = 0.0;
        while(it.hasNext()){
            Credit c = (Credit)it.next();
            res += c.getMontant();
        }
        p.setCapital(res);
        em.merge(p);
        System.out.println(res);
        return res;
    }

   
    
	@Override
	public Entrepreneur creerEntrepreneur(String nom) {
		Entrepreneur e = new Entrepreneur (nom);
		em.persist(e);
		return e;
	}

	@Override
	public Entrepreneur updateEntrepreneur(Entrepreneur e, String nom) {
		e = this.rechercherEntrepreneurParId(e.getIdEntrepreneur());
		e.setNom(nom);
		
		return em.merge(e);
	}
	
	@Override
	public Entrepreneur updateEntrepreneur(Entrepreneur e) {
		return em.merge(e);
	}
	@Override
	public Couple<Entrepreneur, Projet> ajouterEntrepreneurProjet(Entrepreneur e, Projet p) {
		e= this.rechercherEntrepreneurParId(e.getIdEntrepreneur());
		
		p.addEntrepreneur(e);
		e.setProjet(p);
		
		return new Couple<Entrepreneur, Projet>(em.merge(e), em.merge(p));
	}
	
	public Entrepreneur rechercherEntrepreneurParId (long id){
		return em.find(Entrepreneur.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Entrepreneur> rechercherEntrepreneur(String nom) {
		Query query = em.createNamedQuery("findEntrepreneurByName");
		query.setParameter("nom", nom);
		return (ArrayList<Entrepreneur>) query.getResultList();
	}
	

	public Preteur creerPreteur(String nom, String login, String mdp) {
		Preteur preteur = new Preteur(nom, login, mdp);
		em.persist(preteur);
		return preteur;
	}

	public Preteur updatePreteur(Preteur preteur, String nom, String login, String mdp) {
		preteur = this.rechercherPreteurParId(preteur.getIdUtilisateur());
		preteur.setNom(nom);
		preteur.setLogin(login);
		preteur.setMdp(mdp);
		return em.merge(preteur);
	}

	public Preteur updatePreteur(Preteur preteur) {
		return em.merge(preteur);
	}

	public Preteur rechercherPreteurParId(long id) {
		return em.find(Preteur.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Preteur> rechercherPreteurParNom(String nom) {
		Query query = em.createQuery("SELECT p FROM Preteur as e WHERE e.nom = :nom");
		query.setParameter("nom", nom);
		return (List<Preteur>) query.getResultList();
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Investisseur> rechercherInvestisseurParGroupe(GroupeInvestisseurs groupe) {
		groupe = this.rechercherGroupeParId(groupe.getIdInvestisseur());
		Query query = em.createQuery("SELECT i FROM Investisseur as i WHERE i.groupe = :idgroupe");
		query.setParameter("idgroupe", groupe.getIdInvestisseur());
		return (List<Investisseur>) query.getResultList();
	}
	*/

	
	@SuppressWarnings("unchecked")
	/**
	 * La methode recupere la liste des accounts de tous les investisseurs.
	 * Le resultat sera stocke dans un HashMap dont la cle est l'email et la valeur est le mdp 
	 */
	public HashMap<String, String> listeAccountsEntrepreneur(){
		HashMap<String, String> res = new HashMap<String, String>();
		Query query = em.createQuery("SELECT f FROM Fondateur as f");
		List<Entrepreneur> entrepreneurs = (List<Entrepreneur>)query.getResultList();
		/*for (int i=0; i<entrepreneur.size(); i++){
			Entrepreneur e = Entrepreneur.get(i);
			res.put(e.getLogin(), e.getMdp());
		}*/
		
		return res;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> listeAccountsPreteur() {
		HashMap<String, String> res = new HashMap<String, String>();
		Query query = em.createQuery("SELECT inv FROM Investisseur as inv");
		List<Preteur> pret = (List<Preteur>)query.getResultList();
		for (int i=0; i<pret.size(); i++){
			Preteur preteur = pret.get(i);
			res.put(preteur.getLogin(), preteur.getMdp());
		}
		
		return res;
	}
	
	@PreDestroy()
	public void cleanUp(){
		System.out.println("Calling cleanup method");
	}	
	
	public void closeEM(){
		em.close();
	}
   

}
