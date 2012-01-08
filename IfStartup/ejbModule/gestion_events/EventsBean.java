/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_events;

import gestion_investisseurs.AbstraitInvestisseur;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.Organisateur;
import javax.ejb.Stateless;

/**
 *
 * @author UTILISATEUR
 */
@Stateless
public class EventsBean implements EventsBeanLocal, EventsBeanRemote {

    public Participation participation(Startup s, AbstraitInvestisseur i, double d) {
        return new Participation(s, i, d);
    }

    public Startup startup(String nom, String activite, Fondateur f) {
        return new Startup(nom, activite, f);
    }

    public void distribuerDividende() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LeveeDeFonds leveeDeFonds(String d, double m, Organisateur o) {
        return new LeveeDeFonds(d, m, o);
    }

    public double totalParticipations(LeveeDeFonds l) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
