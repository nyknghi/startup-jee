/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Fondateur;
import java.util.List;

/**
 *
 * @author UTILISATEUR
 */
public interface EventsBeanFacade {
    //CRUD participations
    public Participation participation(Startup s, AbstraitInvestisseur i, double d);
    public Participation updateParticipation(String s, String i, double m);
    public Participation updateParticipation(Participation p);
    public List<Participation> findParticipation(AbstraitInvestisseur inv);
    public Participation findParticipation(String s, String i);
    
    //CRUD startup
    public Startup startup(String nom, String activite, Fondateur f);
    public Startup updateStartup(String n, String nouv, String a);
    public Startup updateStartup(Startup s);
    public Startup findStartupByName(String n);
    public List<Startup> findStartupByActivity(String a);
    
    //CRUD  levee de fonds
    public LeveeDeFonds leveeDeFonds(String d, double m, AbstraitInvestisseur o);
    public LeveeDeFonds updateLeveeDeFonds(long id, String date, Etape e);
    public LeveeDeFonds updateLeveeDeFonds(LeveeDeFonds levee);
    public LeveeDeFonds findLeveeDeFonds(long id);
    
    public double totalParticipations(LeveeDeFonds l);
    
    public double calculCapital(Startup s);
    
    public void distribuerDividende(LeveeDeFonds l);
    
    public Double postValue(Startup s, LeveeDeFonds l);
}
