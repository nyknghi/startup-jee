package startup.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import startup.ejb.entity.Fondateur;


@Stateless (name="InvestisseursBean")
public class InvestisseursBean implements InvestisseursRemote, InvestisseursLocal {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Fondateur ajouterFondateur(Fondateur f) {
		em.persist(f);
		return f;
	}
}
