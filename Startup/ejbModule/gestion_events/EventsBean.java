/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.GroupeInvestisseurs;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import util.Couple;

/**
 *
 * @author UTILISATEUR
 */
@Stateless
@TransactionManagement (TransactionManagementType.CONTAINER)
public class EventsBean implements EventsBeanLocal, EventsBeanRemote {
    
    @PersistenceContext(unitName="SampleUnit")
    EntityManager em;

    //CRUD participations
    public Participation participation(Startup s, AbstraitInvestisseur i, double d) {
        Participation p = new Participation(s, i, d);
        if(i instanceof Fondateur){
            s.addFondateur((Fondateur)i);
        }
        s.addParticipation(p);
        i.addParticipation(p);
        em.persist(p);
        em.merge(s);
        em.merge(i);
        return p;
    }
    
    public Participation updateParticipation(String s, String i, double m) {
        Participation p = findParticipation(s, i);
        p.setMontant(m);
        return em.merge(p);
    }
    
    public Participation updateParticipation(Participation p){
    	return em.merge(p);
    }
    public List<Participation> findParticipation(AbstraitInvestisseur inv) {
        Query query = null;
        if(inv instanceof Fondateur){
            query = em.createQuery("select f.participations from Fondateur f where f.nom= :nom");
        }else if(inv instanceof GroupeInvestisseurs){
            query = em.createQuery("select g.participations from GroupeInvestisseurs g where g.nom= :nom");
        }else if(inv instanceof BusinessAngel){
            query = em.createQuery("select b.participations from BusinessAngel b where b.nom= :nom");
        }
        query.setParameter("nom", inv.getNom());
        return (List<Participation>) query.getResultList();
    }
    
    public Participation findParticipation(String s, String i) {
        Query query = em.createQuery("select p from Participation p where p.startup.idStartup= :startup and p.investisseur.idInvestisseur= :inv");
        return (Participation) query.getSingleResult();
    }

    
    //CRUD startup
    public Couple<Startup, Fondateur> startup(String nom, String activite, Fondateur f) {
        Startup s = new Startup(nom, activite, f);
        em.persist(s);
        f.setStartup(s);
        return new Couple<Startup, Fondateur>(em.merge(s), em.merge(f));
    }
    
    public Startup updateStartup(String n, String nouv, String a) {
        Startup s = findStartupByName(n);
        if(nouv!=null){
            s.setNomStartup(nouv);
            if(a!=null){
                s.setActivite(a);
            }
        }
       return em.merge(s);
    }
    
    public Startup updateStartup(Startup s){
    	return em.merge(s);
    }

    public Startup findStartupByName(String n) {
        Query query = em.createQuery("select s from Startup s where s.nomStartup= :nom");
        query.setParameter("nom", n);
        return (Startup) query.getSingleResult();
    }

    public Startup findStartupById(long id){
    	return em.find(Startup.class, id);
    }
    
    public List<Startup> findStartupByActivity(String a) {
        Query query = em.createQuery("select s from Startup s where s.activite= :activite");
        query.setParameter("activite", a);
        return (List<Startup>) query.getResultList();
    }
    
    
    //CRUD levee de fonds
    public LeveeDeFonds leveeDeFonds(String d, double m, AbstraitInvestisseur o) {
        LeveeDeFonds f = new LeveeDeFonds(d, m, o);
        em.persist(f);
        return f;
    }

    public LeveeDeFonds updateLeveeDeFonds(long id, String date, Etape e) {
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
        Startup st = l.getStartup();
        Iterator it = st.getParticipations().iterator();
        while(it.hasNext()){
            Participation p = (Participation) it.next();
            double div = (p.getMontant()/calculCapital(st))*totalParticipations(l);
            p.setMontant(div);
            em.merge(p);
        }
        em.merge(st);
    }

    public double totalParticipations(LeveeDeFonds l) {
        Iterator it = l.getParticipations().iterator();
        double total = 0.0;
        while(it.hasNext()){
            Participation temp = (Participation)it.next();
            total += temp.getMontant();
        }
        return total;
    }

    public double calculCapital(Startup s) {
        Iterator it = s.getParticipations().iterator();
        double res = 0.0;
        while(it.hasNext()){
            Participation p = (Participation)it.next();
            res += p.getMontant();
        }
        return res;
    }

    public Double postValue(Startup s, LeveeDeFonds l) {
        return totalParticipations(l)+calculCapital(s);
    }
}
