package startup.ejb.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="IFStartupBD", name="BusinessAngel")
public class BusinessAngel extends AbstraitInvestisseur{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int idFondateur;
	@Basic(optional=false)
	private String nom;
	@Basic(optional=false)
	private String mail;
	@Basic(optional=false)
	private String mdp;
	
	public BusinessAngel(){}
	
	public ClubAmi monterClub (String nom){
		return new ClubAmi (nom);
	}

	public Membre devenirMembre (ClubAmi ca){
		return new Membre (ca, this);
	}
	
	public void quitterClub (ClubAmi ca){
		// TODO...
	}
	
	@Override
	public Participation investir(Startup st, float montant) {
		return null;
	}

	@Override
	public Inscription inscrireLeveeDeFonds(LeveeDeFonds le) {
		return null;
	}

	
	
}
