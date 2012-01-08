package gestion_investisseurs;

import gestion_events.Startup;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class BeanInvestisseurs implements RemoteInvestisseurs, LocalInvestisseurs {
	@PersistenceContext(unitName="SampleUnit")
	EntityManager em;
	
	public String afficherText(String t){
		return t;
	}
	/*@EJB
	Gest_Events gest;
	*/
	@PostConstruct
	public void init(){
		System.out.println("Calling init method");
	}

	@Override
	// Le fondateur qui cree le startup doit etre persiste
	public Startup creerStartup(String nom, String activite, float capital, Fondateur f) {
		Startup s = new Startup(nom, activite, capital, f);
		em.persist(s);
		return s;
	}

	@Override
	public Fondateur ajouterFondateur(Fondateur f, long idStartup) {
		Startup s = this.rechercherStartupParId(idStartup);
		s.addFondateur(f);
		f.setStartup(s);
		em.merge(s);
		return em.merge(f);
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
	
}
