package gestion_investisseurs;

import gestion_events.Startup;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Fondateur")
@NamedQuery(name="findFondateurByName", query="SELECT f FROM Fondateur as f WHERE f.nom = :nom")
public class Fondateur extends AbstraitInvestisseur{
	@ManyToOne (fetch=FetchType.EAGER)
	@JoinColumn(referencedColumnName="idStartup")
	private Startup startup = null;
	
	@Column
	private boolean isMandataire=false;
	
	public Fondateur(){}
	
	public Fondateur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	public Long organisateurId(){
		return getIdInvestisseur();
	}
	
	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public boolean isMandataire() {
		return isMandataire;
	}

	public void setMandataire(boolean isMandataire) {
		this.isMandataire = isMandataire;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isMandataire ? 1231 : 1237);
		result = prime * result + ((startup == null) ? 0 : startup.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fondateur other = (Fondateur) obj;
		if (isMandataire != other.isMandataire)
			return false;
		if (startup == null) {
			if (other.startup != null)
				return false;
		} else if (!startup.equals(other.startup))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fondateur [id=" + idInvestisseur + ", nom=" + nom + ", idstartup=" + startup.getIdStartup() + ", isMandataire="
				+ isMandataire + ", mail=" + mail + ", mdp=" + mdp +"]";
	}
}
