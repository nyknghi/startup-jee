package testUnit;

import java.util.List;
import gestion_events.Etape;
import gestion_events.EventsBeanRemote;
import gestion_events.Inscription;
import gestion_events.LeveeDeFonds;
import gestion_events.Participation;
import gestion_events.Startup;
import gestion_investisseurs.AbstraitInvestisseur;
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
	private Fondateur f1, f2, f3;
	private Startup s1, s2;
	
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
	
			//prepareEntity();
			//System.out.println(remoteInv.afficherText("Acces a distant connecte !"));
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	
	public void prepareEntity(){
		f1 = remoteInv.creerFondateur("Tita", "tita@gmail.com", "1707");
		f2 = remoteInv.creerFondateur("Jack", "jack@gmail.com", "2349");
		Couple<Startup, Fondateur> res1 = remoteEvents.startup("MStartup", "Informatique", f1);
		s1 = res1.getObjetA();
		f1 = res1.getObjetB();
		
		f3 = remoteInv.creerFondateur("Kendy", "kendy@gmail.com", "ken123");
		Couple<Startup, Fondateur> res2 = remoteEvents.startup("AssetManagement", "Assurance", f3);
		s2 = res2.getObjetA();
		f3 = res2.getObjetB();
	}
	
	@Test
	public void testCRUDStartup(){
		System.out.println("-Test creation startup");
		// CREATION
		f1 = remoteInv.creerFondateur("Tita", "tita@gmail.com", "1707");
		f2 = remoteInv.creerFondateur("Jack", "jack@gmail.com", "2349");
		Couple<Startup, Fondateur> res1 = remoteEvents.startup("MStartup", "Informatique", f1);
		s1 = res1.getObjetA();
		f1 = res1.getObjetB();
		
		f2 = remoteInv.ajouterFondateurStartup(f2, s1, true).getObjetA();
		System.out.println(s1);
		System.out.println(f1);
		System.out.println(f2);
		// UPDATE
		s1 = remoteEvents.updateStartup(s1, "MaStartup", "Informatique");
		System.out.println("\nApres update : \n" + s1);
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
		
		System.out.println("\nRecherche tous les startups dans la BD");
		List<Startup> resStart = remoteEvents.findAllStartup();
		if (resStart.size() > 0){
			for (Startup st : resStart){
				System.out.println(st);
			}
		}
		
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testCRUDParticipation(){
		System.out.println("-Test creation participation");
		// CREATION
		f3 = remoteInv.creerFondateur("Kendy", "kendy@gmail.com", "ken123");
		Couple<Startup, Fondateur> res2 = remoteEvents.startup("AssetManagement", "Assurance", f3);
		s2 = res2.getObjetA();
		f3 = res2.getObjetB();
		Couple<Startup, Participation> res = remoteEvents.participation(s2, f3, 10000);
		Participation p = res.getObjetB();
		s2 = res.getObjetA();
		System.out.println(p);
		// UPDATE
		p = remoteEvents.updateParticipation(s2.getIdStartup(), f3.getIdInvestisseur(), 12000);
		System.out.println("Apres update : " + p);
		// RECHERCHE
		System.out.println("\nLes participations du fondateur " + f3.getNom() + " dans la startup " +
				s2.getNomStartup() + "\n");
		List<Participation> parts = remoteEvents.findParticipation(f3);
		if (parts.size() > 0){
			for (Participation pi : parts){
				System.out.println(pi);
			}
		}
		
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testOrganiserLeveeFonds(){
		System.out.println("-Test organiser une levee de fonds");
		prepareEntity();
		Couple<AbstraitInvestisseur, LeveeDeFonds> org_levee = remoteInv.organiserLeveeFonds(s1, f1, 50000);
		LeveeDeFonds levee = org_levee.getObjetB();
		f1 = (Fondateur)org_levee.getObjetA();
		System.out.println(levee);
		
		
		//Inscription
		Couple<LeveeDeFonds, Inscription> ins = remoteInv.inscrireLevee(f3, levee);
		levee = ins.getObjetA();
		Inscription i = ins.getObjetB();
		//System.out.println("Investisseur " + f2.getNom() + " a inscrit a la levee de fonds " + levee.getIdLevee());
		System.out.println(i);
		
		
		Couple<LeveeDeFonds, Participation> inv_part = null;
		while (inv_part == null){
			inv_part = remoteEvents.participation(levee, f2, 12000);
			if (inv_part != null){
				levee = inv_part.getObjetA();
				Participation p = inv_part.getObjetB();
				System.out.println(p);
				break;
			} else {
				System.out.println("Err_Etape_LeveeFonds: La levee de fonds ne prend encore des participations");
			}
			levee = remoteInv.modifierEtape(levee, Etape.ENGAGEMENT);
			System.out.println(levee);
		}
			
		Couple<LeveeDeFonds, Participation> inv_part2 = remoteEvents.participation(levee, f3, 7000);
		levee = inv_part2.getObjetA();
		Participation p2 = inv_part2.getObjetB();
		System.out.println(p2);
		
		double total = remoteEvents.totalParticipations(levee);
		System.out.println("Total de participations lors de la levee de fonds = " + total);
		
		System.out.println("-Fin test----------------------------------------\n");
		
	}
	
	@Test
	public void testEvaluerStartup(){
		System.out.println("-Test evaluation startup");
		s2 = remoteEvents.findStartupByName("AssetManagement").get(0);
		
		double capital = remoteEvents.calculCapital(s2);
		System.out.println("Capital de la startup " + s2.getNomStartup() + " est : " + capital);
		
		System.out.println("Nouveau investissement :");
		Fondateur f4 = remoteInv.creerFondateur("Raymond", "raymond@hotmail.fr", "raym2");
		Couple<Fondateur, Startup> res = remoteInv.ajouterFondateurStartup(f4, s2, false);
		f4 = res.getObjetA();
		s2 = res.getObjetB();		
		
		Couple<Startup, Participation> res2 = remoteEvents.participation(s2, f4, 25000);
		s2 = res2.getObjetA();
		s2 = remoteEvents.updateStartup(s2);
		Participation p2 = res2.getObjetB();
		System.out.println(p2);
		capital = remoteEvents.calculCapital(s2);
		System.out.println("Capital de la startup " + s2.getNomStartup() + " est : " + capital);
		System.out.println("-Fin test----------------------------------------\n");
	}
}
