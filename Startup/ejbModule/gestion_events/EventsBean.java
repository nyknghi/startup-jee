/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.GroupeInvestisseurs;
import gestion_investisseurs.Organisateur;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author UTILISATEUR
 */
@Stateless
public class EventsBean implements EventsBeanLocal, EventsBeanRemote {
    
    @PersistenceContext
    EntityManager em;

    public Participation participation(Startup s, AbstraitInvestisseur i, double d) {
        Participation p = new Participation(s, i, d);
        em.persist(p);
        return p;
    }

    public Startup startup(String nom, String activite, Fondateur f) {
        Startup s = new Startup(nom, activite, f);
        em.persist(s);
        return s;
    }

    public void distribuerDividende(LeveeDeFonds l) {
        Startup st;
        int startupId;
        if(l.getOrg() instanceof Fondateur){
            startupId = ((Fondateur)l.getOrg()).getStartup().getIdStartup();
        }else{
            startupId = ((ClubAmi)l.getOrg()).getStartup().getIdStartup();
        }
        st = em.find(Startup.class, startupId);
        Query query = em.createQuery("select distinct par.investisseur from Startup s, in s.participations as par"
                + "where s.idStartup= :idstartup");//liste des investisseurs de la startup
        query.setParameter("idstartup", st.getIdStartup());
        LinkedList<AbstraitInvestisseur> list = (LinkedList<AbstraitInvestisseur>) query.getResultList();
        HashMap<AbstraitInvestisseur, Double> sums = new HashMap<AbstraitInvestisseur, Double>();
        for(AbstraitInvestisseur inv: list){
            sums.put(inv, (sousTotal(inv.getIdInvestisseur(), st.getIdStartup())/calculCapital(st))*totalParticipations(l));
            //liste des dividendes par investisseur
        }
        Iterator it = st.getParts().iterator();
        while(it.hasNext()){
            Participation p = (Participation)it.next();
            double res = p.getMontant()+sums.get(p.getInv());
            p.setMontant(res);
            em.merge(p);
        }
        em.merge(st);
    }
    
    private double sousTotal(long inv, int st){
        Query query = em.createQuery("SELECT SUM(par.montant) FROM Startup s, in s.participations as par"
                + " WHERE s.idStartup= :idstartup "
                + "AND par.startup.idStartup= :idstartup"
                + "AND par.investisseur.idInvestisseur= :idinvestisseur");
        query.setParameter("idstartup", st);
        query.setParameter("idinvestisseur", inv);
        return Double.valueOf((String)query.getResultList().get(0));
    }

    public LeveeDeFonds leveeDeFonds(String d, double m, Organisateur o) {
        LeveeDeFonds f = new LeveeDeFonds(d, m, o);
        em.persist(f);
        return f;
    }

    public double totalParticipations(LeveeDeFonds l) {
        Iterator it = l.getParts().iterator();
        double total = 0.0;
        while(it.hasNext()){
            Participation temp = (Participation)it.next();
            total += temp.getMontant();
        }
        return total;
    }

    public void updateParticipation(String n, double m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Participation findParticipation(String s, String i) {
        Query query = em.createQuery("select p from Participation p where p.startup.idStartup= :startup and p.investisseur.idInvestisseur= :inv");
        return (Participation) query.getSingleResult();
    }

    public void updateStartup(String n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Startup findStartupByName(String n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Startup> findStartupByActivity(String a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateLeveeDeFonds(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LeveeDeFonds findLeveeDeFonds(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double calculCapital(Startup s) {
        throw new UnsupportedOperationException("Not supported yet.");
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
    
}
