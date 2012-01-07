package ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejb.entity.Fondateur;

@Stateless (name="InvestisseursBean")
public class InvestisseursBean implements InvestisseursRemote {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Fondateur ajouterFondateur(Fondateur f) {
		em.persist(f);
		return f;
	}
	
	public String testSession(){
		System.out.println("Calling Test session");
		return "my test";
	}
}
