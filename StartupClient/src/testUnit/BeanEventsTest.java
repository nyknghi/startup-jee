package testUnit;

import java.util.List;

import gestion_events.EventsBeanRemote;
import gestion_events.Participation;
import gestion_events.Startup;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import util.Couple;

import junit.framework.TestCase;

public class BeanEventsTest extends TestCase{
	private RemoteInvestisseurs remoteInv;
	private EventsBeanRemote remoteEvents;
	
	public BeanEventsTest(){}
	
	@Before
    public void setUp() {
		Context ctx;
		try {
			ctx = new InitialContext();
			ctx.addToEnvironment(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			ctx.addToEnvironment(InitialContext.URL_PKG_PREFIXES,
					"org.jboss.naming:org.jnp.interfaces");
			ctx.addToEnvironment(InitialContext.PROVIDER_URL,
					"jnp://localhost:1099");
			
			remoteInv = (RemoteInvestisseurs) ctx.lookup("BeanInvestisseurs/remote");
			remoteEvents = (EventsBeanRemote) ctx.lookup("EventsBean/remote");
	
			//System.out.println(remoteInv.afficherText("Acces a distant connecte !"));
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testCRUDStartup(){
		System.out.println("-Test creation startup");
		// CREATION
		Fondateur f1 = remoteInv.creerFondateur("Tita", "tita@gmail.com", "1707");
		Fondateur f2 = remoteInv.creerFondateur("Jack", "jack@gmail.com", "2349");
		Couple<Startup, Fondateur> res = remoteEvents.startup("MStartup", "Informatique", f1);
		Startup s = res.getObjetA();
		f1 = res.getObjetB();
		f2 = remoteInv.ajouterFondateurStartup(f2, s, true).getObjetA();
		System.out.println(s);
		System.out.println(f1);
		System.out.println(f2);
		// UPDATE
		s = remoteEvents.updateStartup(s, "MaStartup", "Informatique");
		System.out.println("\nApres update : \n" + s);
		// RECHERCHE
		System.out.println("\nRecherche les startups ayant comme nom MaStartup");
		List<Startup> startups = remoteEvents.findStartupByName("MaStartup");
		if (startups.size() > 0){
			for (Startup st : startups){
				System.out.println(st);
			}
		}
		System.out.println("\nRecherche les startups dans le domaine Informatique");
		remoteEvents.startup("HighTech", "Informatique", f1);
		List<Startup> startups_2 = remoteEvents.findStartupByActivity("Informatique");
		if (startups_2.size() > 0){
			for (Startup st : startups_2){
				System.out.println(st);
			}
		}
		
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testCRUDParticipation(){
		System.out.println("-Test creation participation");
		// CREATION
		Fondateur f = remoteInv.creerFondateur("Kendy", "kendy@gmail.com", "ken123");
		Couple<Startup, Fondateur> res = remoteEvents.startup("AssetManagement", "Assurance", f);
		Startup s = res.getObjetA();
		f = res.getObjetB();
		Participation p = remoteEvents.participation(s, f, 10000);
		System.out.println(p);
		// UPDATE
		p = remoteEvents.updateParticipation(s.getIdStartup(), f.getIdInvestisseur(), 12000);
		System.out.println("Apres update : " + p);
		// RECHERCHE
		System.out.println("\nLes participations du fondateur " + f.getNom() + " dans le startup " +
				s.getNomStartup() + "\n");
		List<Participation> parts = remoteEvents.findParticipation(f);
		if (parts.size() > 0){
			for (Participation pi : parts){
				System.out.println(pi);
			}
		}
		
		System.out.println("-Fin test----------------------------------------\n");
	}
}
