package gestion_investisseurs;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema="IFStartupBD", name="GroupeInvestisseurs")
@DiscriminatorValue("GROUPE")
public class GroupeInvestisseurs extends AbstraitInvestisseur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Investisseur mandataire;
	private Set<Investisseur> investisseurs;
	
	public GroupeInvestisseurs (String nom){
		this.nom = nom;
		investisseurs = new HashSet<Investisseur>();
	}
	
	public GroupeInvestisseurs (String nom, Set<Investisseur> inv){
		this.nom = nom;
		this.investisseurs = inv;
	}

	public int getIdGroupe() {
		return idInvestisseur;
	}

	public String getNomGroupe() {
		return nom;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nom = nomGroupe;
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
