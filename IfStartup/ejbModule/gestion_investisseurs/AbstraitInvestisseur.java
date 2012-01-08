package gestion_investisseurs;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstraitInvestisseur {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long idInvestisseur;
	
	protected String nom;
	protected String mail;
	protected String mdp;
}
