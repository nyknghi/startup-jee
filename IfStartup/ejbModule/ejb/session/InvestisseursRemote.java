package ejb.session;

import javax.ejb.Remote;
import ejb.entity.Fondateur;

@Remote
public interface InvestisseursRemote {
	public Fondateur ajouterFondateur (Fondateur f);
	public String testSession();
}
