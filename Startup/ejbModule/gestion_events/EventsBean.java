/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.*;

import java.util.Date;
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

import util.Couple;

@Stateless
@TransactionManagement (TransactionManagementType.CONTAINER)
public class EventsBean implements EventsBeanLocal, EventsBeanRemote {
    
    @PersistenceContext
    EntityManager em;
    LocalInvestisseurs localInv;
    
    @PostConstruct
	public void init(){
		System.out.println("Calling init method");
		try{
			Context ctx = new InitialContext();
			localInv = (LocalInvestisseurs) ctx.lookup("BeanInvestisseurs/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}    
	}
    
    //CRUD participations
    public Couple<Startup, Participation> participation(Startup s, AbstraitInvestisseur i, double m) {
        Participation p = new Participation(s, i, m);
        if(i instanceof Fondateur){
            s.addFondateur((Fondateur)i);
        }
        em.persist(p);
        s.addParticipation(p);
        i.addParticipation(p);
        em.merge(i);
        return new Couple<Startup, Participation>(em.merge(s), em.merge(p));
    }
    
	@Override
	public Couple<LeveeDeFonds, Participation> participation(LeveeDeFonds levee, AbstraitInvestisseur i, double m) {
		levee = this.findLeveeDeFonds(levee.getIdLevee());
		Startup s = this.findStartupById(levee.getStartup().getIdStartup());
		if (levee.getEtape().equals(Etape.ENGAGEMENT)){
			Participation p = new Participation(levee, i, m);
			em.persist(p);
			levee.addParticipation(p);
			i.addParticipation(p);
			s.addParticipation(p);
			em.merge(s);
			em.merge(i);
			return new Couple<LeveeDeFonds, Participation>(em.merge(levee), em.merge(p));
		} else {
			System.out.println("Err_Etape_LeveeFonds: La levee de fonds ne prend encore des participations");
			return null;
		}
	}
    
    public Participation updateParticipation(long idStartup, long idInv, double m) {
        Participation p = findParticipation(idStartup, idInv);
        p.setMontant(m);
        return em.merge(p);
    }
    
    public Participation updateParticipation(Participation p){
    	return em.merge(p);
    }
    
    @SuppressWarnings("unchecked")
	public List<Participation> findParticipation(AbstraitInvestisseur inv) {
        Query query = null;
        if(inv instanceof Fondateur){
            query = em.createQuery("select f.participations from Fondateur as f where f.nom= :nom");
        }else if(inv instanceof GroupeInvestisseurs){
            query = em.createQuery("select g.participations from GroupeInvestisseurs as g where g.nom= :nom");
        }else if(inv instanceof BusinessAngel){
            query = em.createQuery("select b.participations from BusinessAngel as b where b.nom= :nom");
        }
        query.setParameter("nom", inv.getNom());
        return (List<Participation>) query.getResultList();
    }
    
    public Participation findParticipation(long idStartup, long idInv) {
        Query query = em.createQuery("select p from Participation as p where p.startup.idStartup= :startup and p.investisseur.idInvestisseur= :inv");
        query.setParameter("startup", idStartup);
        query.setParameter("inv", idInv);
        return (Participation) query.getSingleResult();
    }

    
    //CRUD startup
    public Couple<Startup, Fondateur> startup(String nom, String activite, Fondateur f) {
    	f = localInv.rechercherFondateurParId(f.getIdInvestisseur());
        Startup s = new Startup(nom, activite, f);
        em.persist(s);
        f.setStartup(s);
        return new Couple<Startup, Fondateur>(em.merge(s), em.merge(f));
    }
    
    public Startup updateStartup(Startup s, String nom, String a) {
        s = findStartupById(s.getIdStartup());
        if(nom!=null){
            s.setNomStartup(nom);
            if(a!=null){
                s.setActivite(a);
            }
        }
       return em.merge(s);
    }
    
    public Startup updateStartup(Startup s){
    	return em.merge(s);
    }

    @SuppressWarnings("unchecked")
	public List<Startup> findStartupByName(String n) {
        Query query = em.createQuery("SELECT s FROM Startup as s WHERE s.nomStartup= :nom");
        query.setParameter("nom", n);
        return (List<Startup>) query.getResultList();
    }

    public Startup findStartupById(long id){
    	return em.find(Startup.class, id);
    }
    
    @SuppressWarnings("unchecked")
	public List<Startup> findStartupByActivity(String a) {
        Query query = em.createQuery("select s from Startup as s where s.activite= :activite");
        query.setParameter("activite", a);
        return (List<Startup>) query.getResultList();
    }
    
    
    //CRUD levee de fonds
    public Couple<Startup,LeveeDeFonds> leveeDeFonds(Date date, double montant, AbstraitInvestisseur o, Startup s) {
        LeveeDeFonds levee = new LeveeDeFonds(date, montant, o, s);
        System.out.println("Etape " + levee.getEtape());
        em.persist(levee);
        s.getLevee().add(levee);
        o.getLeveeDeFonds().add(levee);
        em.merge(o);
        return new Couple<Startup, LeveeDeFonds>(em.merge(s), em.merge(levee));
    }

    public LeveeDeFonds updateLeveeDeFonds(long id, Date date, Etape e) {
        LeveeDeFonds levee = findLeveeDeFonds(id);
        if(date!=null){
            levee.setDate_levee(date);
            if(e!=null){
                levee.setEtape(e);
            }
        }
        return em.merge(levee);
    }

    public LeveeDeFonds updateLeveeDeFonds(LeveeDeFonds levee){
    	return em.merge(levee);
    }
    
    public LeveeDeFonds findLeveeDeFonds(long id) {
        return em.find(LeveeDeFonds.class, id);
    }
    
    //Business methods
    public void distribuerDividende(LeveeDeFonds l) {
    	l = this.findLeveeDeFonds(l.getIdLevee());
        Startup st = l.getStartup();
        Iterator<Participation> it = st.getParticipations().iterator();
        while(it.hasNext()){
            Participation p = (Participation) it.next();
            double div = (p.getMontant()/calculCapital(st))*totalParticipations(l);
            p.setMontant(div);
            em.merge(p);
        }
        em.merge(st);
    }

    public double totalParticipations(LeveeDeFonds l) {
        Iterator<Participation> it = l.getParticipations().iterator();
        double total = 0.0;
        while(it.hasNext()){
            Participation temp = (Participation)it.next();
            total += temp.getMontant();
        }
        return total;
    }

	public double calculCapital(Startup s) {
    	s = this.findStartupById(s.getIdStartup());
    	/*System.out.println(s.getIdStartup());
    	Query query = em.createQuery("SELECT p FROM Participation AS p WHERE p.startup = :idStartup");
    	query.setParameter("idStartup", s.getIdStartup());
    	List<Participation> parts = (List<Participation>)query.getResultList();
    	*/
        Iterator<Participation> it = s.getParticipations().iterator();
        double res = 0.0;
        while(it.hasNext()){
            Participation p = (Participation)it.next();
            res += p.getMontant();
        }
        s.setCapital(res);
        em.merge(s);
        System.out.println(res);
        return res;
    }

    public double postValue(Startup s, LeveeDeFonds l) {
    	s = this.findStartupById(s.getIdStartup());
    	l = this.findLeveeDeFonds(l.getIdLevee());
    	double res = totalParticipations(l)+calculCapital(s);
    	s.setPostValue(res);
    	em.merge(s);
    	return res;
    }
    
    @PreDestroy()
	public void cleanUp(){
		System.out.println("Calling cleanup method");
	}
}
