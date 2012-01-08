package gestion_investisseurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GroupeInvestisseurs")
@DiscriminatorValue("GROUPE")
public class GroupeInvestisseurs extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	@Basic(optional=false)
	protected String nom;
	@Basic(optional=false)
	protected String mail;
	@Basic(optional=false)
	protected String mdp;
	@OneToMany(mappedBy="groupe")
	@JoinColumn(name="investisseursId")
	private List<Investisseur> investisseurs;
	
	public GroupeInvestisseurs (String nom){
		this.nom = nom;
		this.investisseurs = new ArrayList<Investisseur>();
	}
	
	public GroupeInvestisseurs (String nom, List<Investisseur> inv){
		this.nom = nom;
		this.investisseurs = inv;
	}

	public long getIdGroupe() {
		return idInvestisseur;
	}

	public String getNomGroupe() {
		return nom;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nom = nomGroupe;
	}

	public List<Investisseur> getInvestisseurs() {
		return investisseurs;
	}

	public void setInvestisseurs(List<Investisseur> investisseurs) {
		this.investisseurs = investisseurs;
	}
}
