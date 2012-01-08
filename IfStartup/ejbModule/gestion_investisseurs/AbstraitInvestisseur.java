package gestion_investisseurs;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstraitInvestisseur {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long idInvestisseur;
	@Basic(optional=false)
	protected String nom;
	@Basic(optional=false)
	protected String mail;
	@Basic(optional=false)
	protected String mdp;
}
