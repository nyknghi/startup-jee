package gestion_investisseurs;

import gestion_events.EventsBean;
import gestion_events.Startup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.Stateless;
import javax.persistence.*;

import util.Couple;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanInvestisseurs implements RemoteInvestisseurs, LocalInvestisseurs{
	@PersistenceContext(unitName="SampleUnit")
	EntityManager em;
	
	EventsBean eventBean = new EventsBean();
	
	public String afficherText(String t){
		return t;
	}

	@PostConstruct
	public void init(){
		System.out.println("Calling init method");
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
		s = this.rechercherStartupById(s.getIdStartup());		
		s.addFondateur(f);
		f.setStartup(s);
		f.setMandataire(isMandataire);
		return new Couple<Fondateur, Startup>(em.merge(f), em.merge(s));
	}
	
	@Override
	public Fondateur rechercherFondateurParId (long id){
		return em.find(Fondateur.class, id);
	}

	public Startup rechercherStartupById (long id){
		return em.find(Startup.class, id);
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
		//s = eventBean.findStartupById(s.getIdStartup());
		s = this.rechercherStartupById(s.getIdStartup());
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
	public Couple<GroupeInvestisseurs, Investisseur> monterGroupe(Investisseur inv, String nomGroupe) {
		GroupeInvestisseurs groupe = new GroupeInvestisseurs(nomGroupe);
		em.persist(groupe);
		return this.adhererGroupe(groupe, inv);
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
	public Couple<GroupeInvestisseurs, Investisseur> adhererGroupe(GroupeInvestisseurs groupe, Investisseur inv) {
		groupe.getInvestisseurs().add(inv);
		inv.setGroupe(groupe);
		return new Couple<GroupeInvestisseurs, Investisseur>(em.merge(groupe), em.merge(inv));
	}

	@Override
	public Couple<GroupeInvestisseurs, Investisseur> quitterGroupe(GroupeInvestisseurs groupe, Investisseur inv) {
		groupe.getInvestisseurs().remove(inv);
		inv.setGroupe(null);
		return new Couple<GroupeInvestisseurs, Investisseur>(em.merge(groupe), em.merge(inv));	
	}
	
	@PreDestroy()
	public void cleanUp(){
		System.out.println("Calling cleanup method");
	}	
	
	public void closeEM(){
		em.close();
	}
}
