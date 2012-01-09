package gestion_investisseurs;

import gestion_events.LeveeDeFonds;
import gestion_events.Startup;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Fondateur")
@NamedQuery(name="findFondateurByName", query="SELECT f FROM Fondateur as f WHERE f.nom = :nom")
public class Fondateur extends AbstraitInvestisseur{
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(referencedColumnName="idStartup")
	private Startup startup;
	@Column
	private boolean isMandataire=false;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="organisateur", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Set<LeveeDeFonds> leveeDeFondsFO;
	
	public Fondateur(){}
	
	public Fondateur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
		leveeDeFondsFO = new HashSet<LeveeDeFonds>();
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

	public Set<LeveeDeFonds> getLeveeDeFonds() {
		return leveeDeFondsFO;
	}

	public void setLeveeDeFonds(Set<LeveeDeFonds> leveeDeFonds) {
		this.leveeDeFondsFO = leveeDeFonds;
	}

	@Override
	public String toString() {
		return "Fondateur [nom=" + nom + ", idstartup=" + startup.getIdStartup() + ", isMandataire="
				+ isMandataire + ", mail=" + mail + ", mdp=" + mdp +"]";
	}
}
