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
	private boolean isMandataire=false;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="organisateur")
	private Set<LeveeDeFonds> leveeDeFonds;
	
	public Fondateur(){}
	
	public Fondateur(String nom, String mail, String mdp){
		this.nom = nom;
		this.mail = mail;
		this.mdp = mdp;
		leveeDeFonds = new HashSet<LeveeDeFonds>();
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
		return leveeDeFonds;
	}

	public void setLeveeDeFonds(Set<LeveeDeFonds> leveeDeFonds) {
		this.leveeDeFonds = leveeDeFonds;
	}

	@Override
	public String toString() {
		return "Fondateur [nom=" + nom + ", startup=" + startup.getNomStartup() + ", isMandataire="
				+ isMandataire + ", mail=" + mail + ", mdp=" + mdp +"]";
	}
}
