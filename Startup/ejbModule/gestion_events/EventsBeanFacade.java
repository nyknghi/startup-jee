/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Fondateur;

import java.util.Date;
import java.util.List;

import util.Couple;

/**
 *
 * @author UTILISATEUR
 */
public interface EventsBeanFacade {
    //CRUD participations
    public Couple<Startup, Participation> participation(Startup s, AbstraitInvestisseur i, double m);
    public Couple<LeveeDeFonds, Participation> participation(LeveeDeFonds levee, AbstraitInvestisseur i, double m);
    public Participation updateParticipation(long idStartup, long idInv, double m);
    public Participation updateParticipation(Participation p);
    public List<Participation> findParticipation(AbstraitInvestisseur inv);
    public Participation findParticipation(long idStartup, long idInv);
    
    //CRUD startup
    public Couple<Startup, Fondateur> startup(String nom, String activite, Fondateur f);
    public Startup updateStartup(Startup s, String nom, String a);
    public Startup updateStartup(Startup s);
    public List<Startup> findStartupByName(String n);
    public Startup findStartupById(long id);
    public List<Startup> findStartupByActivity(String a);
    
    //CRUD  levee de fonds
    public Couple<Startup,LeveeDeFonds> leveeDeFonds(Date d, double montant, AbstraitInvestisseur o, Startup s);
    public LeveeDeFonds updateLeveeDeFonds(long id, Date date, Etape e);
    public LeveeDeFonds updateLeveeDeFonds(LeveeDeFonds levee);
    public LeveeDeFonds findLeveeDeFonds(long id);
    
    public double totalParticipations(LeveeDeFonds l);
    
    public double calculCapital(Startup s);
    
    public void distribuerDividende(LeveeDeFonds l);
    
    public double postValue(Startup s, LeveeDeFonds l);
}
