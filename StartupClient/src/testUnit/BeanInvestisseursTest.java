package testUnit;

import java.util.List;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.ClubAmi;
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
	private Fondateur f1;
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
	
			prepareEntity();
			//System.out.println(remoteInv.afficherText("Acces a distant connecte !"));
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
	
	public void prepareEntity(){
		f1 = remoteInv.creerFondateur("Dupont", "dupont@gmail.com", "12345");
		s = remoteEvents.startup("MonStartup", "Informatique", f1);
		f1.setStartup(s);
	}
	
	/*
	 * TEST FONDATEUR ET STARTUP
	 */
	
	@Test
	public void testCreationStartup(){
		/**
		 * Creer 2 fondateurs f1 et f2
		 * f1 va monter sa startup et f2 va y rejoindre
		 */
		System.out.println("-Test creation fondateur et startup");
		System.out.println(s);
		System.out.println(f1);
		// Test d'ajout d'un fondateur dans la startup
		Fondateur f2 = remoteInv.creerFondateur("Tita", "tita@gmail.com", "1707");
		f2 = remoteInv.ajouterFondateurStartup(f2, s, true);
		System.out.println(f2);
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testUpdateFondateur(){
		System.out.println("-Test update fondateur");
		System.out.println("Fondateur avant update: " + f1);
		f1 = remoteInv.updateFondateur(f1, "Tartempion","dupont@gmail.com", "12345");
		System.out.println("Fondateur apres update: " + f1);
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testRechercheFondateur(){
		System.out.println("-Test recherche fondateur");
		Fondateur f2 = remoteInv.rechercherFondateurParId(f1.getIdInvestisseur());
		System.out.println(f2);
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	/*
	 * TEST CLUB AMI ET BUSINESS ANGEL
	 */
	@Test
	public void testCreerClubAmi(){
		/**
		 * Creer 2 business angel et monter 1 club ami qui contient ces 2 business angel
		 * Verifier que la table Membre est bien creee
		 */
		System.out.println("-Test creation d'un club ami");
		BusinessAngel ba1 = remoteInv.creerBA("Jean", "jean@yahoo.com", "jean");
		BusinessAngel ba2 = remoteInv.creerBA("Paul", "paul@gmail.com", "paul");
		ClubAmi ca = remoteInv.monterClubAmi(ba1, "Club BA Paris");
		ba1 = remoteInv.updateBA(ba1, ba1.getNom(), ba1.getMail(), ba1.getMdp());
		remoteInv.ajouterMembre(ba2, ca.getIdClub(), false);
		
		System.out.println(ba1);
		System.out.println(ba2);
		System.out.println(ca);
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testRechercheBA(){
		/**
		 * Chercher tous les business angel dont le nom est "Jean"
		 */
		System.out.println("-Test recherche business angel");
		List<BusinessAngel> ba = remoteInv.rechercherBAParNom("Jean");
		for (int i=0; i<ba.size(); i++){
			System.out.println(ba.get(i));	
		}
		
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testRechercheClubAmi(){
		System.out.println("-Test recherche club ami");
		List<ClubAmi> clubs = remoteInv.rechercherClubParNom("Club BA Paris");
		for (int i=0; i<clubs.size(); i++){
			System.out.println(clubs.get(i));	
		}
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test 
	public void testUpdateBAClubAmi(){
		System.out.println("-Test update Business Angel et Club Ami");
		ClubAmi ca = remoteInv.rechercherClubParNom("Club BA Paris").get(0);
		BusinessAngel ba = ca.getMandataire();
		System.out.println("Club avant update " + ca);
		ba = remoteInv.updateBA(ba, "Jean_", ba.getMail(), "1234");
		ca = remoteInv.updateClubAmi(ca, ca.getNomClub());
		System.out.println("Club apres update: " + ca);
		System.out.println("-Fin test----------------------------------------\n");
		
	}
	
	@Test
	public void testPartenaire (){
		System.out.println("-Test relation entre startup et son club ami");
		ClubAmi ca = remoteInv.rechercherClubParNom("Club BA Paris").get(0); 
		remoteInv.mettreEnPartenaire(ca, s);
		ca = remoteInv.updateClubAmi(ca);
		s = remoteEvents.updateStartup(s);
		System.out.println(ca);
		System.out.println(s.getClubs());
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@After
    public void tearDown(){}

}
