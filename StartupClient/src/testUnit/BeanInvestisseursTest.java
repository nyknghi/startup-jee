package testUnit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gestion_events.EventsBeanRemote;
import gestion_events.Startup;
import gestion_investisseurs.BusinessAngel;
import gestion_investisseurs.ClubAmi;
import gestion_investisseurs.Fondateur;
import gestion_investisseurs.GroupeInvestisseurs;
import gestion_investisseurs.Investisseur;
import gestion_investisseurs.RemoteInvestisseurs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Couple;
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
		// CREATION
		f1 = remoteInv.creerFondateur("Dupont", "dupont@gmail.com", "12345");
		Couple<Startup, Fondateur> res = remoteEvents.startup("MStartup", "Informatique", f1);
		s = res.getObjetA();
		f1 = res.getObjetB();
	}
	
	/*
	 * TEST FONDATEUR ET STARTUP
	 */
	
	@Test
	public void testCRUDFondateur(){
		System.out.println("-Test CRUD fondateur");
		// UPDATE
		System.out.println("Fondateur avant update: " + f1);
		Fondateur f2 = remoteInv.updateFondateur(f1, "Tartempion","dupont@gmail.com", "12345");
		System.out.println("Fondateur apres update: " + f2);
		assertFalse(f1.equals(f2));
		// RECHERCHE
		f2 = remoteInv.rechercherFondateurParId(f1.getIdInvestisseur());
		if (f2 == null){
			System.out.println("Fondateur non trouve !");
		} else {
			System.out.println(f2);
		}
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
	public void testSupprimerMembreCA(){
		/**
		 * Afficher tous les membres (business angel) du club "Club BA Paris"
		 * puis y en supprimer un.
		 */
		System.out.println("-Test supprimer membre d'un club ami");
		ClubAmi ca = remoteInv.rechercherClubParNom("Club BA Paris").get(0);
		List<BusinessAngel> membres = remoteInv.listerMembres(ca);
		for (int i=0; i<membres.size(); i++){
			System.out.println(membres.get(i));
		}
		Couple<ClubAmi, BusinessAngel> res = remoteInv.supprimerMembre(membres.get(0), ca);
		ca = res.getObjetA();
		System.out.println("Membre " + membres.get(0).getNom() + " a quitte son club");
		membres = remoteInv.listerMembres(ca);
		for (int i=0; i<membres.size(); i++){
			System.out.println(membres.get(i));
		}
		
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
		/**
		 * Mettre en partenaire le club "Club BA Paris" et la startup "MaStartup"
		 */
		System.out.println("-Test relation entre startup et son club ami");
		ClubAmi ca = remoteInv.rechercherClubParNom("Club BA Paris").get(0); 
		Couple<ClubAmi, Startup> res = remoteInv.mettreEnPartenaire(ca, s);
		ca = res.getObjetA(); s = res.getObjetB();
		System.out.println(ca);
		//System.out.println(s.getClubs());
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	/*
	 * TEST GROUPE INVESTISSEURS
	 */
	
	@Test
	public void testGestionGroupeInv(){
		System.out.println("-Test creation d'un groupe investisseurs");
		Investisseur inv1 = remoteInv.creerInvestisseur("Marc", "marc@gmail.com", "marcmdp");
		Investisseur inv2 = remoteInv.creerInvestisseur("Jerry", "jerry@gmail.com", "jmdp");
		
		Couple<GroupeInvestisseurs, Investisseur> res = remoteInv.monterGroupe(inv1, "Groupe des beaux gosses");
		GroupeInvestisseurs groupe = res.getObjetA();
		inv1 = res.getObjetB();
		
		res = remoteInv.adhererGroupe(groupe, inv2, false);
		groupe = res.getObjetA();
		inv2 = res.getObjetB();
		
		System.out.println(inv1);
		System.out.println(inv2);
		System.out.println(groupe);
		
		// Changer le nom du groupe
		groupe = remoteInv.updateGroupeInvestisseurs(groupe, "Beaux gosses");
		System.out.println(groupe);
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testRechercherInvestisseur(){
		System.out.println("-Test recherche investisseur");
		List<Investisseur> invs = remoteInv.rechercherInvestisseurParNom("Jerry");
		Investisseur inv = invs.get(invs.size()-1);
		System.out.println(inv);
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@Test
	public void testQuitterGroupe (){
		System.out.println("-Test quitter groupe");
		List<GroupeInvestisseurs> groupes = remoteInv.rechercherGroupeParNom("Beaux gosses");
		if (groupes.size() > 0){
			GroupeInvestisseurs groupe = groupes.get(0);
			List<Investisseur> invs = remoteInv.rechercherInvestisseurParNom("Jerry");
			Investisseur inv = invs.get(invs.size()-1);
			Couple<GroupeInvestisseurs, Investisseur> res = remoteInv.quitterGroupe(groupe, inv);
			groupe = res.getObjetA();
			inv = res.getObjetB();
			System.out.println("Investisseur " + inv.getIdInvestisseur() + " a quitte son groupe");
			
		} else {
			System.out.println("Groupe non trouve !");
		}
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	/*
	 * TEST DES ACCOUNTS 
	 */
	@Test
	public void testAccounts(){
		System.out.println("-Test recuperation des accounts");
		HashMap<String, String> acc_fond = remoteInv.listeAccountsFondateurs();
		Set<String> emails = acc_fond.keySet();
		Iterator<String> it = emails.iterator();
		while (it.hasNext()){
			String email = it.next();
			String mdp = acc_fond.get(email);
			System.out.println("email=" + email + ", mdp=" + mdp);
		}
		System.out.println("-Fin test----------------------------------------\n");
	}
	
	@After
    public void tearDown(){}

}
