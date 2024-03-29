package gestion_investisseurs;

import gestion_events.Startup;

import java.util.ArrayList;
import javax.ejb.Remote;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ApplicationException;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Stateless
@ApplicationException(rollback = true)
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(RemoteInvestisseurs.class)
public class BeanInvestisseurs implements RemoteInvestisseurs, LocalInvestisseurs{
	@PersistenceContext(unitName="SampleUnit")
	EntityManager em;
	@Resource
	private EJBContext context;
 
	UserTransaction ut = context.getUserTransaction();
	
	public String afficherText(String t){
		return t;
	}

	@PostConstruct
	public void init(){
		System.out.println("Calling init method");
	}
	
	@Override
	public Fondateur ajouterFondateur(Fondateur f) {
		em.persist(f);
		return f;
	}

	@Override
	public Fondateur ajouterFondateurStartup(Fondateur f, Startup s) {
		try {
			ut.begin();
			s.addFondateur(f);
			f.setStartup(s);
			em.merge(s);
			f = em.merge(f);
			ut.commit();
		} catch (NotSupportedException | SecurityException | IllegalStateException
				| RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			System.out.println("Probleme de transaction !");
			e.printStackTrace();
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}	
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
	// Le fondateur qui cree le startup doit etre persiste
	public Startup creerStartup(String nom, String activite, double capital, Fondateur f) {
		Startup s = null;
		try {
			ut.begin();
			s = new Startup(nom, activite, capital, f);
			f.setStartup(s);
			em.persist(s);
			em.merge(f);
			ut.commit();
		} catch (NotSupportedException | SecurityException | IllegalStateException
				| RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			System.out.println("Probleme de transaction !");
			e.printStackTrace();
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}	
		return s;
	}

	@Override
	public Startup rechercherStartupParId (long idStartup){
		return em.find(Startup.class, idStartup);
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
		Membre m = this.rechercherMembreParId(ba.getIdBusinessAngel(), ca.getIdClub());
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
	
	@PreDestroy()
	public void cleanUp(){
		System.out.println("Calling cleanup method");
	}	
	
	public void closeEM(){
		em.close();
	}

	@Override
	public GroupeInvestisseurs monterGroupe(Investisseur inv, String nomGroupe) {
		GroupeInvestisseurs groupe = new GroupeInvestisseurs(nomGroupe);
		em.persist(groupe);
		this.adhererGroupe(groupe, inv);
		return groupe;
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
}
