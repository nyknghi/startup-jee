package startup.ejb.entity;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GroupeInvestisseurs extends AbstraitInvestisseur{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int idGroupe;
	@Basic(optional=false)
	private String nom;
	@Basic(optional=false)
	private String mail;
	@Basic(optional=false)
	private String mdp;
	
	
	
	@Override
	public Participation investir(Startup st, float montant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscription inscrireLeveeDeFonds(LeveeDeFonds le) {
		// TODO Auto-generated method stub
		return null;
	}

}
