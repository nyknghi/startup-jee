package gestion_investisseurs;

import gestion_events.Etape;
import gestion_events.EventsBeanLocal;
import gestion_events.LeveeDeFonds;
import gestion_events.Startup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.*;

import util.Couple;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanInvestisseurs implements RemoteInvestisseurs, LocalInvestisseurs{
	@PersistenceContext(unitName="SampleUnit")
	EntityManager em;
	
	EventsBeanLocal eventLocal;

	@PostConstruct
	public void init(){
		System.out.println("Calling init method");
		try{
			Context ctx = new InitialContext();
			eventLocal = (EventsBeanLocal) ctx.lookup("EventsBean/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}  
	}
	
	@Override
	public Fondateur creerFondateur(String nom, String mail, String mdp) {
		Fondateur f = new Fondateur (nom, mail, mdp);
		em.persist(f);
		return f;
	}

	@Override
	public Fondateur updateFondateur(Fondateur f, String nom, String mail, String mdp) {
		f = this.rechercherFondateurParId(f.getIdInvestisseur());
		f.setNom(nom);
		f.setMail(mail);
		f.setMdp(mdp);
		return em.merge(f);
	}
	
	@Override
	public Fondateur updateFondateur(Fondateur f) {
		return em.merge(f);
	}

	@Override
	public Couple<Fondateur, Startup> ajouterFondateurStartup(Fondateur f, Startup s, boolean isMandataire) {
		f = this.rechercherFondateurParId(f.getIdInvestisseur());
		s = eventLocal.findStartupById(s.getIdStartup());
		s.addFondateur(f);
		f.setStartup(s);
		f.setMandataire(isMandataire);
		return new Couple<Fondateur, Startup>(em.merge(f), em.merge(s));
	}
	
	@Override
	public Fondateur rechercherFondateurParId (long id){
		return em.find(Fondateur.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Fondateur> rechercherFondateur(String nom) {
		Query query = em.createNamedQuery("findFondateurByName");
		query.setParameter("nom", nom);
		return (ArrayList<Fondateur>) query.getResultList();
	}
	
	@Override
	public BusinessAngel creerBA(String nom, String mail, String mdp) {
		BusinessAngel ba = new BusinessAngel(nom, mail, mdp);
		em.persist(ba);
		return ba;
	}

	@Override
	public BusinessAngel updateBA(BusinessAngel ba, String nom, String mail, String mdp) {
		ba = this.rechercherBAParId(ba.getIdInvestisseur());
		ba.setNom(nom);
		ba.setMail(mail);
		ba.setMdp(mdp);
		return em.merge(ba);
	}

	@Override
	public BusinessAngel updateBA(BusinessAngel ba) {
		return em.merge(ba);
	}
	
	@Override
	public BusinessAngel rechercherBAParId(long id) {
		return em.find(BusinessAngel.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessAngel> rechercherBAParNom(String nom) {
		Query query = em.createQuery("SELECT ba FROM BusinessAngel as ba WHERE ba.nom = :nom");
		query.setParameter("nom", nom);
		return (List<BusinessAngel>) query.getResultList();
	}
	
	@Override
	public ClubAmi monterClubAmi (BusinessAngel ba, String nomClub) {
		ClubAmi ca = new ClubAmi(nomClub);
		em.persist(ca);
		this.ajouterMembre(ba, ca.getIdClub(), true);
		ca.setMandataire(ba);
		ba.setMandataire(true);
		em.merge(ba);
		return em.merge(ca);
	}
	
	@Override
	public ClubAmi updateClubAmi(ClubAmi ca, String nomClub) {
		ca = this.rechercherClubParId(ca.getIdClub());
		ca.setNomClub(nomClub);
		return em.merge(ca);
	}
	
	@Override
	public ClubAmi updateClubAmi(ClubAmi ca) {
		return em.merge(ca);
	}
	
	@Override
	public ClubAmi rechercherClubParId (long id){
		return em.find(ClubAmi.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClubAmi> rechercherClubParNom (String nom){
		Query query = em.createQuery("SELECT ca FROM ClubAmi as ca WHERE ca.nomClub = :nom");
		query.setParameter("nom", nom);
		return (List<ClubAmi>) query.getResultList();
	}
	
	@Override
	public Couple<ClubAmi, Startup> mettreEnPartenaire (ClubAmi ca, Startup s){
		ca = this.rechercherClubParId(ca.getIdClub());
		ca.setStartup(s);
		s.getClubs().add(ca);
		return new Couple<ClubAmi, Startup>(em.merge(ca), em.merge(s));
	}
	
	@Override
	public Couple<ClubAmi, BusinessAngel> ajouterMembre (BusinessAngel ba, long idClub, boolean mandataire){
		ClubAmi ca = this.rechercherClubParId(idClub);
		ba.setMandataire(mandataire);
		Membre membre = new Membre(ca, ba, new Date(), mandataire);
		ca.getMembres().add(membre);
		ba.getClubAmis().add(membre);
		em.persist(membre);
		return new Couple<ClubAmi, BusinessAngel>(em.merge(ca), em.merge(ba));
	}
	
	@Override
	public Couple<ClubAmi, BusinessAngel> supprimerMembre (BusinessAngel ba, ClubAmi ca){
		Membre m = this.rechercherMembreParId(ba.getIdInvestisseur(), ca.getIdClub());
		ba = this.rechercherBAParId(ba.getIdInvestisseur());
		ca = this.rechercherClubParId(ca.getIdClub());
		ca.getMembres().remove(m);
		ba.getClubAmis().remove(m);
		em.remove(m);
		System.out.println("Membre " + ba.getIdInvestisseur() + "supprime");
		return new Couple<ClubAmi, BusinessAngel>(em.merge(ca), em.merge(ba));
	}
	
	@Override
	public Membre rechercherMembreParId (long idBA, long idClub){
		Query query = em.createNamedQuery("findMembreById");
		query.setParameter("idBA", idBA);
		query.setParameter("idClub", idClub);
		return (Membre) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessAngel> listerMembres (ClubAmi ca){
		ca = this.rechercherClubParId(ca.getIdClub());
		Query query = em.createQuery("SELECT ba FROM Membre AS m, BusinessAngel AS ba " +
				"WHERE m.idClub = :idClub AND m.idBA = ba.idInvestisseur");
		query.setParameter("idClub", ca.getIdClub());
		return query.getResultList();
	}


	@Override
	public Investisseur creerInvestisseur(String nom, String mail, String mdp) {
		Investisseur inv = new Investisseur(nom, mail, mdp);
		em.persist(inv);
		return inv;
	}

	@Override
	public Investisseur updateInvestisseur(Investisseur inv, String nom, String mail, String mdp) {
		inv = this.rechercherInvestisseurParId(inv.getIdInvestisseur());
		inv.setNom(nom);
		inv.setMail(mail);
		inv.setMdp(mdp);
		return em.merge(inv);
	}

	@Override
	public Investisseur updateInvestisseur(Investisseur inv) {
		return em.merge(inv);
	}

	@Override
	public Investisseur rechercherInvestisseurParId(long id) {
		return em.find(Investisseur.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Investisseur> rechercherInvestisseurParNom(String nom) {
		Query query = em.createQuery("SELECT i FROM Investisseur as i WHERE i.nom = :nom");
		query.setParameter("nom", nom);
		return (List<Investisseur>) query.getResultList();
	}
	
	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Investisseur> rechercherInvestisseurParGroupe(GroupeInvestisseurs groupe) {
		groupe = this.rechercherGroupeParId(groupe.getIdInvestisseur());
		Query query = em.createQuery("SELECT i FROM Investisseur as i WHERE i.groupe = :idgroupe");
		query.setParameter("idgroupe", groupe.getIdInvestisseur());
		return (List<Investisseur>) query.getResultList();
	}
	*/
	
	@Override
	public Couple<GroupeInvestisseurs, Investisseur> monterGroupe(Investisseur inv, String nomGroupe) {
		GroupeInvestisseurs groupe = new GroupeInvestisseurs(nomGroupe);
		em.persist(groupe);
		return this.adhererGroupe(groupe, inv, true);
	}

	@Override
	public GroupeInvestisseurs updateGroupeInvestisseurs(GroupeInvestisseurs groupe, String nomGroupe) {
		groupe = this.rechercherGroupeParId(groupe.getIdInvestisseur());
		groupe.setNom(nomGroupe);
		return em.merge(groupe);
	}

	@Override
	public GroupeInvestisseurs updateGroupeInvestisseurs(GroupeInvestisseurs groupe) {
		return em.merge(groupe);
	}
	
	@Override
	public GroupeInvestisseurs rechercherGroupeParId(long id) {
		return em.find(GroupeInvestisseurs.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupeInvestisseurs> rechercherGroupeParNom(String nomGroupe) {
		Query query = em.createQuery("SELECT g FROM GroupeInvestisseurs as g WHERE g.nom = :nom");
		query.setParameter("nom", nomGroupe);
		return (List<GroupeInvestisseurs>) query.getResultList();
	}
	
	@Override
	public Couple<GroupeInvestisseurs, Investisseur> adhererGroupe(GroupeInvestisseurs groupe, 
			Investisseur inv, boolean isLeader) {
		groupe.getInvestisseurs().add(inv);
		inv.setGroupe(groupe);
		inv.setLeader(isLeader);
		return new Couple<GroupeInvestisseurs, Investisseur>(em.merge(groupe), em.merge(inv));
	}

	@Override
	public Couple<GroupeInvestisseurs, Investisseur> quitterGroupe(GroupeInvestisseurs groupe, Investisseur inv) {
		groupe = this.rechercherGroupeParId(groupe.getIdInvestisseur());
		inv = this.rechercherInvestisseurParId(inv.getIdInvestisseur());
		groupe.getInvestisseurs().remove(inv);
		inv.setGroupe(null);
		return new Couple<GroupeInvestisseurs, Investisseur>(em.merge(groupe), em.merge(inv));	
	}
	
	@Override
	public Couple<AbstraitInvestisseur,LeveeDeFonds> organiserLeveeFonds(Startup s, AbstraitInvestisseur ainv, double cible) {
		LeveeDeFonds levee = eventLocal.leveeDeFonds(new Date(), cible, ainv, s).getObjetB();
		if (ainv instanceof Fondateur){
			Fondateur f = (Fondateur)ainv;
			f.getLeveeDeFonds().add(levee);
			levee.setOrganisateur(f);
			return new Couple<AbstraitInvestisseur,LeveeDeFonds>(em.merge(f), em.merge(levee));
		}
		else if (ainv instanceof BusinessAngel){
			BusinessAngel ba = (BusinessAngel)ainv;
			ba.getLeveeDeFonds().add(levee);
			levee.setOrganisateur(ba);
			return new Couple<AbstraitInvestisseur,LeveeDeFonds>(em.merge(ba), em.merge(levee));
		} else {
			System.out.println("Droit organisateur !");
			return null;
		}
	}

	@Override
	public LeveeDeFonds modifierEtape(LeveeDeFonds levee, Etape etape) {
		levee = eventLocal.updateLeveeDeFonds(levee.getIdLevee(), new Date(), etape);
		return em.merge(levee);
	}

	@Override
	public LeveeDeFonds annulerLeveeFonds(LeveeDeFonds levee) {
		levee = eventLocal.findLeveeDeFonds(levee.getIdLevee());
		levee.setEtape(Etape.ANNULLATION);
		return em.merge(levee);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * La methode recupere la liste des accounts de tous les investisseurs.
	 * Le resultat sera stocke dans un HashMap dont la cle est l'email et la valeur est le mdp 
	 */
	public HashMap<String, String> listeAccountsFondateurs(){
		HashMap<String, String> res = new HashMap<String, String>();
		Query query = em.createQuery("SELECT f FROM Fondateur as f");
		List<Fondateur> fondateurs = (List<Fondateur>)query.getResultList();
		for (int i=0; i<fondateurs.size(); i++){
			Fondateur f = fondateurs.get(i);
			res.put(f.getMail(), f.getMdp());
		}
		
		return res;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> listeAccountsBA() {
		HashMap<String, String> res = new HashMap<String, String>();
		Query query = em.createQuery("SELECT ba FROM BusinessAngel as ba");
		List<BusinessAngel> bas = (List<BusinessAngel>)query.getResultList();
		for (int i=0; i<bas.size(); i++){
			BusinessAngel ba = bas.get(i);
			res.put(ba.getMail(), ba.getMdp());
		}
		
		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> listeAccountsInvestisseurs() {
		HashMap<String, String> res = new HashMap<String, String>();
		Query query = em.createQuery("SELECT inv FROM Investisseur as inv");
		List<Investisseur> invs = (List<Investisseur>)query.getResultList();
		for (int i=0; i<invs.size(); i++){
			Investisseur inv = invs.get(i);
			res.put(inv.getMail(), inv.getMdp());
		}
		
		return res;
	}
	
	@PreDestroy()
	public void cleanUp(){
		System.out.println("Calling cleanup method");
	}	
	
	public void closeEM(){
		em.close();
	}
}
