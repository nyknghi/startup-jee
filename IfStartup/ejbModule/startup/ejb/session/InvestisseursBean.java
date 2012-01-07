package startup.ejb.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import startup.ejb.entity.Fondateur;
import startup.ejb.entity.Startup;


@Stateless (name="InvestisseursBean")
public class InvestisseursBean implements InvestisseursRemote, InvestisseursLocal {
	@PersistenceContext
	EntityManager em;
	
	/*@EJB
	Gest_Events gest;
	*/
	
	@Override
	// Le fondateur qui cree le startup doit etre persiste
	public Startup creerStartup(String nom, String activite, float capital, Fondateur f) {
		Startup s = new Startup(nom, activite, capital, f);
		em.persist(s);
		return s;
	}

	@Override
	public Fondateur ajouterFondateur(Fondateur f, Startup s) {
		s.addFondateur(f);
		em.persist(s);
		return f;
	}

	@Override
	public ArrayList<Fondateur> rechercherFondateur(String nom) {
		Query query = em.createNamedQuery("findFondateurByName");
		query.setParameter("nom", nom);
		return (ArrayList<Fondateur>) query.getResultList();
	}
}
