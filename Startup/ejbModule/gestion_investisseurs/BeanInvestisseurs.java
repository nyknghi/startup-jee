package gestion_investisseurs;

import gestion_events.Startup;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanInvestisseurs implements RemoteInvestisseurs, LocalInvestisseurs{
	@PersistenceContext(unitName="SampleUnit")
	EntityManager em;
	
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
	public Fondateur ajouterFondateurStartup(Fondateur f, Startup s, boolean isMandataire) {
		s.addFondateur(f);
		f.setStartup(s);
		f.setMandataire(isMandataire);
		em.merge(s);
		f = em.merge(f);	
		return f;
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
		this.ajouterMembre(ba, ca.getIdClub(), true);
		em.persist(ca);
		em.merge(ba);
		return ca;
	}
	
	@Override
	public ClubAmi updateClubAmi(ClubAmi ca, String nomClub) {
		ca = this.rechercherClubParId(ca.getIdClub());
		ca.setNomClub(nomClub);
		return em.merge(ca);
	}
	
	@Override
	public ClubAmi rechercherClubParId (long id){
		return em.find(ClubAmi.class, id);
	}
	
	@Override
	public void ajouterMembre (BusinessAngel ba, long idClub, boolean mandataire){
		ClubAmi ca = this.rechercherClubParId(idClub);
		ba.setMandataire(mandataire);
		Membre membre = new Membre(ca, ba);
		ca.getMembres().add(membre);
		ba.getClubAmis().add(membre);
		em.persist(ba);
		em.persist(membre);
		em.merge(ca);
	}
	
	@Override
	public void supprimerMembre (BusinessAngel ba, ClubAmi ca){
		Membre m = this.rechercherMembreParId(ba.getIdInvestisseur(), ca.getIdClub());
		ca.getMembres().remove(m);
		ba.getClubAmis().remove(m);
		em.remove(m);
		em.merge(ba);
		em.merge(ca);
		em.flush();
	}
	
	@Override
	public Membre rechercherMembreParId (long idBA, long idClub){
		Query query = em.createNamedQuery("findMembreById");
		query.setParameter("idBA", idBA);
		query.setParameter("idClub", idClub);
		return (Membre) query.getSingleResult();
	}	

	@Override
	public GroupeInvestisseurs monterGroupe(Investisseur inv, String nomGroupe) {
		GroupeInvestisseurs groupe = new GroupeInvestisseurs(nomGroupe);
		em.persist(groupe);
		this.adhererGroupe(groupe, inv);
		return groupe;
	}

	@Override
	public GroupeInvestisseurs updateGroupeInvestisseurs(GroupeInvestisseurs groupe, String nomGroupe) {
		groupe = this.rechercherGroupeParId(groupe.getIdInvestisseur());
		groupe.setNom(nomGroupe);
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
	public void adhererGroupe(GroupeInvestisseurs groupe, Investisseur inv) {
		groupe.getInvestisseurs().add(inv);
		inv.setGroupe(groupe);
		em.merge(groupe);
		em.merge(inv);
	}

	@Override
	public void quitterGroupe(GroupeInvestisseurs groupe, Investisseur inv) {
		groupe.getInvestisseurs().remove(inv);
		inv.setGroupe(null);
		em.merge(groupe);
		em.merge(inv);		
	}
	
	@PreDestroy()
	public void cleanUp(){
		System.out.println("Calling cleanup method");
	}	
	
	public void closeEM(){
		em.close();
	}
}
