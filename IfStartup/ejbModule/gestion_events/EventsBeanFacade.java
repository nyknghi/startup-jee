/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.Organisateur;

/**
 *
 * @author UTILISATEUR
 */
public interface EventsBeanFacade {
    public Participation participation(Startup s, AbstraitInvestisseur i, double d);
    public Startup startup(String nom, String activite, Fondateur f);
    public LeveeDeFonds leveeDeFonds(String d, double m, Organisateur o);
    public double totalParticipations(LeveeDeFonds l);
    public void distribuerDividende();
}
