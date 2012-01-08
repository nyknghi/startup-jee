package gestion_investisseurs;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstraitInvestisseur {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long idInvestisseur;
	
	public long getIdInvestisseur() {
		return idInvestisseur;
	}
}
