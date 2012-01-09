package gestion_investisseurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GroupeInvestisseurs")
@DiscriminatorValue("GROUPE")
public class GroupeInvestisseurs extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="groupe")
	private List<Investisseur> investisseurs;
	
	public GroupeInvestisseurs(){}
	
	public GroupeInvestisseurs (String nom){
		this.nom = nom;
		this.investisseurs = new ArrayList<Investisseur>();
	}
	
	public GroupeInvestisseurs (String nom, List<Investisseur> inv){
		this.nom = nom;
		this.investisseurs = inv;
	}

	public List<Investisseur> getInvestisseurs() {
		return investisseurs;
	}

	public void setInvestisseurs(List<Investisseur> investisseurs) {
		this.investisseurs = investisseurs;
	}
}
