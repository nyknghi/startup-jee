package ejb.session;

import javax.ejb.Local;
import ejb.entity.Fondateur;

@Local
public interface InvestisseursLocal {
	public Fondateur ajouterFondateur (Fondateur f);
}
