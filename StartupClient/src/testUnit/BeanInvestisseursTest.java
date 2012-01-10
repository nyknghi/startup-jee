package testUnit;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class BeanInvestisseursTest extends TestCase{
	private RemoteInvestisseurs remoteInv;
	private EventsBeanRemote remoteEvents;
	private Fondateur f;
	private Startup s;
	
	public BeanInvestisseursTest() {}
	
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
			
			f = remoteInv.creerFondateur("Dupont", "dupont@gmail.com", "12345");
			s = remoteEvents.startup("MonStartup", "Informatique", f);
			f.setStartup(s);
			//System.out.println(remoteInv.afficherText("Acces a distant connecte !"));
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	/*
	 * Test de la creation d'un fondateur et de son startup
	 */
	@Test
	public void testCreationStartup(){
		System.out.println("-Test creation fondateur et startup");
		System.out.println(s);
		System.out.println(f);
		// Test d'ajout d'un fondateur dans la startup
		Fondateur f1 = remoteInv.creerFondateur("Tita", "tita@gmail.com", "1707");
		f1 = remoteInv.ajouterFondateurStartup(f1, s, true);
		System.out.println(f1);
		System.out.println("-Fin test----------------------------------------");
	}
	
	@Test
	public void testUpdateFondateur(){
		System.out.println("-Test update fondateur");
		System.out.println("Fondateur avant update: " + f);
		f = remoteInv.updateFondateur(f, "Tartempion","dupont@gmail.com", "12345");
		System.out.println("Fondateur apres update:" + f);
		System.out.println("-Fin test----------------------------------------");
	}
	
	@Test
	public void testRecherche(){
		System.out.println("-Test recherche fondateur");
		Fondateur f1 = remoteInv.rechercherFondateurParId(f.getIdInvestisseur());
		System.out.println(f1);
		System.out.println("-Fin test----------------------------------------");
	}
	
	
	@After
    public void tearDown(){}

}
