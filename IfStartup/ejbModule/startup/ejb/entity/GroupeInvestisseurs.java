package startup.ejb.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="IFStartupBD", name="GroupeInvestisseurs")
public class GroupeInvestisseurs extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int idGroupe;
	@Basic(optional=false)
	private String nomGroupe;
	
	private Investisseur mandataire;
	private Set<Investisseur> investisseurs;
	
	public GroupeInvestisseurs (String nom){
		this.nomGroupe = nom;
		investisseurs = new HashSet<Investisseur>();
	}
	
	public GroupeInvestisseurs (String nom, Set<Investisseur> inv){
		this.nomGroupe = nom;
		this.investisseurs = inv;
	}

	public int getIdGroupe() {
		return idGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public Investisseur getMandataire() {
		return mandataire;
	}

	public void setMandataire(Investisseur mandataire) {
		this.mandataire = mandataire;
	}

	public Set<Investisseur> getInvestisseurs() {
		return investisseurs;
	}

	public void setInvestisseurs(Set<Investisseur> investisseurs) {
		this.investisseurs = investisseurs;
	}
}
